package hu.cscs.nhw.jmspoc.service;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.jms.Session;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class JmsSender {

    private final JmsTemplate jmsTemplate;
    private final JmsSenderInTransaction jmsSenderInTransaction;

    @Async("jmsSenderExecutor")
    public void send() {
        try {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                jmsSenderInTransaction.send();
            }
            log.info("Time: " + (System.currentTimeMillis() - start));
        } catch (JmsException ex) {
            log.error("Error in sending: ", ex);
        }
    }
}
