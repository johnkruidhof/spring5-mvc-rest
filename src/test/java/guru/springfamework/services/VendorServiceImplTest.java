package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
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

public class VendorServiceImplTest {

    public static final Long ID = 2L;
    public static final String NAME = "Home Fruits";
    public static final String BASEURL_ID2 = VendorServiceImpl.BASE_URL+"2";
    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorMapper, vendorRepository);
    }

    @Test
    public void getAllVendors() throws Exception {
        // given
        List<Vendor> vendors = Arrays.asList(new Vendor(), new Vendor(), new Vendor());
        given(vendorRepository.findAll()).willReturn(vendors);

        // when
        List<VendorDTO> result = vendorService.getAllVendors();

        // then
        assertEquals(3, result.size());
    }

    @Test
    public void getVendorById() throws Exception {
        // given
        Vendor vendor = Vendor.builder().id(ID).name(NAME).build();
        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        // when
        VendorDTO result = vendorService.getVendorById(ID);

        // then
        assertNotNull(result);
        assertEquals(NAME, result.getName());
    }

    @Test
    public void createNewVendor() throws Exception {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);

        Vendor savedVendor = Vendor.builder().id(ID).name(NAME).build();

        given(vendorRepository.save(any(Vendor.class))).willReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.createNewVendor(vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedDto.getName());
        assertEquals(BASEURL_ID2, savedDto.getVendorUrl());
    }

    @Test
    public void saveVendorByDTO() throws Exception {

        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(NAME);
        Vendor savedVendor = Vendor.builder().id(ID).name(NAME).build();

        given(vendorRepository.save(any(Vendor.class))).willReturn(savedVendor);

        //when
        VendorDTO savedDto = vendorService.saveVendorByDTO(2L, vendorDTO);

        //then
        assertEquals(vendorDTO.getName(), savedDto.getName());
        assertEquals(BASEURL_ID2, savedDto.getVendorUrl());
    }

    @Test
    public void deleteVendorById() throws Exception {

        Long id = 1L;

        vendorService.deleteVendorById(id);

        verify(vendorRepository, times(1)).deleteById(anyLong());
    }

}
