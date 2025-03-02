package com.v1.leadservice.kafka;

import org.modelmapper.ModelMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.v1.leadservice.classes.User;
import com.v1.leadservice.lead.LeadService;

import eventdto.UserUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class LeadConsumer {
    private final ModelMapper mapper;
    private final LeadService leadService;

    @KafkaListener(topics = "#{kafkaTopicConfig.userUpdate.name}")
    public void listenUpdateUser(UserUpdateEvent userUpdateEvent) {
        var user = mapper.map(userUpdateEvent, User.class);
        leadService.updateUserInfoInAllLead(user);
    }
}
