package iuh.fit.duongdinhlong_lab02.converters;


import iuh.fit.duongdinhlong_lab02.enums.ProductStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProductStatusConverter implements AttributeConverter<ProductStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProductStatus productStatus) {
        if(productStatus != null){
            return productStatus.getValue();
        }
        return null;
    }

    @Override
    public ProductStatus convertToEntityAttribute(Integer integer) {
        if(integer == null){
            return null;
        }
        return Stream.of(ProductStatus.values()).filter(c -> c.getValue() == integer).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}