package com.mukul;

import com.formdev.flatlaf.FlatLightLaf;
import com.mukul.controller.AuthController;
import com.mukul.model.AuthModel;
import com.mukul.view.AuthView;

public class App {
    public static void main(String[] args) {
        FlatLightLaf.setup();
        AuthModel model = new AuthModel();
        AuthView view = new AuthView();
        new AuthController(model, view).showView();
    }
}
