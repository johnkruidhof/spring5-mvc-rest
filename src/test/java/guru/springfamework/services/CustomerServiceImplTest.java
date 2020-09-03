package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CustomerServiceImplTest {

    public static final Long ID = 2L;
    public static final String NAME = "Jimmy";
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }

    @Test
    public void getAllCustomers() throws Exception {
        // given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());
        given(customerRepository.findAll()).willReturn(customers);

        // when
        List<CustomerDTO> result = customerService.getAllCustomers();

        // then
        assertEquals(3, result.size());
    }

    @Test
    public void getCustomerById() throws Exception {
        // given
        Customer customer = Customer.builder().id(ID).firstname(NAME).build();
        given(customerRepository.findById(anyLong())).willReturn(Optional.of(customer));

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

        Customer savedCustomer = Customer.builder().id(ID).firstname(NAME).lastname(NAME).build();

        given(customerRepository.save(any(Customer.class))).willReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customer/2", savedDto.getCustomerUrl());
    }

    @Test
    public void saveCustomerByDTO() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");
        customerDTO.setLastname(NAME);
        Customer savedCustomer = Customer.builder().id(ID).firstname("Jim").lastname(NAME).build();

        given(customerRepository.save(any(Customer.class))).willReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.saveCustomerByDTO(2L, customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customer/2", savedDto.getCustomerUrl());
    }

    @Test
    public void deleteCustomerById() throws Exception {

        Long id = 1L;

        customerService.deleteCustomerById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }

}
