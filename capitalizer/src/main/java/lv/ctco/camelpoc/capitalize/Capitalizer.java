package lv.ctco.camelpoc.capitalize;

import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.context.support.*;

@SpringBootApplication
public class Capitalizer {

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(CapitalizerConfiguration.class);
// TODO: Should close connection
//        ctx.destroy();
    }
}
