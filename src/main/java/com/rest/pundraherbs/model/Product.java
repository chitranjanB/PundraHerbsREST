package com.rest.pundraherbs.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Product {
	@Id
	@GeneratedValue
	private Long productId;
	private String productName;

	@Enumerated(EnumType.STRING)
	private ProductType productType;
	private String productSummary;
	private String productPrice;
	private String productDiscount;
	private String productImg;
	private int unitInStock;
	private Double price;

	@ElementCollection
	private List<String> ingredients;
	@ElementCollection
	private List<String> packings;
	@ElementCollection
	private List<String> indications;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "productId")
	private List<Review> reviewComments;

	@OneToMany
	@JoinColumn(name = "productId")
	private List<Dosage> dosage;

	public Product() {
	}

	public Product(Long productId) {
		this.productId = productId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getProductSummary() {
		return productSummary;
	}

	public void setProductSummary(String productSummary) {
		this.productSummary = productSummary;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(String productDiscount) {
		this.productDiscount = productDiscount;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public int getUnitInStock() {
		return unitInStock;
	}

	public void setUnitInStock(int unitInStock) {
		this.unitInStock = unitInStock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<String> ingredients) {
		this.ingredients = ingredients;
	}

	public List<String> getPackings() {
		return packings;
	}

	public void setPackings(List<String> packings) {
		this.packings = packings;
	}

	public List<String> getIndications() {
		return indications;
	}

	public void setIndications(List<String> indications) {
		this.indications = indications;
	}

	public List<Review> getReviewComments() {
		return reviewComments;
	}

	public void setReviewComments(List<Review> reviewComments) {
		this.reviewComments = reviewComments;
	}

	public List<Dosage> getDosage() {
		return dosage;
	}

	public void setDosage(List<Dosage> dosage) {
		this.dosage = dosage;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", productSummary=" + productSummary
				+ ", productPrice=" + productPrice + ", productDiscount=" + productDiscount + ", productImg="
				+ productImg + ", unitInStock=" + unitInStock + ", ingredients=" + ingredients + ", packings="
				+ packings + ", indications=" + indications + ", reviewComments=" + reviewComments + ", dosage="
				+ dosage + "]";
	}

}
