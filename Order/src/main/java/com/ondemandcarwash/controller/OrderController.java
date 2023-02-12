package com.ondemandcarwash.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ondemandcarwash.model.Order;
import com.ondemandcarwash.model.WashPacks;
import com.ondemandcarwash.repository.OrderRepository;
import com.ondemandcarwash.repository.WashPackRepository;
import com.ondemandcarwash.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private WashPackRepository washpackrepository;

	// Creating/Adding Order
	@PostMapping("/addorder")
	public String saveOrder(@RequestBody Order order) {
		orderService.addOrder(order);
		return "Order is Placed with Washer and will be Proceesed soon " + order;
	}

	// Reading all washer
	@GetMapping("/allorders")
	public List<Order> getOrder() {
		return orderRepository.findAll();
	}

	// Deleting order by Id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable int id)
	{
		
		 
			orderService.deleteById(id);
			return new ResponseEntity<Object>("Order deleted with id" + id, HttpStatus.OK);
		
	}
	// Reading Order by id
		@GetMapping("/orders/{id}")
		public Optional<Order> getCustomerById(@PathVariable int id)  {
			return orderRepository.findById(id);
					
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
		//adding washpack by admin
		@PostMapping("/addpack")
		public String savepack(@RequestBody WashPacks pack)
		{
		washpackrepository.save(pack);
		return " Pack saved successfully with id :"+pack.getId();
		}
}