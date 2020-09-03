package guru.springfamework.api.v1.mapper;

public class CustomerURLFormat {

    public static String toCustomerURL(Long id) {
        return "/shop/customers/"+id;
    }
}
