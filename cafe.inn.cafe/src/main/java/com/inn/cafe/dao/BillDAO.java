package com.inn.cafe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inn.cafe.POJO.Bill;

public interface BillDAO extends JpaRepository<Bill, Integer>{
    
}
