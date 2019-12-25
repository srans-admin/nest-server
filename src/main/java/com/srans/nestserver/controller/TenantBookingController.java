package com.srans.nestserver.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.Tenant;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.TenantRepository;
import com.srans.nestserver.util.NSException;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class TenantBookingController {

	Logger logger = LoggerFactory.getLogger(TenantController.class);

	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private TenantBookRepository tenantBookRepository; 
/*
	
	@GetMapping("tenantbooking/hostelName")
	public List<String> findAll() {
		return tenantRepository.getAllHostelName();

	}

	@GetMapping("tenantbooking/hostelName/{hostelname}/bed")
	public List<Object[]> getNumOfFloor(@PathVariable(value = "hostelname") String hostelname) {
	
	
		Long HostelId=tenantRepository.getHostelId(hostelname);
		
		System.out.println(HostelId);

		return tenantRepository.getBedInfo(HostelId);

	}*/
	
	@PostMapping("/tenantbooking")
	@PreAuthorize("permitAll()")
	public Tenant bookTenant(@Valid @RequestBody Tenant tenant) throws NSException {
		
		logger.info("IN::POST::/tenantbooking::bookTenant::" + tenant);

		Tenant responsetenant=tenantRepository.save(tenant); 
 
		tenantBookRepository.save(tenant.getTenantBooking());
		 
		return responsetenant;
			
		}
	
	@GetMapping("/tenantbooking")
	public List<Tenant> getAllPosts() {
		return tenantRepository.findAll();
		
		
	}
	
	
   

}