package com.Screens.Panes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;

public class AntNameInputListener implements TextInputListener {

    String txt;
    @Override
    public void input(String text) {
        txt = text;
       System.out.println(" worked!");
    }

    @Override
    public void canceled() {
        Gdx.input.getTextInput(new TextInputListener() {
            @Override
            public void input (String text) {
              System.out.println("message: " + text + ", touch screen for new dialog");
            }
            @Override
            public void canceled () {
              System.out.println("cancled by user");
            }
          }, "enter something funny", "funny", "something funny");
    }
    
}
