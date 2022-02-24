package com.amolsoftwares.locationmanagementapi.service;

import com.amolsoftwares.locationmanagementapi.exception.BusinessException;
import com.amolsoftwares.locationmanagementapi.model.UserModel;

public interface UserService {
    public boolean login(UserModel userModel) throws BusinessException;
    public Long register(UserModel userModel) throws BusinessException;
}
