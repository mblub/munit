Mule Syncronize module
======================

This module allows you to wait for the async element you throw in your flow and your asyn flow refs.

Import in the XML
-----------------

```xml

<mule xmlns="http://www.mulesoft.org/schema/mule/core"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:synchronize="http://www.mulesoft.org/schema/mule/synchronize"
      xsi:schemaLocation="
        http://www.mulesoft.org/schema/mule/core http://www.mulesoft.org/schema/mule/core/current/mule.xsd
        http://www.mulesoft.org/schema/mule/synchronize http://www.mulesoft.org/schema/mule/synchronize/3.4.0/mule-synchronize.xsd">
        
```

Usage
-----

Waiting for async thrown in an inner flow:

```xml
<flow name="testFlow">
    <synchronize:run-and-wait timeout="16000">
        <flow-ref name="runAsyncs"/>
    </synchronize:run-and-wait>
</flow>

<flow name="runAsyncs">
    <async>
        <expression-component>
            Thread.sleep(3000);
        </expression-component>
    </async>
    <async>
        <expression-component>
            Thread.sleep(5000);
        </expression-component>
    </async>
</flow>
```

Waiting for flow-refs to an asynchronous flow:

If the flow that calls the flow-ref is synchronous, then mule won't allow you to do a flow-ref to an asynchronous. If this is the case set runAsync to true in this module:

```xml
<flow name="testAsyncFlow">
    <synchronize:run-and-wait runAsyc="true" timeout="16000">
       <flow-ref name="asyncFlow"/>
    </synchronize:run-and-wait>

    <custom-processor class="org.mule.EndedMessageProcessor"/>
</flow>

<flow name="asyncFlow" processingStrategy="asynchronous">
    <expression-component>
        Thread.sleep(4000);
    </expression-component>
</flow>
```

For the case of asynchronous flow calling asynchronous flow, you can avoid using the runAsync:

```xml
<flow name="testAsyncFlow"  processingStrategy="asynchronous">
    <synchronize:run-and-wait timeout="16000">
       <flow-ref name="asyncFlow"/>
    </synchronize:run-and-wait>

    <custom-processor class="org.mule.EndedMessageProcessor"/>
</flow>
```


JAVA api usage
--------------

You need to implement the Synchronizer abstract class:

```JAVA
Synchronizer synchronizer = new Synchronizer(muleContext, 12000)
{

    @Override
    protected MuleEvent process(MuleEvent event) throws Exception
    {
        // Process the event
        return event;
    }
};

synchronizer.runAndWait(event);

```









