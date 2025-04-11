package com.firesoul.jmario.view.api;

import com.firesoul.jmario.model.api.GameObject;

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

    void draw(GameObject player);
}
