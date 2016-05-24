package lv.ctco.camelpoc.orchestrator;

import org.apache.camel.*;
import org.apache.camel.builder.*;
import org.apache.camel.impl.*;

public class Orchestrator {

    public static final String RABBITMQ_URL = "rabbitmq://192.168.99.100:32771";

    public static void main(String[] args) throws Exception {

        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("timer://simple?period=1000").setBody().simple("foo")
                    .to(RABBITMQ_URL + "/capitalize?" +
                            "autoDelete=false&" +
                            "exchangeType=topic&" +
                            "routingKey=capitalize.in");

                from(RABBITMQ_URL + "/capitalize?" +
                                     "autoDelete=false&" +
                                     "exchangeType=topic&" +
                                     "routingKey=capitalize.out")
                        .to("stream:out");
            }
        });
        context.setTracing(true);
        context.start();

    }
}
