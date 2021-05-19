package com.borderfree.account.login.ui.login;

import com.borderfree.account.login.data.model.AccountLogin;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private final AccountLogin accountLogin;

    public LoggedInUserView(AccountLogin accountLogin) {
        this.accountLogin = accountLogin;
    }

    String getDisplayName() {
        return accountLogin.getUser().getFirstName() + " " + accountLogin.getUser().getLastName();
    }
}