package tk.skuro.maven.plan;

import org.apache.maven.execution.DefaultMavenExecutionRequest;
import org.apache.maven.execution.DefaultMavenExecutionResult;
import org.apache.maven.execution.MavenExecutionRequest;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.lifecycle.MavenExecutionPlan;
import org.apache.maven.lifecycle.internal.LifecycleExecutionPlanCalculator;
import org.apache.maven.lifecycle.internal.LifecycleTaskSegmentCalculator;
import org.apache.maven.lifecycle.internal.TaskSegment;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.PlexusConstants;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.context.Context;
import org.codehaus.plexus.context.ContextException;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Goal which prints the execution plan
 */
@Mojo(name = "plan")
public class PlanMojo extends AbstractMojo implements Contextualizable {

    @Component
    private LifecycleExecutionPlanCalculator planCalculator;

    @Component
    private LifecycleTaskSegmentCalculator lifecycleTaskSegmentCalculator;

    @Component
    private MavenExecutionPlanRenderer renderer;

    /**
     * The Maven Session Object
     */
    @Parameter(defaultValue = "${session}", required = true, readonly = true)
    private MavenSession session;

    /**
     * The Maven Project Object
     */
    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    /**
     * List of commands to use instead of those specified on the command line
     * e.g. "{@code clean,install,war:war}"
     */
    @Parameter( property = "goals")
    private List<String> goals;

    private PlexusContainer container;

    public void contextualize(Context context) throws ContextException {
        container = (PlexusContainer)context.get(PlexusConstants.PLEXUS_KEY);
    }

    public void execute() {
        final MavenSession actualSession = 0 == goals.size() ?
                session : createCustomSession();

        outputPlanForSession(actualSession);
    }

    private MavenSession createCustomSession() {
        final MavenExecutionRequest request =
                DefaultMavenExecutionRequest.copy(session.getRequest());
        request.setGoals(goals);

        final MavenExecutionResult result = new DefaultMavenExecutionResult();

        final MavenSession custom = new MavenSession(
                container, session.getRepositorySession(),
                request, result);
        custom.setProjects(session.getProjects());
        custom.setCurrentProject(session.getCurrentProject());
        custom.setParallel(session.isParallel());
        custom.setProjectDependencyGraph(session.getProjectDependencyGraph());

        return custom;
    }

    private void outputPlanForSession(MavenSession session) {
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
            getLog().info(scanner.nextLine());
        }
    }
}
