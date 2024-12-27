package com.v1.tour.user.dao;

import java.util.List;

import com.v1.tour.base.BaseModel;
import com.v1.tour.enums.EnumRoleName;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "account")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDao extends BaseModel {
    private String username;
    private String email;
    private String phone;
    private List<EnumRoleName> roles;
}
