package com.borderfree.account.login.data.model.errors;

import lombok.Data;

@Data
public class ErrorResponse {
    private String errorMsg;
    private ErrorBody errorBody;
    private String action;
    private String actionType;
    private int status;

}
