package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class VendorMapperTest {
    public static final String NAME = "Fun Fresh Foods Ltd.";
    public static final long ID = 1L;
    public static final String URL = "/shop/vendors/1";

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDto() throws Exception {
        //given
        Vendor vendor = Vendor.builder().id(ID).name(NAME).build();

        //when
        VendorDTO result = vendorMapper.vendorToVendorDto(vendor);

        //then
        assertEquals(NAME, result.getName());
        assertEquals(URL, result.getVendorUrl());
    }

    @Test
    public void customerDTOToCustomer() throws Exception {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        //when
        Vendor result = vendorMapper.vendorDtoToVendor(vendorDTO);

        //then
        assertEquals(NAME, result.getName());
    }
}
