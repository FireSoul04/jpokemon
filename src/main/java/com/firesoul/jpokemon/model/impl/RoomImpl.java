package com.firesoul.jpokemon.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.firesoul.jpokemon.model.api.Collider;
import com.firesoul.jpokemon.model.api.GameObject;
import com.firesoul.jpokemon.model.api.Room;

public class RoomImpl implements Room {

    private final List<GameObject> gameObjects = new ArrayList<>();
    private final GameObject player;

    public RoomImpl(final GameObject player) {
        this.player = player;
    }

    @Override
    public void update(final double dt) {
        for (final GameObject g : this.gameObjects) {
            g.update(dt);
        }
        this.checkCollisions(dt);
    }

    @Override
    public boolean addGameObject(final GameObject gameObject) {
        return this.gameObjects.add(gameObject);
    }

    @Override
    public boolean removeGameObject(final GameObject gameObject) {
        return this.gameObjects.remove(gameObject);
    }

    @Override
    public List<GameObject> getGameObjects() {
        return Collections.unmodifiableList(this.gameObjects);
    }

    @Override
    public GameObject getPlayer() {
        return this.player;
    }

    private void checkCollisions(final double dt) {
        for (final GameObject g1 : this.gameObjects) {
            for (final GameObject g2 : this.gameObjects) {
                final Collider c1 = g1.getCollider();
                final Collider c2 = g2.getCollider();
                if (c1 != c2 && c1.isCollidingWith(c2)) {
                    final Vector2 direction = c1.getPosition().subtract(c2.getPosition());
                    if (direction.norm() < Room.TILE_SIZE*5/6) {
                        g1.pushBack(dt, direction);
                        g2.pushBack(dt, direction);
                    }
                }
            }
        }
    }
}
