package tk.skuro.maven.plan;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.lifecycle.MavenExecutionPlan;
import org.apache.maven.lifecycle.internal.LifecycleExecutionPlanCalculator;
import org.apache.maven.lifecycle.internal.LifecycleTaskSegmentCalculator;
import org.apache.maven.lifecycle.internal.TaskSegment;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import java.util.List;
import java.util.Scanner;

/**
 * Goal which prints the execution plan
 *
 * @goal plan
 */
public class PlanMojo extends AbstractMojo {

    private Log log;

    /**
     * @component
     */
    private LifecycleExecutionPlanCalculator planCalculator;

    /**
     * @component
     */
    private LifecycleTaskSegmentCalculator lifecycleTaskSegmentCalculator;

    /**
     * @component
     */
    private MavenExecutionPlanRenderer renderer;

    /**
     * The Maven Session Object
     *
     * @parameter expression="${session}"
     * @required
     * @readonly
     */
    private MavenSession session;

    /**
     * The Maven Project Object
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    protected MavenProject project;

    public void execute() throws MojoExecutionException {
        try {
            for (TaskSegment segment : lifecycleTaskSegmentCalculator.calculateTaskSegments(session)) {
                List<Object> goals = segment.getTasks();
                MavenExecutionPlan plan = planCalculator.calculateExecutionPlan(session, project, goals, false);
                outputPlan(plan);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private void outputPlan(MavenExecutionPlan plan) {
        Scanner scanner = new Scanner(renderer.render(plan));
        while(scanner.hasNextLine()) {
            log.info(scanner.nextLine());
        }
    }

    public void setLog(Log log) {
        this.log = log;
    }
}
