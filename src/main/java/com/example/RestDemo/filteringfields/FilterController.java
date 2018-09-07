package com.example.RestDemo.filteringfields;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

	@GetMapping("/filter")
	public FilteringBean getFilteredBean(){
		return new FilteringBean("name", "DOB", "password");
	}
}
