package com.test.app.ui.cart;


public interface CartNavigator {
    void handleError(Throwable throwable);

    void goToHomeScreen();
}
