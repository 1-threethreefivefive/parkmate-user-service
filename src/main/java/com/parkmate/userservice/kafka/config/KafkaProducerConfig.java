package com.parkmate.userservice.kafka.config;

import com.parkmate.userservice.kafka.event.CreateReviewJoinUserEvent;
import com.parkmate.userservice.kafka.event.UpdateUserProfileEvent;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    private Map<String, Object> commonProducerConfigs() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:10000,localhost:10001,localhost:10002");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
        return configProps;
    }

    @Bean
    public ProducerFactory<String, CreateReviewJoinUserEvent> createReviewJoinUserProducerFactory() {
        return new DefaultKafkaProducerFactory<>(commonProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, CreateReviewJoinUserEvent> createReviewJoinUserKafkaTemplate() {
        return new KafkaTemplate<>(createReviewJoinUserProducerFactory());
    }

    @Bean
    public ProducerFactory<String, UpdateUserProfileEvent> updateUserProfileProducerFactory() {
        return new DefaultKafkaProducerFactory<>(commonProducerConfigs());
    }

    @Bean
    public KafkaTemplate<String, UpdateUserProfileEvent> updateUserProfileKafkaTemplate() {
        return new KafkaTemplate<>(updateUserProfileProducerFactory());
    }
}
