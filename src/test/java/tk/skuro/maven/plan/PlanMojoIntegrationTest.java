package tk.skuro.maven.plan;

import junit.framework.TestCase;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

import java.io.File;

public class PlanMojoIntegrationTest extends TestCase {

    private final static String defaultMavenHome = "/home/linuxbrew/.linuxbrew";

    public void setUp(){
        if(System.getProperty("maven.home") == null) {
            String m2Home = System.getenv("M2_HOME");
            if(m2Home != null) {
                System.setProperty("maven.home", m2Home);
            } else {
                System.setProperty("maven.home", defaultMavenHome);
            }
        }
        System.out.println("Using maven.home=" + System.getProperty("maven.home"));
    }

    public void testMyPlugin()
            throws Exception {
        File testDir = ResourceExtractor.simpleExtractResources(getClass(), "/test-project");

        Verifier verifier = new Verifier(testDir.getAbsolutePath());
        verifier.executeGoal("plan:plan");
        verifier.verifyErrorFreeLog();

        /*
         * Reset the streams before executing the verifier
         * again.
         */
        verifier.resetStreams();
    }
}