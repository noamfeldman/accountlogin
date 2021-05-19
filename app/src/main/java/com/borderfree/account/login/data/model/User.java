package com.borderfree.account.login.data.model;

import java.util.List;

import lombok.Data;

@Data
public class User {
    private String uuid;
    private String email;
    private String signUpId;
    private String firstName;
    private String lastName;
    private String country;
    private String locale;
    private Origin origin;
    private List<BuyerProfile> billing;
    private List<BuyerProfile> shipping;
    private List<String> orders;
    private List<Subscriptions> subscriptions;
    private Preferences preferences;
    private String group;
    private String createdDate;
    private String updatedDate;
    private String lastLoginDate;
}
