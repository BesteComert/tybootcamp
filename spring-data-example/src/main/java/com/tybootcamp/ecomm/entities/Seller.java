package com.tybootcamp.ecomm.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sellers")
public class Seller extends Profile {
    @NotNull
    private String accountId;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "seller", orphanRemoval = true)
    private Profile profile;

    public Seller()
    {
    }

    public Seller(String accountId)
    {
        this.accountId = accountId;
    }

    public String getAccountId()
    {
        return accountId;
    }

    public void setAccountId(String accountId)
    {
        this.accountId = accountId;
    }

    public Profile getProfile()
    {
        return profile;
    }

    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }

    @Override
    public String toString()
    {
        if (profile == null)
        {
            return super.toString();
        }
        else
        {
            return getProfile().getFirstName() + " " + getProfile().getLastName();
        }
    }
}

