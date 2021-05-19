package com.borderfree.account.login.data.model;

import lombok.Data;

@Data
public class Subscriptions {
    private String id;
    private String name;
    private long startDate;
    private long expiryDate;
}
