package com.tybootcamp.ecomm.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "customers")
public class Customer extends Profile {

    @NotNull
    private String accountId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true)
    private Basket basket;
    @NotNull
    private  String name;


    public Customer(){

    }

    public Customer(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  void addProductToBasket(Product product,int amount){
        this.basket.setProduct(product);
        this.basket.setAmount(amount);
    }
}
