package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

	
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private CartOrderRepository cartOrderRepository;
    

    @PostMapping("/add")
    public String addCustomer(@RequestParam(value = "fName") String first, @RequestParam(value = "lName") String last) {
        Customer customer = new Customer();
        customer.setFirstName(first);
        customer.setLastName(last);
        customerRepository.save(customer);
        return "success";
    }
    @GetMapping("/signup")
    public String signupPage(Model model) {
    	model.addAttribute("customer", new Customer());
    	return "signup";
    }
    @PostMapping("/process_register")
    public String processRegister(Customer user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        try {
        	customerRepository.save(user);
        	Customer temp = customerRepository.findCustomerByEmail(user.getEmail());
        	CartOrder cO = new CartOrder();
        	cO.setIsActive(1);
        	cO.setUserId(temp.getId());
        	cartOrderRepository.save(cO);
        }
        catch (Exception s){
        	return "redirect:/signup?error";
        }
        return "/login";
    }
    
    @RequestMapping("/editor")
    public String editorPage() {
    	return "editor";
    }
    
    @RequestMapping("/design")
    public String designPage() {
    	return "design";
    }
    
    @RequestMapping("/profile")
    public String profilePage() {
    	return "profile";
    }
    
    @RequestMapping("/cart")
    public String cartPage() {
    	return "cart";
    }
    
    @RequestMapping("/orders")
    public String orderPage() {
    	return "orders";
    }
    
    /*
    @GetMapping("/list")
    public Iterable<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Integer id) {
        return customerRepository.findCustomerById(id);
    }
    */

}