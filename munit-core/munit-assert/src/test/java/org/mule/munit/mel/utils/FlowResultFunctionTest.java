/*
 *
 * (c) 2003-2012 MuleSoft, Inc. This software is protected under international
 * copyright law. All use of this software is subject to MuleSoft's Master
 * Subscription Agreement (or other Terms of Service) separately entered
 * into between you and MuleSoft. If such an agreement is not in
 * place, you may not use the software.
 */
package org.mule.munit.mel.utils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mule.api.MuleContext;
import org.mule.api.MuleMessage;
import org.mule.api.el.ExpressionLanguageContext;
import org.mule.api.registry.MuleRegistry;
import org.mule.module.scripting.component.Scriptable;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Mulesoft Inc.
 * @since 3.3.2
 */
public class FlowResultFunctionTest
{

    public static final String SCRIPT_NAME = "scriptName";
    public static final String SCRIPT_RESULT = "scriptResult";
    private MuleContext muleContext = mock(MuleContext.class);
    private MuleRegistry muleRegistry = mock(MuleRegistry.class);
    private Scriptable script = mock(Scriptable.class);
    private ScriptEngine scriptEngine = mock(ScriptEngine.class);
    private Bindings bindings = mock(Bindings.class);
    private ExpressionLanguageContext context = mock(ExpressionLanguageContext.class);
    private MuleMessage muleMessage = mock(MuleMessage.class);

    @Before
    public void returnMuleContext()
    {
       when(context.getVariable("_muleMessage")).thenReturn(muleMessage);
       when(muleMessage.getMuleContext()).thenReturn(muleContext);
    }


    @Test(expected = IllegalArgumentException.class)
    public void callWithNull()
    {
        new FlowResultFunction().call(null, context);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callWithEmpty()
    {
        new FlowResultFunction().call(new Object[] {}, context);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callWithNotString()
    {
        new FlowResultFunction().call(new Object[] {new Object()}, context);
    }

    @Test(expected = NullPointerException.class)
    public void callWithScriptNameThatDoesNotExist()
    {
        when(muleContext.getRegistry()).thenReturn(muleRegistry);
        when(muleRegistry.lookupObject(SCRIPT_NAME)).thenReturn(null);
        new FlowResultFunction().call(new Object[] {SCRIPT_NAME}, context);
    }

    @Test(expected = IllegalArgumentException.class)
    public void callWithScriptNameThatExistsButIsNotAnScript()
    {
        when(muleContext.getRegistry()).thenReturn(muleRegistry);
        when(muleRegistry.lookupObject(SCRIPT_NAME)).thenReturn(new Object());
        new FlowResultFunction().call(new Object[] {SCRIPT_NAME}, context);
    }


    @Test
    public void callWithScriptNameThatExists() throws ScriptException
    {
        when(muleContext.getRegistry()).thenReturn(muleRegistry);
        when(muleRegistry.lookupObject(SCRIPT_NAME)).thenReturn(script);
        when(script.getScriptEngine()).thenReturn(scriptEngine);
        when(scriptEngine.createBindings()).thenReturn(bindings);
        when(script.runScript(bindings)).thenReturn(SCRIPT_RESULT);

        assertEquals(SCRIPT_RESULT, new FlowResultFunction().call(new Object[] {SCRIPT_NAME}, context));
    }

    @Test(expected = RuntimeException.class)
    public void callAndScriptFails() throws ScriptException
    {
        when(muleContext.getRegistry()).thenReturn(muleRegistry);
        when(muleRegistry.lookupObject(SCRIPT_NAME)).thenReturn(script);
        when(script.getScriptEngine()).thenReturn(scriptEngine);
        when(scriptEngine.createBindings()).thenReturn(bindings);
        when(script.runScript(bindings)).thenThrow(new ScriptException("error"));

        new FlowResultFunction().call(new Object[] {SCRIPT_NAME}, context);
    }


}
