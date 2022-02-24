package com.amolsoftwares.locationmanagementapi.converter;

import com.amolsoftwares.locationmanagementapi.entity.UserEntity;
import com.amolsoftwares.locationmanagementapi.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertModelToEntity(UserModel userModel){

        UserEntity userEntity = new UserEntity();

        userEntity.setFullName(userModel.getFullName());
        userEntity.setEmail(userModel.getEmail());
        userEntity.setMobileNumber(userModel.getMobileNumber());
        userEntity.setPassword(userEntity.getPassword());

        return userEntity;
    }
}
