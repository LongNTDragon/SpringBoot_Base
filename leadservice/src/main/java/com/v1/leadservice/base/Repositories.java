package com.v1.leadservice.base;

import org.springframework.stereotype.Component;

import com.v1.leadservice.lead.LeadRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Repositories {
    public final LeadRepository leadRepository;
}
