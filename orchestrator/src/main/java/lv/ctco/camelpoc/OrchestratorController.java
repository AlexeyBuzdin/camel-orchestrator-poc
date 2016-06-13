package lv.ctco.camelpoc;

import org.apache.camel.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrchestratorController {

    @EndpointInject(uri = "direct:startPipeline")
    ProducerTemplate producerTemplate;

    @RequestMapping(path = "/send", method = RequestMethod.GET)
    public String productsList(String message) {
        producerTemplate.sendBody(message);
        return message;
    }
}
