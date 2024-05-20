package com.inn.cafe.restImpl;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.inn.cafe.rest.UserRest;

@RestController
public class UserRestImpl implements UserRest{

    @Override
    public ResponseEntity<String> signup(Map<String, String> requestMap) {
       try{

       }catch(Exception ex){
        ex.printStackTrace();
       }
    }

}
