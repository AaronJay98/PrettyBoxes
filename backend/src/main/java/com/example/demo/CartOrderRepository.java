package com.example.demo;

import java.util.*;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CartOrderRepository extends CrudRepository<CartOrder, Integer> {

    List<CartOrder> findCartOrderByUserId(Integer id);
    
    CartOrder findCartOrderById(Integer id);
    
    @Modifying(clearAutomatically = true)
    @Query("UPDATE CartOrder c SET c.isActive = 0 WHERE c.id = :id")
    void updateCartOrderById(@Param("id") Integer id);
}