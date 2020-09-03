package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = CustomerMapperTest.SpringTestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerMapperTest {

    @Configuration
    @ComponentScan(basePackageClasses = CustomerMapperTest.class)
    public static class SpringTestConfig {
    }

    public static final String FIRSTNAME = "Jimmy";
    public static final String LASTNAME = "Fallon";
    public static final long ID = 1L;
    public static final String URL = "/shop/customers/1";

    @Autowired
    CustomerMapper customerMapper;

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
}
