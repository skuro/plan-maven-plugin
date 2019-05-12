package tk.skuro.maven.plan;

import junit.framework.TestCase;
import org.apache.maven.it.Verifier;
import org.apache.maven.it.util.ResourceExtractor;

import java.io.File;

public class PlanMojoIntegrationTest extends TestCase {
    public void testMyPlugin()
            throws Exception {
        File testDir = ResourceExtractor.simpleExtractResources(getClass(), "/test-project");

        System.setProperty("maven.home", "/home/linuxbrew/.linuxbrew");
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