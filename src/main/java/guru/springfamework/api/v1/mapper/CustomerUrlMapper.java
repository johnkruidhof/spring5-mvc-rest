package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public abstract class CustomerUrlMapper {

    @AfterMapping
    protected void setUrl(Customer customer, @MappingTarget CustomerDTO result) {
        result.setCustomerUrl("/shop/customer/"+ customer.getId());
    }

    public abstract CustomerDTO customerToCustomerDTO(Customer customer);
}
