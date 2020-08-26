package com.cp.k8.ecom.customer.controller;

import com.cp.k8.ecom.customer.domain.Address;
import com.cp.k8.ecom.customer.domain.Customer;
import com.cp.k8.ecom.customer.dto.AddressDTO;
import com.cp.k8.ecom.customer.dto.CustomerDTO;
import com.cp.k8.ecom.customer.dto.ServiceResponseDTO;
import com.cp.k8.ecom.customer.repository.AddressRepository;
import com.cp.k8.ecom.customer.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@RestController
@CommonsLog
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/amiconnected")
    public String amIconnected() {
        return "amiconnected : yes";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ServiceResponseDTO registerCustomer(@RequestBody CustomerDTO customerDTO, HttpServletRequest request) {
        ServiceResponseDTO response = new ServiceResponseDTO();
        log.info("begin register customer");
        response.setStatus("success");
        response.setMessage("");

        Address address = convertAddressDTOToAddress(customerDTO.getAddress());
        address.setCreatedDate(Calendar.getInstance().getTime());
        address.setUpdatedDate(Calendar.getInstance().getTime());
        Customer customer = convertCustomerDTOToCustomer(customerDTO);
        customer.setCreatedDate(Calendar.getInstance().getTime());
        customer.setUpdatedDate(Calendar.getInstance().getTime());

        Address savedAddress = addressRepository.save(address);
        customer.setAddress(savedAddress);

        Customer savedCustomer = customerRepository.save(customer);
        response.setBody(convertCustomerToCustomerDTO(savedCustomer));

        log.info("finished registering customer with email : " + customer.getEmail() + " . Saved Customer Id is :" + savedCustomer.getId());

        return response;
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO) {
        return Customer.builder().dateOfBirth(customerDTO.getDateOfBirth())
                .email(customerDTO.getEmail())
                .mobile(customerDTO.getMobile())
                .dateOfBirth(customerDTO.getDateOfBirth())
                .name(customerDTO.getName()).build();
    }

    private Address convertAddressDTOToAddress(AddressDTO addressDTO) {
        return Address.builder().address(addressDTO.getAddress())
                .type(addressDTO.getType()).build();
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer) {
        AddressDTO addressDTO = AddressDTO.builder().id(String.valueOf(customer.getAddress().getId()))
                .address(customer.getAddress().getAddress())
                .type(customer.getAddress().getType()).build();

        CustomerDTO customerDTO = CustomerDTO.builder().id(String.valueOf(customer.getId()))
                .name(customer.getName())
                .email(customer.getEmail())
                .mobile(customer.getMobile())
                .dateOfBirth(customer.getDateOfBirth())
                .address(addressDTO).build();

        return customerDTO;

    }
}
