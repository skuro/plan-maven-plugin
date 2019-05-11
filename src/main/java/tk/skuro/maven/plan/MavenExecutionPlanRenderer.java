package tk.skuro.maven.plan;

import org.apache.maven.lifecycle.MavenExecutionPlan;

/**
 * Used to render the Plan plugin output
 *
 * @since 1.0
 * @author Carlo Sciolla
 */
public interface MavenExecutionPlanRenderer {

    /**
     * Renders a {@link MavenExecutionPlan}
     * @param plan The plan to render
     * @return The rendered plan
     */
    String render (MavenExecutionPlan plan);
}
