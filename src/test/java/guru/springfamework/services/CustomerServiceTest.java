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

public class CustomerServiceTest {

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
        Customer customer = Customer.builder().id(ID).firstName(NAME).build();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(ID);
        customerDTO.setFirstName(NAME);
        given(customerRepository.findById(anyLong())).willReturn(Optional.of(customer));
        given(customerMapper.customerToCustomerDTO(any())).willReturn(customerDTO);

        // when
        CustomerDTO result = customerService.getCustomerById(ID);

        // then
        assertNotNull(result);
        assertEquals(ID, result.getId());
        assertEquals(NAME, result.getFirstName());
    }
}
