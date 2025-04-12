package com.firesoul.jpokemon.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
}
