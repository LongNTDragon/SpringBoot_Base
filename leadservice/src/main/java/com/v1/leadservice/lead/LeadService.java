package com.v1.leadservice.lead;

import org.springframework.stereotype.Service;

import com.v1.leadservice.base.BaseService;
import com.v1.leadservice.base.Repositories;
import com.v1.leadservice.lead.dto.LeadDto;

@Service
public class LeadService extends BaseService<LeadModel, LeadRepository> {
    public LeadService(Repositories repositories) {
        super(repositories.leadRepository);
    }

    public LeadModel create(LeadDto leadDto) {
        return super.create(leadDto, LeadModel.class);
    }
}
