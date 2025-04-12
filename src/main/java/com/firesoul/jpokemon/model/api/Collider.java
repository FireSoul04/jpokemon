package com.firesoul.jpokemon.model.api;

import com.firesoul.jpokemon.model.impl.Vector2;

public interface Collider {

    boolean isCollidingWith(Collider c);

    Vector2 getPosition();

    Vector2 getDimensions();

    double getWidth();

    double getHeight();
}
