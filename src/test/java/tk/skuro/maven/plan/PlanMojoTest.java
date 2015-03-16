package tk.skuro.maven.plan;

import org.apache.maven.plugin.testing.MojoRule;
import org.codehaus.plexus.PlexusTestCase;
import org.junit.Rule;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PlanMojoTest {

    @Rule
    public MojoRule rule = new MojoRule();

    @Test
    public void testSomething()
            throws Exception
    {
        File pom = PlexusTestCase.getTestFile("src/test/resources/pom-all-defaults.xml");
        assertNotNull( pom );
        assertTrue( pom.exists() );

        PlanMojo plan = (PlanMojo) rule.lookupMojo("plan", pom);
        assertNotNull( plan );
        plan.execute();
    }

}
