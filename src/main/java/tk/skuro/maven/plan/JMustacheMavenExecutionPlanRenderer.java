package tk.skuro.maven.plan;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.apache.maven.lifecycle.MavenExecutionPlan;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.logging.AbstractLogEnabled;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Renders a {@link MavenExecutionPlan} using JMustache
 */
@Component(role = MavenExecutionPlanRenderer.class)
public class JMustacheMavenExecutionPlanRenderer extends AbstractLogEnabled implements MavenExecutionPlanRenderer {

    private static final Template template;

    static {
        ClassLoader classLoader = JMustacheMavenExecutionPlanRenderer.class.getClassLoader();
        InputStream templateStream = classLoader.getResourceAsStream("plan.mustache");
        template = Mustache.compiler().defaultValue("-").compile(new InputStreamReader(templateStream));
    }

    /**
     * {@inheritDoc}
     */
    public String render(MavenExecutionPlan plan) {
        Map<String, Object> ctx = new HashMap<String, Object>();
        List<String> phases = extractPhasesInLifecycle(plan);
        ctx.put("plan", plan);
        ctx.put("phases", phases.isEmpty() ? new String[]{} : new String[]{"true"});
        return template.execute(ctx);
    }

    private List<String> extractPhasesInLifecycle(MavenExecutionPlan plan) {
        try {
            Field phasesField = plan.getClass().getDeclaredField("phasesInExecutionPlan");
            phasesField.setAccessible(true);
            return (List<String>) phasesField.get(plan);
        } catch (Throwable e) {
            return Collections.emptyList();
        }
    }
}
