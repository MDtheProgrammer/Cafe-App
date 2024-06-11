package com.inn.cafe.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.inn.cafe.dao.BillDAO;
import com.inn.cafe.dao.CategoryDAO;
import com.inn.cafe.dao.ProductDAO;
import com.inn.cafe.service.DashboardService;


@Service
public class DashboardServiceImpl implements DashboardService{

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired 
    BillDAO billDAO;

    @Override
    public ResponseEntity<Map<String, Object>> getCount() {
        Map<String, Object> map = new HashMap<>();
        map.put("category", categoryDAO.count());
        map.put("product", productDAO.count());
        map.put("bill", billDAO.count());
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

}
