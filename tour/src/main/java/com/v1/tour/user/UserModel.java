package com.v1.tour.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.v1.tour.base.BaseDto;
import com.v1.tour.base.BaseModel;
import com.v1.tour.user.dto.UserDto;

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

    @JsonIgnore
    private String password;

    @Override
    public void updateByDto(BaseDto baseDto) {
        var userDto = (UserDto) baseDto;

        if (userDto.getUsername() != null)
            this.setUsername(userDto.getUsername());
        if (userDto.getEmail() != null)
            this.setEmail(userDto.getEmail());
        if (userDto.getPhone() != null)
            this.setPhone(userDto.getPhone());
    }
}
