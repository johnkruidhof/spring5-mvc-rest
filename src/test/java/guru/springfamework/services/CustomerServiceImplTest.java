package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

public class CustomerServiceImplTest {

    public static final Long ID = 2L;
    public static final String NAME = "Jimmy";

    @InjectMocks
    CustomerServiceImpl customerService;

    @Mock
    CustomerMapper customerMapper;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void getAllCustomers() throws Exception {
        // given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
        CustomerDTO customerDTO = new CustomerDTO();
        given(customerRepository.findAll()).willReturn(customers);
        given(customerMapper.customerToCustomerDTO(any())).willReturn(customerDTO);

        // when
        List<CustomerDTO> result = customerService.getAllCustomers();

        // then
        assertEquals(3, result.size());
    }

    @Test
    public void getCustomerById() throws Exception {
        // given
        Customer customer = Customer.builder().id(ID).firstname(NAME).build();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(NAME);
        given(customerRepository.findById(anyLong())).willReturn(Optional.of(customer));
        given(customerMapper.customerToCustomerDTO(any())).willReturn(customerDTO);

        // when
        CustomerDTO result = customerService.getCustomerById(ID);

        // then
        assertNotNull(result);
        assertEquals(NAME, result.getFirstname());
    }

    @Test
    public void createNewCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(NAME);
        customerDTO.setLastname(NAME);

        CustomerDTO customerDTO2 = new CustomerDTO();
        customerDTO2.setFirstname(NAME);
        customerDTO2.setLastname(NAME);
        customerDTO2.setCustomerUrl("/api/v1/customer/2");

        Customer customer = Customer.builder().firstname(NAME).lastname(NAME).build();
        Customer savedCustomer = Customer.builder().id(ID).firstname(NAME).lastname(NAME).build();

        when(customerMapper.customerDtoToCustomer(any(CustomerDTO.class))).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
        given(customerMapper.customerToCustomerDTO(any(Customer.class))).willReturn(customerDTO2);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customer/2", savedDto.getCustomerUrl());
    }
}
