package lv.ctco.camelpoc;

import lv.ctco.camelpoc.camel.*;
import org.springframework.stereotype.*;

@Component
public class OrchestratorRoutes extends AmqpRouteBuilder {

    @Override
    public void configure() throws Exception {
        camel(from("direct:dc")).to("truncate", "in");
        from("truncate", "out").to("capitalize", "in");
        from("capitalize", "out").camel().log("Finished ${body}");
    }
}
