package com.rest.pundraherbs;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.rest.pundraherbs.model.ProductType;

@Component
public class ProductTypeEnumConverter implements Converter<String, ProductType> {
	@Override
	public ProductType convert(String value) {
		return ProductType.fromValue(value);
	}
}