package lv.ctco.camelpoc.camel;

import org.apache.camel.model.*;

public class RouteDefinitionWrapper {

    private ProcessorDefinition<RouteDefinition> routeDefinition;
    private AmqpRouteBuilder amqpRouteBuilder;

    RouteDefinitionWrapper(ProcessorDefinition<RouteDefinition> routeDefinition, AmqpRouteBuilder amqpRouteBuilder) {
        this.routeDefinition = routeDefinition;
        this.amqpRouteBuilder = amqpRouteBuilder;
    }

    public ProcessorDefinition<RouteDefinition> to(String topic, String direction) {
        return routeDefinition = amqpRouteBuilder.to(routeDefinition, topic, direction);
    }

    public ProcessorDefinition<RouteDefinition> camel() {
        return routeDefinition;
    }
}
