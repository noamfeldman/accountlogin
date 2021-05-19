package com.borderfree.account.login.data.model.errors;

import java.util.List;

import lombok.Data;

@Data
public class ErrorBody {
    private List<ErrorData> data;
}
