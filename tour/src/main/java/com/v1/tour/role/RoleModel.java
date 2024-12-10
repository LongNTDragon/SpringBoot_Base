package com.v1.tour.role;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.v1.tour.base.BaseDto;
import com.v1.tour.base.BaseModel;
import com.v1.tour.enums.EnumRoleName;
import com.v1.tour.role.dto.RoleDto;

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

    @Override
    public void updateByDto(BaseDto baseDto) {
        var roleDto = (RoleDto) baseDto;

        if (roleDto.getName() != null)
            this.setName(roleDto.getName());
    }
}
