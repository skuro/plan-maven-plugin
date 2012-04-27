Plan Maven Plugin
=================

Execution plan made easy.

The development of this simple plugin stems from a [question][1] on SO. The idea is to have maven providing a more
succint feedback on the execution plan, without the need to run through the whole logs to figure out what did it do.

Usage
=====

This is what a normal invokation would look like:

    $ mvn plan:plan clean javadoc
    [...]
    [INFO] --- plan-maven-plugin:1.0:plan (default-cli) @ test ---
    [INFO] Execution plan:
    [INFO]     [-] tk.skuro:plan-maven-plugin:plan
    [INFO]     [clean] org.apache.maven.plugins:maven-clean-plugin:clean
    [INFO]     [-] org.apache.maven.plugins:maven-javadoc-plugin:jar
    [INFO]     [process-resources] org.apache.maven.plugins:maven-resources-plugin:resources
    [INFO]     [compile] org.apache.maven.plugins:maven-compiler-plugin:compile
    [INFO]     [process-test-resources] org.apache.maven.plugins:maven-resources-plugin:testResources
    [INFO]     [test-compile] org.apache.maven.plugins:maven-compiler-plugin:testCompile
    [INFO]     [test] org.apache.maven.plugins:maven-surefire-plugin:test
    [INFO]     [package] org.apache.maven.plugins:maven-jar-plugin:jar
    [INFO]     [install] org.apache.maven.plugins:maven-install-plugin:install

[1] http://stackoverflow.com/questions/10331462/maven-execution-plan