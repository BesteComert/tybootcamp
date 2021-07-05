package com.tybootcamp.ecomm.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products",indexes = @Index(columnList = "name"))
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String name2;
    private String description;
    @NotNull
    private float price;

    @ElementCollection
    @CollectionTable(name = "Product_Images", joinColumns = @JoinColumn(name = "product_id", nullable = false))
    @Column(name = "image_URL", nullable = false)
    @Size(min = 1)
    @NotNull
    private List<String> images;

    @ManyToOne(targetEntity = Seller.class)
    @JoinColumn(name = "seller_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Seller seller;


    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    @Size(min = 1)
    @NotNull
    private Set<Category> fallIntoCategories;

    public Product()
    {
    }

    public Product(String name2, String name, String description, float price, List<String> images, Seller seller, HashSet<Category> fallIntoCategories)
    {
        this.name = name;
        this.name2 = name2;
        this.description = description;
        this.price = price;
        this.images = images;
        this.seller = seller;
        this.fallIntoCategories = fallIntoCategories;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public String getName2()
    {
        return name2;
    }

    public void setName2(String name2)
    {
        this.name2 = name2;
    }


    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public List<String> getImages()
    {
        return images;
    }

    public void setImages(List<String> images)
    {
        this.images = images;
    }

    public Seller getSeller()
    {
        return seller;
    }

    public void setSeller(Seller seller)
    {
        this.seller = seller;
    }

    public Set<Category> getFallIntoCategories()
    {
        return fallIntoCategories;
    }

    public void setFallIntoCategories(HashSet<Category> fallIntoCategories)
    {
        this.fallIntoCategories = fallIntoCategories;
    }
}

