package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DesignOrderRepository extends CrudRepository<DesignOrder, Integer> {
	DesignOrder findDesignOrderById(Integer id);
	List<DesignOrder> findDesignOrdersByCartId(Integer cartId);
	long deleteDesignOrderById(Integer id);
}