package lv.ctco.camelpoc;

import lv.ctco.camelpoc.camel.*;
import org.springframework.stereotype.*;

@Component
public class OrchestratorRoutes extends AmqpRouteBuilder {

    @Override
    public void configure() throws Exception {
        camel(from("timer://simple?period=10000").setBody().simple("foo"))
                .to("capitalize", "in");

        from("capitalize", "out").to("truncate", "in");
        from("truncate", "out").camel().log("Finished ${body}");
    }
}
