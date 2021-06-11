package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.awt.Image;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@CrossOrigin
@RestController
public class RestfulController {
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private DesignRepository designRepository;
    
    @Autowired
    private DesignOrderRepository designOrderRepository;
    
    @Autowired
    private CartOrderRepository cartOrderRepository;
    

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customer/id={id}")
    public Customer findCustomerById(@PathVariable Integer id) {
        return customerRepository.findCustomerById(id);
    }
    
    @GetMapping("/customer/{email}")
    public Customer getCompanyById(@PathVariable(value = "email") String email)
    {
        return customerRepository.findCustomerByEmail(email);
    }
    
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Principal principal) {
    	if (principal == null) {
    		return "no user";
    	}
    	return principal.getName();
    }
    
    
    @PostMapping("/design/save")
    public Design storeFile(@RequestParam("imageBlob") MultipartFile file, @RequestParam("userId") Integer userId) throws IOException {
        Design designImage = new Design(userId, file.getBytes());
        return designRepository.save(designImage);
    }
    
    @GetMapping("/design/list")
    public List<Design> getDesigns(){
        return designRepository.findAll();
    }
    
    @GetMapping("/design/id={id}")
    public Design getDesign(@PathVariable Integer id) throws Exception{
    	Design d = designRepository.findDesignById(id);
    	if (d == null) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Design does not exist");
    	}
        return d;
    }
    @DeleteMapping("/design/id={id}")
    @Transactional
    public long deleteDesign(@PathVariable Integer id) {
    	return designRepository.deleteDesignById(id);
    }
    
    @PostMapping("/order/addToCart")
    public DesignOrder addToCart(@RequestParam("designId") Integer designId, @RequestParam("quantity") Integer quantity, @RequestParam("cartId") Integer cartId) throws IOException {
    	DesignOrder o = new DesignOrder(cartId, designId, quantity);
        return designOrderRepository.save(o);
    }
   
    @GetMapping("/order/list/cartId={id}")
    public List<DesignOrder> getOrders(@PathVariable Integer id){
    	List<DesignOrder> orders = designOrderRepository.findDesignOrdersByCartId(id);
        return orders;
    }
    
    @DeleteMapping("/order/deleteId={id}")
    @Transactional
    public long deleteOrder(@PathVariable Integer id) {
    	return designOrderRepository.deleteDesignOrderById(id);
    }
    
    @GetMapping("/cart/id={id}")
    public List<CartOrder> getCarts(@PathVariable Integer id){
    	List<CartOrder> carts = cartOrderRepository.findCartOrderByUserId(id);
    	if (carts == null) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
    	}
    	return carts;
    }
    
    @GetMapping("/cart/getActiveCart/id={id}")
    public CartOrder getActiveCart(@PathVariable Integer id){
    	List<CartOrder> carts = cartOrderRepository.findCartOrderByUserId(id);
    	for (CartOrder c: carts) {
    		if (c.getIsActive() == 1) {
    			return c;
    		}
    	}
    	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
    }
    
    @Transactional
    @PutMapping("/cart/checkout/id={id}")
    public CartOrder checkout(@PathVariable Integer id){
    	List<CartOrder> carts = cartOrderRepository.findCartOrderByUserId(id);
    	Integer cartId = -1;
    	for (CartOrder c: carts) {
    		if (c.getIsActive() == 1) {
    			cartId = c.getId();
    		}
    	}
    	if (cartId == -1) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User does not exist");
    	}
    	CartOrder cO = new CartOrder();
    	cO.setIsActive(1);
    	cO.setUserId(id);
    	cartOrderRepository.save(cO);
    	cartOrderRepository.updateCartOrderById(cartId);
    	return cartOrderRepository.findCartOrderById(cartId);
    }
    
}

