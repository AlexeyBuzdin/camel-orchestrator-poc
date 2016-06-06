package lv.ctco.camelpoc;

import org.apache.camel.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrchestratorController {

    @Autowired
    ProducerTemplate producerTemplate;

    @RequestMapping(path = "/send", method = RequestMethod.GET)
    public String productsList(String message) {
        producerTemplate.sendBody("direct:dc", message);
        return message;
    }
}
