package hu.cscs.nhw.jmspoc.endpoint.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JMSListener {

    @JmsListener(destination = "DEV.QUEUE.1")
    @Transactional(rollbackFor = Exception.class)
    @Timed
    public void process(final String message) {
        // log.info(message);

    }
}
