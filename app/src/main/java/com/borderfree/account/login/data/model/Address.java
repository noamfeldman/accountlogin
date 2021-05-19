package com.borderfree.account.login.data.model;

import lombok.Data;

@Data
public class Address {
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String region;
    private String countryCode;
    private String postalCode;
}
