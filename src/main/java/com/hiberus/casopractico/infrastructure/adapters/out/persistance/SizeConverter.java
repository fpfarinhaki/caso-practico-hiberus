package com.hiberus.casopractico.infrastructure.adapters.out.persistance;

import com.hiberus.casopractico.commons.Size;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.util.StringUtils;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class SizeConverter implements AttributeConverter<Size, String> {

    @Override
    public String convertToDatabaseColumn(Size size) {
        if(size == null) {
            return null;
        }
        return size.getCode();
    }

    @Override
    public Size convertToEntityAttribute(String code) {
        if(StringUtils.hasLength(code)) {
            return Stream.of(Size.values())
                    .filter(c -> c.getCode().equals(code))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
        return null;
    }
}
