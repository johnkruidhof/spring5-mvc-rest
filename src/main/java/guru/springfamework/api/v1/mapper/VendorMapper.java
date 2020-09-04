package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(imports = ShopEntityURLFormat.class)
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
    String shopEntityName = "vendors";

    @Mapping(target = "vendorUrl", expression = "java( ShopEntityURLFormat.toShopEntityURL(shopEntityName, vendor.getId()) )")
    VendorDTO vendorToVendorDto(Vendor vendor);

    Vendor vendorDtoToVendor(VendorDTO vendorDTO);
}
