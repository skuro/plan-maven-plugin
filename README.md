Plan Maven Plugin
=================

[![Latest version](https://maven-badges.herokuapp.com/maven-central/tk.skuro/plan-maven-plugin/badge.svg)](http://search.maven.org/#artifactdetails|tk.skuro|plan-maven-plugin|1.2|)

Add it to your project POM:

```xml
<plugins>
  <plugin>
    <groupId>tk.skuro</groupId>
    <artifactId>plan-maven-plugin</artifactId>
    <version>1.2</version> <!-- use 1.1 if using maven < 3.1.0 -->
  </plugin>
</plugins>
```

Or enable it globally in your `settings.xml`:

```xml
<settings>
  <pluginGroups>
    <pluginGroup>tk.skuro</pluginGroup>
  </pluginGroups>
</settings>
```

Execution plans explained
=========================

When creating complex builds with Maven, which extensively use profiles and activation rules, making sure that plugins execute in the proper order is sometimes a challenge in itself. This plugin queries the Maven machinery to provide a clean and concise report of your build execution plan, including the full list of the steps of the current lifecycle and the plugin executions linked to any lifecycle step.

The reported executions are given in the format:

    [{lifecyle-phase}] {groupId}:{artifactId}:{goal} ({execution-id})
    
Where `[-]` indicates that a plugin goal is directly executed instead of being triggered by a lifecycle step.
    
Usage
=====

You can specify a set of goals without actually executing them:

    $ mvn plan:plan -Dgoals=jar:jar
    [INFO] --- plan-maven-plugin:1.1:plan (default-cli) @ plan-maven-plugin ---
    [INFO]
    [INFO] Execution plan:
    [INFO]     [-] org.apache.maven.plugins:maven-jar-plugin:jar (default-cli)
    [INFO]

You can also run it as part of the normal build, letting it log
the plan along with executing the build itself:

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
