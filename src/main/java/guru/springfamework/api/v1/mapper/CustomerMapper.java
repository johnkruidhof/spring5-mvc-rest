package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
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
