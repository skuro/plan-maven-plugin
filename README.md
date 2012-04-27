Plan Maven Plugin
=================

Execution plan made easy.

The development of this simple plugin stems from a [question](http://stackoverflow.com/questions/10331462/maven-execution-plan) on SO. The idea is to have maven providing a more
succint feedback on the execution plan, without the need to run through the whole logs to figure out what did it do.

Usage
=====

This is what a normal invokation would look like:

    $ mvn plan:plan clean javadoc
    [...]
    [INFO] --- plan-maven-plugin:1.0-SNAPSHOT:plan (default-cli) @ plan-maven-plugin ---
    [INFO] Execution plan:
    [INFO]     [-] tk.skuro:plan-maven-plugin:plan (default-cli)
    [INFO]     [clean] org.apache.maven.plugins:maven-clean-plugin:clean (default-clean)
    [INFO]     [-] org.apache.maven.plugins:maven-javadoc-plugin:javadoc (default-cli)
    [INFO]     [validate] org.apache.maven.plugins:maven-enforcer-plugin:enforce (enforce-maven)
    [INFO]     [generate-resources] org.apache.maven.plugins:maven-plugin-plugin:descriptor (default-descriptor)
    [INFO]     [process-resources] org.apache.maven.plugins:maven-resources-plugin:resources (default-resources)
    [INFO]     [compile] org.apache.maven.plugins:maven-compiler-plugin:compile (default-compile)
    [INFO]     [process-test-resources] org.apache.maven.plugins:maven-resources-plugin:testResources (default-testResources)
    [INFO]     [test-compile] org.apache.maven.plugins:maven-compiler-plugin:testCompile (default-testCompile)
    [INFO]     [test] org.apache.maven.plugins:maven-surefire-plugin:test (default-test)
    [INFO]     [package] org.apache.maven.plugins:maven-jar-plugin:jar (default-jar)
    [INFO]     [package] org.apache.maven.plugins:maven-plugin-plugin:addPluginArtifactMetadata (default-addPluginArtifactMetadata)
    [INFO]     [validate] org.apache.maven.plugins:maven-enforcer-plugin:enforce (enforce-maven)
    [INFO]     [generate-resources] org.apache.maven.plugins:maven-plugin-plugin:descriptor (default-descriptor)
    [INFO]     [process-resources] org.apache.maven.plugins:maven-resources-plugin:resources (default-resources)
    [INFO]     [compile] org.apache.maven.plugins:maven-compiler-plugin:compile (default-compile)
    [INFO]     [process-test-resources] org.apache.maven.plugins:maven-resources-plugin:testResources (default-testResources)
    [INFO]     [test-compile] org.apache.maven.plugins:maven-compiler-plugin:testCompile (default-testCompile)
    [INFO]     [test] org.apache.maven.plugins:maven-surefire-plugin:test (default-test)
    [INFO]     [package] org.apache.maven.plugins:maven-jar-plugin:jar (default-jar)
    [INFO]     [package] org.apache.maven.plugins:maven-plugin-plugin:addPluginArtifactMetadata (default-addPluginArtifactMetadata)
    [INFO]     [install] org.apache.maven.plugins:maven-install-plugin:install (default-install)
    [INFO]     [-] org.apache.maven.plugins:maven-site-plugin:site (default-cli)
