package com.firesoul.jpokemon.view.api;

import java.util.List;

import com.firesoul.jpokemon.model.api.GameObject;

public interface Renderer {
    
    /**
     * Open the window
     * @param width
     * @param height
     */
    void open(int width, int height);

    void resize(int width, int height);

    void setScaleX(double scaleX);

    void setScaleY(double scaleY);

    int getWidth();

    int getHeight();

    void draw(List<GameObject> gameObjects);
}
