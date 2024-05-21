package com.inn.cafe.restImpl;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inn.cafe.constants.CafeConstants;
import com.inn.cafe.rest.UserRest;
import com.inn.cafe.service.UserService;
import com.inn.cafe.utils.CafeUtils;

@RestController
public class UserRestImpl implements UserRest{

    @Override
    public ResponseEntity<String> signup(Map<String, String> requestMap) {

        UserService userService;

       try{
        userService.signUp(requestMap);
       }catch(Exception ex){
        ex.printStackTrace();
       }

       return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
