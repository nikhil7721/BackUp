package com.ondemandcarwash.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.ondemandcarwash.models.Admin;
import com.ondemandcarwash.models.Customer;
import com.ondemandcarwash.models.Order;
import com.ondemandcarwash.models.Ratings;
import com.ondemandcarwash.models.WashPacks;
import com.ondemandcarwash.models.Washer;
import com.ondemandcarwash.models.adminAuthResponse;
import com.ondemandcarwash.repository.AdminRepository;
import com.ondemandcarwash.repository.RatingRepository;
import com.ondemandcarwash.repository.WashPackRepository;
import com.ondemandcarwash.service.AdminService;
import com.ondemandcarwash.service.RatingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin")

public class AdminController {
	
	@Autowired
	private WashPackRepository washpackrepository;


	@Autowired
	private AdminService adminService;
	
	@Autowired
	private RatingRepository ratingrepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private RestTemplate  restTemplate;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private AuthenticationManager  authenticationManager;
	
	
	
	@PostMapping("/addadmin")
	private ResponseEntity<?> saveAdmin(@RequestBody Admin admin){
		
		String email = admin.getaEmail();
		String password = admin.getaPassword();
		Admin admin1 = new Admin();
		admin1.setaEmail(email);
		admin1.setaPassword(password);
		try {
			adminRepository.save(admin);
			
		} catch (Exception e) {
			return ResponseEntity.ok(new adminAuthResponse("Error creating admin "+ email));
		}
		return ResponseEntity.ok(new adminAuthResponse("Successfully created admin "+ email));
		
		
	}
	//authenticating washer
	@PostMapping("/auth")
	private ResponseEntity<?> authAdmin(@RequestBody Admin admin){
		String email = admin.getaEmail();
		String password = admin.getaPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			
		} catch (Exception e) {
			
			return ResponseEntity.ok(new adminAuthResponse("Error during  admin Authentication"+ email));
		}
		return ResponseEntity.ok(new adminAuthResponse("Successfully Authenticated admin"+ email));
		
	}
	//Reading All admin 
	@GetMapping("/alladmins")
	public List<Admin> getallAdmins(){
		return adminRepository.findAll();	
	}
	
	//Reading Admin by ID
	@GetMapping("/alladmins/{id}")
	public Optional<Admin> getadminbyId(@PathVariable int id){
		return adminRepository.findById(id);
	}
	
	//Reading orders  By
	@GetMapping("/getallorders/{id}") 
	public Order getOrderById (@PathVariable("id") int id) 
	{
	  return restTemplate.getForObject("http://Order/order/orders/" +id , Order.class);
	}
	
	
	//Reading All orders 
	@GetMapping("/getallorders") 
	public String getallOrder() 
	{
	   return restTemplate.getForObject("http://Order/order/allorders", String.class);
	}
	
	
	//Updating Admin Data by Id
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateAdmin(@PathVariable int id, @RequestBody Admin admin)
	{
	adminRepository.save(admin);
	return new ResponseEntity<Object>("Admininfo updated successfully with id " +id, HttpStatus.OK);
	}

	// Deleting Admin Data by Id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteAdmin (@PathVariable int id)
	{
	adminService.deleteById(id);
	return new ResponseEntity<Object>("Admininfo deleted with id"+id,HttpStatus.OK);
	}
	
	
	//adding washpack by admin
	@PostMapping("/addpack")
	public String savepack(@RequestBody WashPacks pack)
	{
	washpackrepository.save(pack);
	return " Pack saved successfully with id :"+pack.getId();
	}
	
	
	//get all washpack by admin
	@GetMapping("/allpacks")
	public List<WashPacks> getpack()
	{
	return washpackrepository.findAll();
	}
	
	@DeleteMapping("/deletepack/{id}")
	public String deletepack(@PathVariable int id)
	{
	washpackrepository.deleteById(id);
	return "pack deleted with id "+id;
	}
	
	
	//Creating and adding ratings
	@PostMapping("/addrating")
	public String saveRating(@RequestBody Ratings rating)
	{
	ratingService.addRating(rating);
	return " Thanks for Your Valuable feedback" + rating;
	}


	//Reading all ratings
	@GetMapping("/allratings")
	public List<Ratings> getAllRating()
	{
	return ratingService.getAllRating();
	}


	//Read Rating By washerId
	@GetMapping("/ratings/{id}")
	public Optional<Ratings> getRatingById(@PathVariable int id)
	{
	return ratingrepository.findById(id);
	}


	// Deleting Review by wId
	@DeleteMapping("/deleterating/{id}")
	public ResponseEntity<Object> deleteRating(@PathVariable int id)
	{
	ratingService.deleteRatingById(id);
	return new ResponseEntity<Object>("Review is deleted with id" + id, HttpStatus.OK);
	}
	
	//Reading all Washer Details
	@GetMapping("/allwashers")
	public List<Washer> findAllWashers()
	{
	String baseurl="http://Washer/washer/allwashers";
	Washer[] allWashers=restTemplate.getForObject(baseurl, Washer[].class);
	return Arrays.asList(allWashers);
	}


	//Remove Washer By Id
	@DeleteMapping("/removewasher/{id}")
	public String removeWasher(@PathVariable("id") int id)
	{
	restTemplate.delete("http://Washer/washer/delete/" +id , String.class);
	return "Washer is successfully Removed " + id;
	}
	//reading all customer admin

	
	@GetMapping("/allcustomers")
	public List<Customer> findAllCustomers()
	{
	String baseurl="http://Customers/customer/allcustomers";
	Customer[] allcustomers=restTemplate.getForObject(baseurl, Customer[].class);
	return Arrays.asList(allcustomers);
	}

	
	
	//delete customer by admin through ID
	@DeleteMapping("/removecustomer/{id}")
	public String removeCustomer(@PathVariable("id") int id)
	{
	restTemplate.delete("http://Customers/customer/delete/" +id , String.class);
	return "Customer is successfully Removed " + id;
	}
	


	}




	
