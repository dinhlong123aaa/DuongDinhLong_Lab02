package iuh.fit.duongdinhlong_lab02.converters;


import iuh.fit.duongdinhlong_lab02.enums.EmployeeStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class EmployeeStatusConverter implements AttributeConverter<EmployeeStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(EmployeeStatus employeeStatus) {
        if(employeeStatus != null){
            return employeeStatus.getValue();
        }

        return null;
    }

    @Override
    public EmployeeStatus convertToEntityAttribute(Integer integer) {
        if(integer == null){
            return null;
        }
        return Stream.of(EmployeeStatus.values()).filter(c -> c.getValue() == integer).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}