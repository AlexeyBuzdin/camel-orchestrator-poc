package lv.ctco.camelpoc.camel;

import org.apache.camel.builder.*;
import org.apache.camel.model.*;

public abstract class AmqpRouteBuilder extends RouteBuilder {

    public static final String RABBITMQ_URL = "rabbitmq://192.168.99.100:32771";


    protected RouteDefinitionWrapper camel(ProcessorDefinition<RouteDefinition> rootDefinition) {
        return new RouteDefinitionWrapper(
                rootDefinition, this
        );
    }

    protected RouteDefinitionWrapper from(String topic, String direction) {
        return new RouteDefinitionWrapper(
                from(createAmqpUri(topic, direction))
                        .log("Processing ${body}")
                        .removeHeaders("rabbitmq.*"), this
        );
    }

    public RouteDefinition to(ProcessorDefinition<RouteDefinition>  routeDefinition, String topic, String direction) {
        return routeDefinition.to(createAmqpUri(topic, direction)).id(topic);
    }

    private String createAmqpUri(String topic, String direction) {
        return RABBITMQ_URL + "/"+topic +"?" +
                "autoDelete=false&" +
                "exchangeType=topic&" +
                "routingKey="+topic+ "." + direction;
    }
}
