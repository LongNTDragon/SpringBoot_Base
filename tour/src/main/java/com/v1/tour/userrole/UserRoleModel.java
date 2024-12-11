package com.v1.tour.userrole;

import java.util.UUID;

import com.v1.tour.base.BaseModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "user_role")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRoleModel extends BaseModel {
    private UUID userId;
    private UUID roleId;
}
