package com.inn.cafe.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.cafe.JWT.JWTAuthenticationFilter;
import com.inn.cafe.POJO.Bill;
import com.inn.cafe.constants.CafeConstants;
import com.inn.cafe.dao.BillDAO;
import com.inn.cafe.service.BillService;
import com.inn.cafe.utils.CafeUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BillServiceImpl implements BillService{

    @Autowired
    JWTAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    BillDAO billDAO;

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        log.info("Inside generateReport");

        try{
            String fileName;
            if(validateRequestMap(requestMap)){
                if(requestMap.containsKey("isGenerate") && !(Boolean)requestMap.get("isGenerate")){
                    fileName = (String) requestMap.get("uuid");
                }else{
                    fileName = CafeUtils.getUUID();
                    requestMap.put("uuid", fileName);
                    insertBill(requestMap);

                }
            }
            return CafeUtils.getResponseEntity("Required data not found", HttpStatus.BAD_REQUEST);

        }catch(Exception ex){

        }

        return CafeUtils.getResponseEntity(CafeConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void insertBill(Map<String, Object> requestMap) {
        try{
            Bill bill = new Bill();
            bill.setUuid((String) requestMap.get("uuid"));
            bill.setName((String) requestMap.get("name"));
            bill.setEmail((String) requestMap.get("email"));
            bill.setContactNumber((String) requestMap.get("contactNumber"));
            bill.setPaymentMethod((String) requestMap.get("paymentMethod"));
            bill.setTotal(Integer.parseInt((String) requestMap.get("totalAmount")));
            bill.setProductDetail((String) requestMap.get("productDetails"));
            bill.setCreatedBy(jwtAuthenticationFilter.getCurrentUser());
            billDAO.save(bill);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private boolean validateRequestMap(Map<String, Object> requestMap){


        return requestMap.containsKey("name") && requestMap.containsKey("contactNumber") &&
        requestMap.containsKey("email") && requestMap.containsKey("paymentMethod") && requestMap.containsKey("productDetails") &&
        requestMap.containsKey("totalAmount");
    }

}
