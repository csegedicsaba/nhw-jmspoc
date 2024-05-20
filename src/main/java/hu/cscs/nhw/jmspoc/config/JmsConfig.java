package hu.cscs.nhw.jmspoc.config;

import static org.springframework.jms.listener.DefaultMessageListenerContainer.CACHE_CONSUMER;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.destination.BeanFactoryDestinationResolver;

//@Configuration
public class JmsConfig {

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(CachingConnectionFactory connectionFactory, BeanFactory beanFactory) {

        // settings made based on https://bsnyderblog.blogspot.sk/2010/05/tuning-jms-message-consumption-in.html
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory() {
            @Override
            protected void initializeContainer(DefaultMessageListenerContainer container) {
                super.initializeContainer(container);
                container.setIdleConsumerLimit(5);
                container.setIdleTaskExecutionLimit(10);
            }
        };
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("10-50");
        factory.setCacheLevel(CACHE_CONSUMER);
        factory.setReceiveTimeout(5000L);
        factory.setDestinationResolver(new BeanFactoryDestinationResolver(beanFactory));
        return factory;
    }
}
