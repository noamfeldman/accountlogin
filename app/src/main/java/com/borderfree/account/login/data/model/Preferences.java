package com.borderfree.account.login.data.model;

import java.util.List;

import lombok.Data;

@Data
public class Preferences {
    private List<Communications> communications;
}
