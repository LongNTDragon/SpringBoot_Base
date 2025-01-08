package com.v1.leadservice.lead;

import org.springframework.data.mongodb.core.mapping.Document;

import com.v1.leadservice.base.BaseDto;
import com.v1.leadservice.base.BaseModel;
import com.v1.leadservice.lead.dto.LeadDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "lead")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeadModel extends BaseModel {
    private String name;
    private String email;
    private String phone;
    private String requirement;

    @Override
    public void updateByDto(BaseDto baseDto) {
        var leadDto = (LeadDto) baseDto;
        if (leadDto.getName() != null)
            this.setName(leadDto.getName());
        if (leadDto.getEmail() != null)
            this.setEmail(leadDto.getEmail());
        if (leadDto.getPhone() != null)
            this.setPhone(leadDto.getPhone());
        if (leadDto.getRequirement() != null)
            this.setRequirement(leadDto.getRequirement());
    }
}
