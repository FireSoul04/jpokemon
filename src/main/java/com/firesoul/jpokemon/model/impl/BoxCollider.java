package com.firesoul.jpokemon.model.impl;

import com.firesoul.jpokemon.model.api.Collider;
import com.firesoul.jpokemon.model.api.GameObject;

public class BoxCollider implements Collider {

    private final GameObject attachedGameObject;
    private final double width;
    private final double height;

    public BoxCollider(final GameObject attachedGameObject, final double width, final double height) {
        this.attachedGameObject = attachedGameObject;
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean isCollidingWith(final Collider c) {
        final Vector2 d1 = this.getDimensions();
        final Vector2 d2 = c.getDimensions();
        final Vector2 c1 = this.getPosition();
        final Vector2 c2 = c.getPosition();
        return c1.x() < c2.x() + d2.x()
            && c1.x() + d1.x() > c2.x()
            && c1.y() < c2.y() + d2.y()
            && c1.y() + d1.y() > c2.y();
    }

    @Override
    public Vector2 getPosition() {
        return attachedGameObject.getPosition();
    }

    @Override
    public Vector2 getDimensions() {
        return new Vector2(this.width, this.height);
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }
}
