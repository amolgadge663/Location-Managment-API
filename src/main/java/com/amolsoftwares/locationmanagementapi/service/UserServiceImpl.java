package com.amolsoftwares.locationmanagementapi.service;

import com.amolsoftwares.locationmanagementapi.constant.ErrorType;
import com.amolsoftwares.locationmanagementapi.converter.UserConverter;
import com.amolsoftwares.locationmanagementapi.entity.UserEntity;
import com.amolsoftwares.locationmanagementapi.exception.BusinessException;
import com.amolsoftwares.locationmanagementapi.exception.ErrorModel;
import com.amolsoftwares.locationmanagementapi.model.UserModel;
import com.amolsoftwares.locationmanagementapi.repository.UserEntityRepository;
import com.amolsoftwares.locationmanagementapi.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserValidator userValidator;

    @Override
    public boolean login(UserModel userModel) throws BusinessException {

        //to check empty email and password
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if (!CollectionUtils.isEmpty(errorModelList)){
            throw new BusinessException(errorModelList);
        }

        boolean result = false;

        UserEntity userEntity = userEntityRepository
                .findByEmailAndPassword(userModel.getEmail(), userModel.getPassword());

        if (userEntity == null){
            List<ErrorModel> errorList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.AUTH_INVALID_CREDENTIALS.toString());
            errorModel.setMessage("Incorrect Email and Password!!!");
            errorList.add(errorModel);
            throw new BusinessException(errorList);
        } else {
            result = true;
        }

        return result;
    }

    @Override
    public Long register(UserModel userModel) throws BusinessException {

        //to check empty email and password
        List<ErrorModel> errorModelList = userValidator.validateRequest(userModel);

        if (!CollectionUtils.isEmpty(errorModelList)){
            throw new BusinessException(errorModelList);
        }

        //check if user already exist
        UserEntity ue = userEntityRepository.findByEmail(userModel.getEmail());

        if (null != ue){
            List<ErrorModel> errorList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode(ErrorType.ALREADY_EXIST.toString());
            errorModel.setMessage("User with this email is already exist!!! Try another Email ID");
            errorList.add(errorModel);
            throw new BusinessException(errorList);
        }

        UserEntity userEntity = userConverter.convertModelToEntity(userModel);
        UserEntity userEntity1 = userEntityRepository.save(userEntity);
        return userEntity1.getId();
    }
}
