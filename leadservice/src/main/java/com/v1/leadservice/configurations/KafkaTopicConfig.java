package com.v1.leadservice.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@ConfigurationProperties(prefix = "spring.kafka.topics")
@Component
@Slf4j
@Setter
@Getter
public class KafkaTopicConfig {
    private TopicConfig userUpdate;

    @Data
    public static class TopicConfig {
        private String name;
    }
}
