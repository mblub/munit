package org.mule.munit;

import org.mule.api.annotations.param.OutboundHeaders;
import org.mule.api.annotations.param.Payload;

import java.util.Map;

/**
 * Created by damiansima on 12/19/14.
 */
public class AnnotatedComponent {

    public Object process(@Payload Object payload, @OutboundHeaders Map<String, Object> outHeaders) {
        System.out.println("the component Test: " + payload.toString());
        payload = "the new payload";
        return payload;
    }
}
