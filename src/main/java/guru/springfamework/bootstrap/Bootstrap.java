package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository,
                     CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();
        loadVendors();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded (categories) = " + categoryRepository.count() );
    }

    private void loadCustomers() {
        Customer joe = new Customer();
        joe.setFirstname("Joe");
        joe.setLastname("Newman");

        Customer michael = new Customer();
        michael.setFirstname("Michael");
        michael.setLastname("Lachappele");

        customerRepository.save(joe);
        customerRepository.save(michael);

        System.out.println("Data Loaded (customers) = " + customerRepository.count() );
    }

    private void loadVendors() {
        Vendor fresh = new Vendor();
        fresh.setName("Fresh Foods Ltd.");

        Vendor home = new Vendor();
        home.setName("Home Fruits");

        Vendor nuts = new Vendor();
        nuts.setName("Nuts for Nuts Company");

        Vendor fun = new Vendor();
        fun.setName("Fun Fresh Foods");

        vendorRepository.save(fresh);
        vendorRepository.save(home);
        vendorRepository.save(nuts);
        vendorRepository.save(fun);

        System.out.println("Data Loaded (vendors) = " + vendorRepository.count() );
    }

}
