package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DesignOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer cartId;
    private Integer designId;
    private Integer quantity;

    
    public DesignOrder() {}
    public DesignOrder(Integer cartId, Integer designId, Integer quantity) {
    	this.cartId = cartId;
    	this.designId = designId;
    	this.quantity = quantity;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public Integer getDesignId() {
		return designId;
	}

	public void setDesignId(Integer designId) {
		this.designId = designId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

	

    

    
}