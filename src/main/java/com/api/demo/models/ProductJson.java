package com.api.demo.models;

public class ProductJson {
    protected long id;
    protected String productName;
    protected String  productCode;
    protected String description;
    protected float starRating;


    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product [description=" + description + ", id=" + id + ", productCode=" + productCode + ", productName="
                + productName + ", starRating=" + starRating + "]";
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getStarRating() {
        return starRating;
    }

    public void setStarRating(float starRating) {
        this.starRating = starRating;
    }      
    
}
