package lv.ctco.camelpoc.camel;

import org.apache.camel.builder.*;
import org.apache.camel.model.*;

public abstract class AmqpRouteBuilder extends RouteBuilder {



    protected RouteDefinitionWrapper camel(ProcessorDefinition<RouteDefinition> rootDefinition) {
        return new RouteDefinitionWrapper(
                rootDefinition, this
        );
    }

    protected RouteDefinitionWrapper from(String topic, String direction) {
        return new RouteDefinitionWrapper(
                from("mq://"+topic+ "?direction=" + direction)
                        .log("Processing ${body}")
                        .removeHeaders("rabbitmq.*"), this
        );
    }

    public RouteDefinition to(ProcessorDefinition<RouteDefinition>  routeDefinition, String topic, String direction) {
        return routeDefinition.to("mq://"+topic+ "?direction=" + direction).id(topic);
    }
}
