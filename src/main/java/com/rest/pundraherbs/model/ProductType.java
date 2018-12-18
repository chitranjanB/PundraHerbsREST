package com.rest.pundraherbs.model;

import java.util.Arrays;

public enum ProductType {

	HUMAN("human"), VET("veterinary");

	private String value;

	private ProductType(String value) {
		this.value = value;
	}

	public static ProductType fromValue(String value) {
		for (ProductType product : values()) {
			if (product.value.equalsIgnoreCase(value)) {
				return product;
			}
		}
		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}

}
