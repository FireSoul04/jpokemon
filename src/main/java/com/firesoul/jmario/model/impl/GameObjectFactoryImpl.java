package com.firesoul.jmario.model.impl;

import java.util.function.Function;

import com.firesoul.jmario.model.api.EventHandler;
import com.firesoul.jmario.model.api.GameObject;
import com.firesoul.jmario.model.api.GameObjectFactory;

public class GameObjectFactoryImpl implements GameObjectFactory {

    public GameObjectFactoryImpl() {

    }

    @Override
    public GameObject movingGameObject(final Vector2 pos, final Vector2 sp, final Function<Double, Vector2> movingCriteria) {
        return new GameObject() {
            private final Vector2 speed = sp;
            private Vector2 position = pos;
            private Vector2 velocity = Vector2.zero();

            @Override
            public void update(final double dt) {
                this.velocity = movingCriteria.apply(dt);
                this.position = this.velocity.multiply(dt);
            }
        };
    }

    @Override
    public GameObject staticGameObject(final Vector2 position) {
        return movingGameObject(position, Vector2.zero(), dt -> Vector2.zero());
    }

    @Override
    public GameObject dynamicGameObject(final Vector2 position, final Vector2 speed) {
        return movingGameObject(position, speed, dt -> Vector2.zero());
    }

    @Override
    public GameObject player(final Vector2 position, final Vector2 speed, final EventHandler keys) {
        return movingGameObject(position, speed, dt -> this.getPlayerInput(keys, speed, dt));
    }

    private Vector2 getPlayerInput(final EventHandler keys, final Vector2 speed, final double dt) {
        Vector2 velocity = Vector2.zero();
        if (keys.getEvent("MoveLeft")) {
            velocity = velocity.subtract(speed).multiply(dt);
        }
        if (keys.getEvent("MoveRight")) {
            velocity = velocity.add(speed).multiply(dt);
        }
        return velocity;
    }
}
