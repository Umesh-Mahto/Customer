package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Customer;
import com.luv2code.springboot.thymeleafdemo.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class customerController {

	private CustomerService customerService;
	public customerController(CustomerService theCustomerService){
		customerService = theCustomerService;
	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		// get employee from the database
		List<Customer> theCustomers = customerService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theCustomers);

		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){

		// create model attribute to bind form data

		Customer theCustomer = new Customer();
		theModel.addAttribute("employee", theCustomer);

		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel){

		// get the employee from the service
		Customer theCustomer = customerService.findById(theId);

		// set employee in the model to prepopulate the form
		theModel.addAttribute("employee", theCustomer);

		// send over to our form

		return "employees/employee-form";
	}




	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Customer theCustomer) {
		// save the employee
		customerService.save(theCustomer);
		// use the redirect to prevent duplicate submissions

		return "redirect:/employees/list";
	}

	@GetMapping("/delete")
	public  String delete(@RequestParam("employeeId")int theId){

		// delete the employee
		customerService.deleteById(theId);
		// return to the /employees/list
		return "redirect:/employees/list";
	}
}









