munit
=====

Munit is a Mule testing framework, it allows mule developers to automate mule app testing in an easy manner.

With Munit you can:

* Create your mule test by writing mule code
* Create your mule test by writing java code.
* Disable flow inbound endpoints
* Disable endpoint connectors.
* Mock outbound endpoints
* Mock Message processors
* Spy any message processor.
* Verify Message processor calls.
* Assert flow exceptions
* Enable or disable particular tests
* It is fully integrated with Maven and Surefire, so you can add your tests to a continuous integration environment.
* Run your tests from eclipse
* See your assertion/error report with Mule stack trace

## Dependencies

Munit depends on the public project [mule-interceptor-module](https://github.com/mulesoft/mule-interceptor-module). So if
you are working on master you might want to clone and compile that repository too.

## Compiling

Run:

```console
mvn clean install
```

with the following maven opts

```console
MAVEN_OPTS="-Xmx1G -XX:MaxPermSize=512m"
```


## Modules

* [munit-core] (https://github.com/mulesoft/munit/tree/master/munit-core) contains the major functionality of munit (mocking, assertion, runner)
* [munit-integration-tests] (https://github.com/mulesoft/munit/tree/master/munit-integration-tests) The integration suite for the munit project (good place for examples)
* [munit-maven-tools] (https://github.com/mulesoft/munit/tree/master/munit-maven-tools) Munit maven plugin/archetype
* [munit-utils] (https://github.com/mulesoft/munit/tree/master/munit-utils) Utilities for mule integration testing
* [tooling] (https://github.com/mulesoft/munit/tree/master/tooling) Not included in the build. Eclipse plugin


