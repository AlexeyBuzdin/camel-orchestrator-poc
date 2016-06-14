package lv.ctco.camelpoc;

import org.apache.camel.*;
import org.apache.camel.component.rabbitmq.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class OrchestratorController {

    @EndpointInject(uri = "direct:startPipeline")
    ProducerTemplate producerTemplate;

    @RequestMapping(path = "/send", method = RequestMethod.GET)
    public String productsList(String message) {
        producerTemplate.sendBodyAndHeader(message, RabbitMQConstants.CORRELATIONID, UUID.randomUUID().toString());
        return message;
    }
}
