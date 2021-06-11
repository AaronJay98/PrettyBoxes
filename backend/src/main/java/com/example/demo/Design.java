package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

//import com.mysql.cj.jdbc.Blob;

@Entity
public class Design {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Lob
    private byte[] image;
    
    private Integer userId;

    public Design() {}
    public Design(Integer userId, byte[] bytes) {
		// TODO Auto-generated constructor stub
    	this.userId = userId;
    	this.image = bytes;
	}
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer id) {
        this.userId = id;
    }

    public byte[] getImage() {
    	return this.image;
    }
    
    public void setImage(byte[] image) {
    	this.image = image;
    }
    
}