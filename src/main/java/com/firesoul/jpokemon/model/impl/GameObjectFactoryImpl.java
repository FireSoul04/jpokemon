package com.firesoul.jpokemon.model.impl;

import java.util.function.Function;

import com.firesoul.jpokemon.model.api.EventHandler;
import com.firesoul.jpokemon.model.api.GameObject;
import com.firesoul.jpokemon.model.api.GameObjectFactory;
import com.firesoul.jpokemon.model.api.Room;

public class GameObjectFactoryImpl implements GameObjectFactory {

    private abstract class BaseGameObject implements GameObject {

        private Vector2 position;
        private double speed;

        public BaseGameObject(final Vector2 position, final double speed) {
            this.position = position;
            this.speed = speed;
        }

        public abstract Vector2 movingCriteria(final double dt);

        @Override
        public void update(final double dt) {
            this.position = this.position.add(this.movingCriteria(dt).multiply(this.getSpeed()));
        }

        @Override
        public Vector2 getPosition() {
            return this.position;
        }

        @Override
        public double getSpeed() {
            return this.speed;
        }

        protected void setPosition(final Vector2 position) {
            this.position = position;
        }

        protected void setSpeed(final double speed) {
            this.speed = speed;
        }
    }

    public GameObjectFactoryImpl() {

    }

    @Override
    public GameObject movingGameObject(final Vector2 position, final double speed, final Function<Double, Vector2> movingCriteria) {
        return new BaseGameObject(position, speed) {
            @Override
            public Vector2 movingCriteria(final double dt) {
                return movingCriteria.apply(dt);
            }
        };
    }

    @Override
    public GameObject staticGameObject(final Vector2 position) {
        return this.movingGameObject(position, 0.0, dt -> Vector2.zero());
    }

    @Override
    public GameObject dynamicGameObject(final Vector2 position, final double speed) {
        return this.movingGameObject(position, speed, dt -> Vector2.zero());
    }

    @Override
    public GameObject player(final Vector2 position, final double speed, final EventHandler keys) {
        return new BaseGameObject(position, speed) {
            private final double startSpeed = speed;
            private Vector2 oldVelocity = Vector2.zero();
        
            @Override
            public Vector2 movingCriteria(final double dt) {
                if (keys.getEvent("SpeedUp")) {
                    this.setSpeed(this.startSpeed*2);
                } else {
                    this.setSpeed(this.startSpeed);
                }
                if ((int) (this.getPosition().x() % Room.TILE_SIZE) != 0 ||
                    (int) (this.getPosition().y() % Room.TILE_SIZE) != 0) {
        
                    return this.oldVelocity;
                }
                Vector2 velocity = Vector2.zero();
                if (keys.getEvent("MoveUp")) {
                    velocity = new Vector2(0.0, velocity.add(-1.0).y()).multiply(dt);
                }
                if (keys.getEvent("MoveDown")) {
                    velocity = new Vector2(0.0, velocity.add(1.0).y()).multiply(dt);
                }
                if (keys.getEvent("MoveLeft")) {
                    velocity = new Vector2(velocity.add(-1.0).x(), 0.0).multiply(dt);
                }
                if (keys.getEvent("MoveRight")) {
                    velocity = new Vector2(velocity.add(1.0).x(), 0.0).multiply(dt);
                }
                this.oldVelocity = velocity;
                return velocity;
            }
        };
    }
}
