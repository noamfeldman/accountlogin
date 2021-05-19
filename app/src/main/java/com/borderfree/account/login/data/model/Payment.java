package com.borderfree.account.login.data.model;

import lombok.Data;

@Data
public class Payment {
    private String id;
    private String type;
    private String lastFourDigits;
    private String expiryYear;
    private String expiryMonth;
}
