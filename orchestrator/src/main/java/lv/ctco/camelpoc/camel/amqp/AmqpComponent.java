package lv.ctco.camelpoc.camel.amqp;

import org.apache.camel.*;
import org.apache.camel.component.rabbitmq.*;
import org.apache.camel.impl.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

public class AmqpComponent extends DefaultComponent {

    public static final String RABBITMQ_URL = "rabbitmq://192.168.99.100:32771";

    @Autowired
    CamelContext camelContext;

    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        RabbitMQComponent rabbitMQComponent = new RabbitMQComponent(camelContext);
        return rabbitMQComponent.createEndpoint(
                RABBITMQ_URL + "/" + remaining + "?" +
                "autoDelete=false&" +
                "exchangeType=topic&" +
                "routingKey=" + remaining + "." + parameters.get("direction"));
    }

    protected void validateParameters(String uri, Map<String, Object> parameters, String optionPrefix) {
        String[] unknownParams = parameters.keySet()
                .stream().filter(k -> !k.equals("direction")).toArray(String[]::new);
        boolean invalidParamsPresent = unknownParams.length > 0;
        if (invalidParamsPresent) {
            String joinedParams = String.join(",", unknownParams);
            throw new ResolveEndpointFailedException(uri, "There are parameters that couldn't be set on the endpoint." +
                    " Check the uri if the parameters are spelt correctly and that they are properties of the endpoint." +
                    " Unknown parameters=[" + joinedParams + "]");
        }
    }
}
