package com.amolsoftwares.locationmanagementapi.validation;

import com.amolsoftwares.locationmanagementapi.constant.ErrorType;
import com.amolsoftwares.locationmanagementapi.exception.ErrorModel;
import com.amolsoftwares.locationmanagementapi.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserValidator {

    public List<ErrorModel> validateRequest(UserModel userModel){

        List<ErrorModel> errorModelList = new ArrayList<>();
        ErrorModel errorModel = new ErrorModel();

        if(null != userModel && userModel.getEmail() == null){
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("Email cannot be empty!");
            errorModelList.add(errorModel);
        }

        if(null != userModel && userModel.getPassword() == null){
            errorModel.setCode(ErrorType.NOT_EMPTY.toString());
            errorModel.setMessage("Password cannot be empty!");
            errorModelList.add(errorModel);
        }

        return errorModelList;
    }
}
