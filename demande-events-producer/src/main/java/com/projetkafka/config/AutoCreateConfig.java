package com.projetkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AutoCreateConfig {

    @Bean
    public NewTopic DemandeEvent(){

        return TopicBuilder.name("demande-events")
                .partitions(3)
                .replicas(3)
                .build();
    }

}
