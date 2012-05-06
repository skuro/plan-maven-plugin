Plan Maven Plugin
=================

Execution plan made easy.

The development of this simple plugin stems from a [question](http://stackoverflow.com/questions/10331462/maven-execution-plan) on SO. The idea is to have maven providing a more
succint feedback on the execution plan, without the need to run through the whole logs to figure out what did it do.

Usage
=====

This is what a normal invokation would look like:

    $ mvn plan:plan clean javadoc:javadoc package install site:site
    [...]
    [INFO] --- plan-maven-plugin:1.1:plan (default-cli) @ plan-maven-plugin ---
    [INFO]
    [INFO] Current lifecycle:
    [INFO]     pre-clean
    [INFO]     clean
    [INFO]     post-clean
    [INFO]     validate
    [INFO]     initialize
    [INFO]     generate-sources
    [INFO]     process-sources
    [INFO]     generate-resources
    [INFO]     process-resources
    [INFO]     compile
    [INFO]     process-classes
    [INFO]     generate-test-sources
    [INFO]     process-test-sources
    [INFO]     generate-test-resources
    [INFO]     process-test-resources
    [INFO]     test-compile
    [INFO]     process-test-classes
    [INFO]     test
    [INFO]     prepare-package
    [INFO]     package
    [INFO]     pre-integration-test
    [INFO]     integration-test
    [INFO]     post-integration-test
    [INFO]     verify
    [INFO]     install
    [INFO]     deploy
    [INFO]
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
    [INFO]

Alternatively, you can specify a set of commands without actually executing them:

    $ mvn plan:plan -Dgoals=jar:jar
    [INFO] --- plan-maven-plugin:1.1:plan (default-cli) @ plan-maven-plugin ---
    [INFO]
    [INFO] Execution plan:
    [INFO]     [-] org.apache.maven.plugins:maven-jar-plugin:jar (default-cli)
    [INFO]
