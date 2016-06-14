package lv.ctco.camelpoc;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.*;
import org.springframework.beans.factory.annotation.*;

import java.nio.charset.*;

public class AppenderListener implements MessageListener {

    @Autowired
    RabbitTemplate appendTemplate;

    @Override
    public void onMessage(Message message) {
        try {
            String messageStr = new String(message.getBody(), StandardCharsets.UTF_8);
            System.out.println("Received: " + messageStr);
            String newString = "a" + messageStr;
            Message outMessage = new Message(newString.getBytes(), message.getMessageProperties());
            appendTemplate.send(outMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
