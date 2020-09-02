package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class CustomerMapperDecorator implements CustomerMapper {

    @Autowired
    @Qualifier("delegate")
    private CustomerMapper delegate;

    @Override
    public CustomerDTO customerToCustomerDTO(Customer customer) {
        CustomerDTO dto = delegate.customerToCustomerDTO(customer);
        dto.setCustomerUrl( "/shop/customers/" + customer.getId());
        return dto;
    }
}
