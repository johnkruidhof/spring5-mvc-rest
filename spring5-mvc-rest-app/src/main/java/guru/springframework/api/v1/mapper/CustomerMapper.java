package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

//@Mapper
//@Mapper(uses = {CustomerUrlMapper.class})
//@DecoratedWith(CustomerMapperDecorator.class)
@Mapper(imports = ShopEntityURLFormat.class)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    String shopEntityName = "customers";

    @Mapping(target = "customerUrl", expression = "java( ShopEntityURLFormat.toShopEntityURL(shopEntityName, customer.getId()) )")
    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
