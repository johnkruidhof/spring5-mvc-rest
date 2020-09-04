package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    public static final String BASE_URL = "/api/v1/vendors/";
    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendorMapper::vendorToVendorDto)
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        Optional<Vendor> vendor = vendorRepository.findById(id);
        return vendor.map(vendorMapper::vendorToVendorDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {

        return saveAndReturnDTO(vendorMapper.vendorDtoToVendor(vendorDTO));
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO returnDto = vendorMapper.vendorToVendorDto(savedVendor);

        returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return returnDto;
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.vendorDtoToVendor(vendorDTO);
        vendor.setId(id);

        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {

            if(vendorDTO.getName() != null){
                vendor.setName(vendorDTO.getName());
            }

            VendorDTO returnDto = vendorMapper.vendorToVendorDto(vendorRepository.save(vendor));

            returnDto.setVendorUrl(getVendorUrl(id));

            return returnDto;

        }).orElseThrow(ResourceNotFoundException::new);
    }

    private String getVendorUrl(Long id) {
        return BASE_URL + id;
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}
