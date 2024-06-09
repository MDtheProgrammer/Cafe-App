package com.inn.cafe.utils;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CafeUtils {

    private CafeUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseStatus, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\"" + responseStatus + "\"}", httpStatus); 
    }  

    public static String getUUID(){
        Date date = new Date();
        long time = date.getTime();
        return "BILL- " + time;
    }
}
