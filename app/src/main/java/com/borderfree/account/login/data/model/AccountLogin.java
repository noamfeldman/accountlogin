package com.borderfree.account.login.data.model;

import lombok.Data;

@Data
public class AccountLogin {
    private String sessionId;
    private User user;
    private String promotion;
}
