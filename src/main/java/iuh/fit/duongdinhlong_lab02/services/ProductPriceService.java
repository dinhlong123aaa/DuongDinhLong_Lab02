package iuh.fit.duongdinhlong_lab02.services;

import iuh.fit.duongdinhlong_lab02.models.Product;
import iuh.fit.duongdinhlong_lab02.models.ProductPrice;
import iuh.fit.duongdinhlong_lab02.repositories.ProductPriceRepository;
import iuh.fit.duongdinhlong_lab02.repositories.ProductRepository;

import java.util.Optional;

public class ProductPriceService {
    private ProductPriceRepository productPriceRepository;
    private ProductRepository productRepository;

    public ProductPriceService() {
        this.productPriceRepository = new ProductPriceRepository();
        this.productRepository = new ProductRepository();
    }

    public boolean addProductPrice(long pId, ProductPrice productPrice){
        Optional<Product> rs = productRepository.findById(pId);
        if(rs.isEmpty()){
            return false;
        }
        productPrice.setProduct(rs.get());
        System.out.println(productPrice.getPriceDateTime().toDate());
        productPriceRepository.addProductPrice(productPrice);

        return true;
    }
}