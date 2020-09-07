package guru.springframework.api.v1.mapper;

public class ShopEntityURLFormat {

    public static String toShopEntityURL(String shopEntityName, Long id) {
        return "/shop/"+shopEntityName+"/"+id;
    }
}
