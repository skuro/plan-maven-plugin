package tk.skuro.maven.plan;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.apache.maven.lifecycle.MavenExecutionPlan;
import org.codehaus.plexus.component.annotations.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Renders a {@link MavenExecutionPlan} using JMustache
 */
@Component( role = MavenExecutionPlanRenderer.class )
public class JMustacheMavenExecutionPlanRenderer implements MavenExecutionPlanRenderer {

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
        ctx.put("plan", plan);
        return template.execute(ctx);
    }
}
