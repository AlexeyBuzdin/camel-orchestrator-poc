package lv.ctco.camelpoc.capitalize;

import org.springframework.context.annotation.*;
import org.springframework.context.support.*;

public class CapitalizeWorker {

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(CapitalizeConfiguration.class);
// TODO: Should close connection
//        ctx.destroy();
    }
}
