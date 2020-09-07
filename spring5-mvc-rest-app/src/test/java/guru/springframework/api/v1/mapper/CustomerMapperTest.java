package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

//@ContextConfiguration(classes = CustomerMapperTest.SpringTestConfig.class)
//@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerMapperTest {

//    @Configuration
//    @ComponentScan(basePackageClasses = CustomerMapperTest.class)
//    public static class SpringTestConfig {
//    }

    public static final String FIRSTNAME = "Jimmy";
    public static final String LASTNAME = "Fallon";
    public static final long ID = 1L;
    public static final String URL = "/shop/customers/1";

//    @Autowired
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception {
        //given
        Customer customer = Customer.builder().id(ID).firstname(FIRSTNAME).lastname(LASTNAME).build();

        //when
        CustomerDTO result = customerMapper.customerToCustomerDTO(customer);

        //then
        assertEquals(FIRSTNAME, result.getFirstname());
        assertEquals(LASTNAME, result.getLastname());
        assertEquals(URL, result.getCustomerUrl());
    }

    @Test
    public void customerDTOToCustomer() throws Exception {
        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);

        //when
        Customer result = customerMapper.customerDtoToCustomer(customer);

        //then
        assertEquals(FIRSTNAME, result.getFirstname());
        assertEquals(LASTNAME, result.getLastname());
    }
}
