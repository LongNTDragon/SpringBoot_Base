package com.v1.tour.user;

import com.v1.tour.base.BaseModel;

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
public class UserModel extends BaseModel {
    private String username;
    private String email;
    private String phone;
    private String password;
}
