package controller;

import view.Game;

import javax.swing.*;

public class ControllerForModel implements IControllerForModel {

    private static ControllerForModel instance = null;

    public IControllerForModel getInstance() {
        if (instance == null)
            instance = new ControllerForModel();
        return instance;
    }


}