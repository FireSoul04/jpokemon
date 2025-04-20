package com.firesoul.jpokemon.view.api;

import java.util.List;

import com.firesoul.jpokemon.model.api.GameObject;

public interface Renderer {
    
    /**
     * Open the window.
     * @param width
     * @param height
     */
    void open(int width, int height);

    /**
     * Resize the window to new width and height.
     * @param width
     * @param height
     */
    void resize(int width, int height);

    /**
     * Rescale the width of the window to a new multiplier.
     * @param scaleX
     */
    void setScaleX(double scaleX);

    /**
     * Rescale the height of the window to a new multiplier.
     * @param scaleY
     */
    void setScaleY(double scaleY);

    /**
     * @return the width of the window
     */
    int getWidth();

    /**
     * @return the height of the window
     */
    int getHeight();

    /**
     * Draw all the given game objects' sprites.
     * @param gameObjects
     */
    void draw(List<GameObject> gameObjects);
}
