package lv.ctco.camelpoc;

import org.apache.camel.builder.*;
import org.springframework.stereotype.*;

@Component
public class OrchestratorRoutes extends RouteBuilder {

    public static final String RABBITMQ_URL = "rabbitmq://192.168.99.100:32771";

    @Override
    public void configure() throws Exception {
            from("timer://simple?period=10000").setBody().simple("foo")
                    .to(RABBITMQ_URL + "/capitalize?" +
                            "autoDelete=false&" +
                            "exchangeType=topic&" +
                            "routingKey=capitalize.in");

            from(RABBITMQ_URL + "/capitalize?" +
                    "autoDelete=false&" +
                    "exchangeType=topic&" +
                    "routingKey=capitalize.out")
                    .log("Processing ${body}").to("stream:out");
    }
}
