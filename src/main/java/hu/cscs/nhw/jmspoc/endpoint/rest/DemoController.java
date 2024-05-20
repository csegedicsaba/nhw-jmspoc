package hu.cscs.nhw.jmspoc.endpoint.rest;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.cscs.nhw.jmspoc.service.JmsSender;
import io.micrometer.core.annotation.Timed;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DemoController {

    private final JmsTemplate jmsTemplate;
    private final JmsSender jmsSender;

    @GetMapping("send")
    //@Timed
    String send() {
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                jmsTemplate.convertAndSend("DEV.QUEUE.1", "Hello World!");
            }
            log.info("Time: " + (System.currentTimeMillis() - start));
            return "OK";
        } catch (JmsException ex) {
            log.error("Error in sending: ", ex);
            return "FAIL";
        }
    }

    @GetMapping("send-async")
    //@Timed
    String sendAsync() {
        for (int i = 0; i < 16; i++) {
            jmsSender.send();
        }
        return "OK";
    }

    @PostConstruct
    public void init() {
        // send();
    }
}
