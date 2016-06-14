package lv.ctco.camelpoc;

import org.apache.camel.builder.*;
import org.springframework.stereotype.*;

@Component
public class OrchestratorRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:startPipeline")
                .log("Received ${header.rabbitmq.CORRELATIONID}: ${body}")
                .to("mq:truncate?direction=in")
                .id("truncate");

        from("mq:truncate?direction=out")
                .log("Received ${header.rabbitmq.CORRELATIONID}: ${body}")
                .to("mq:capitalize?direction=in")
                .id("capitalize");

        from("mq:capitalize?direction=out")
                .log("Finished ${header.rabbitmq.CORRELATIONID}: ${body}");
    }
}
