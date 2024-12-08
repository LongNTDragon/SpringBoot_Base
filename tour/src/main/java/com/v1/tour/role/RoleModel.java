package com.v1.tour.role;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.v1.tour.base.BaseModel;
import com.v1.tour.enums.EnumRoleName;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "role")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleModel extends BaseModel {
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    private EnumRoleName name;
}
