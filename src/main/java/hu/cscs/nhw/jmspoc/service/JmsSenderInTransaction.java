package hu.cscs.nhw.jmspoc.service;

import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor

public class JmsSenderInTransaction {

    private final JmsTemplate jmsTemplate;

    //@Transactional()
    public void send() {
        try {
            jmsTemplate.convertAndSend("DEV.QUEUE.1", "Hello World!");
        } catch (JmsException ex) {
            log.error("Error in sending: ", ex);
        }
    }
}
