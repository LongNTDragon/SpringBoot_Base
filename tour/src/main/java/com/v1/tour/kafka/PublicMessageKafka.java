package com.v1.tour.kafka;

import java.util.concurrent.CompletableFuture;

import org.modelmapper.ModelMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.v1.tour.configurations.KafkaTopicConfig;
import com.v1.tour.user.UserModel;

import eventdto.UserUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class PublicMessageKafka {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ModelMapper mapper;
    private final KafkaTopicConfig kafkaTopicConfig;

    public void publicUpdateUser(UserModel user) {
        var userUpdateEvent = mapper.map(user, UserUpdateEvent.class);
        var future = kafkaTemplate.send(kafkaTopicConfig.getUserUpdate().getName(), userUpdateEvent);
        handlePublicEvent(future);
    }

    public void handlePublicEvent(CompletableFuture<SendResult<String, Object>> future) {
        future.whenComplete((result, throwable) -> {
            if (throwable != null) {
                log.info("Unable to send message [" + result.getRecordMetadata().topic() + "] due to: "
                        + throwable.getMessage());
            } else {
                log.info("Sent message [" + result.getRecordMetadata().topic() + "] with offset: "
                        + result.getRecordMetadata().offset());
            }
        });
    }
}
