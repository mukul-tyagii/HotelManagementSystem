package com.mukul;

import com.mukul.controller.AuthController;
import com.mukul.model.AuthModel;
import com.mukul.view.AuthView;

public class App {
    public static void main(String[] args) {
        AuthModel model = new AuthModel();
        AuthView view = new AuthView();
        new AuthController(model, view).showView();
    }
}
