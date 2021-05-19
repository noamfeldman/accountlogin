package com.borderfree.account.login.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BuyerProfile {
    private long id;
    private String firstName;
    private String lastName;
    private Address address;
    @JsonProperty("default")
    private boolean def;
    private boolean active;
    private Payment payment;
    private String primaryPhone;
    private String secondaryPhone;
    private long createdOn;
    private long updatedOn;
    private boolean valid;
}
