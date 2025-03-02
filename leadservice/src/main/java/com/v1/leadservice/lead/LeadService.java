package com.v1.leadservice.lead;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.v1.leadservice.base.BaseService;
import com.v1.leadservice.base.Repositories;
import com.v1.leadservice.classes.User;
import com.v1.leadservice.lead.dto.LeadDto;

@Service
public class LeadService extends BaseService<LeadModel, LeadRepository> {
    public LeadService(Repositories repositories) {
        super(repositories.leadRepository);
    }

    public LeadModel create(LeadDto leadDto) {
        return super.create(leadDto, LeadModel.class);
    }

    public void updateUserInfoInAllLead(User user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userCreate._id").is(user.getId()));

        Update update = new Update();
        update
                .set("userCreate.username", user.getUsername())
                .set("userCreate.email", user.getEmail());

        mongoTemplate.updateMulti(query, update, LeadModel.class);
    }
}
