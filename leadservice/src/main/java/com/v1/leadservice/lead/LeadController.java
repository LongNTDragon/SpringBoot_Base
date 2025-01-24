package com.v1.leadservice.lead;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.v1.leadservice.base.BaseController;
import com.v1.leadservice.base.ResponseObject;
import com.v1.leadservice.classes.User;
import com.v1.leadservice.lead.dto.LeadDto;
import com.v1.leadservice.utils.Constants.UrlPath;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = UrlPath.LEAD)
@RequiredArgsConstructor
public class LeadController extends BaseController {
    private final LeadService service;

    @GetMapping("{id}")
    public ResponseEntity<ResponseObject> getById(@PathVariable String id) {
        return super.onSuccess(service.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<ResponseObject> create(@Valid @RequestBody LeadDto leadDto) {
        var user = super.getUserDetailsImpl();

        leadDto.setUserCreate(User.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .build());

        return super.onSuccess(service.create(leadDto));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable String id, @RequestBody LeadDto leadDto) {
        return super.onSuccess(service.updateById(id, leadDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable String id) {
        return super.onSuccess(service.deleteById(id));
    }
}
