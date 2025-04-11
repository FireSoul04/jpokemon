package com.firesoul.jmario.model.api;

public interface GameObject {
    
    /**
     * This determine what the game object does every frame based on delta time.
     * @param dt the difference between this frame and the last one
     */
    void update(double dt);
}
