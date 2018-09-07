package com.example.RestDemo.filteringfields;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class DynamicFilterController {

	
	//filtering all except field1 and field2
	@GetMapping("/filter-bean1")
	public MappingJacksonValue getFilteredFields1(){
		
		DynamicFilterBean someBean = new DynamicFilterBean("rahul1", "rahul2", "rahul3");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", filter);
		
		MappingJacksonValue mapping=new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		return mapping;
	}
	
	
	//passing only field2 and field3
	@GetMapping("/filtered-bean-list")
	public MappingJacksonValue getFilteredFields2(){
		List<Object> list = Arrays.asList(new DynamicFilterBean("value1", "value2", "value3"),
										  new DynamicFilterBean("value1", "value2", "value3"));
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");
		
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("myFilter", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filterProvider);
		return mapping;
	}
	
}
