package com.firesoul.jpokemon.model.impl;

import java.util.function.Supplier;

import com.firesoul.jpokemon.model.api.Collider;
import com.firesoul.jpokemon.model.api.EventHandler;
import com.firesoul.jpokemon.model.api.GameObject;
import com.firesoul.jpokemon.model.api.GameObjectFactory;
import com.firesoul.jpokemon.model.api.Room;
import com.firesoul.jpokemon.model.api.Room.Grid;

public class GameObjectFactoryImpl implements GameObjectFactory {

    private final Room room;

    private abstract class BaseGameObject implements GameObject {

        private final Collider collider;
        private Vector2 position;
        private double speed;

        public BaseGameObject(final Vector2 position, final double speed) {
            this.collider = new BoxCollider(this, Room.TILE_SIZE, Room.TILE_SIZE);
            this.position = position;
            this.speed = speed;
        }

        public abstract Vector2 movingCriteria();

        @Override
        public void update(final double dt) {
            this.position = this.position.add(this.movingCriteria().multiply(this.speed).multiply(dt));
        }

        @Override
        public Vector2 getPosition() {
            return this.position;
        }

        @Override
        public Grid getGridPosition() {
            return new Grid(
                (int) (this.position.x()/Room.TILE_SIZE),
                (int) (this.position.y()/Room.TILE_SIZE)
            );
        }

        @Override
        public double getSpeed() {
            return this.speed;
        }

        @Override
        public Collider getCollider() {
            return this.collider;
        }

        protected void setPosition(final Vector2 position) {
            this.position = position;
        }

        protected void setSpeed(final double speed) {
            this.speed = speed;
        }
    }

    public GameObjectFactoryImpl(final Room room) {
        this.room = room;
    }

    @Override
    public GameObject movingGameObject(final Vector2 position, final double speed, final Supplier<Vector2> movingCriteria) {
        return new BaseGameObject(position, speed) {
            @Override
            public Vector2 movingCriteria() {
                return movingCriteria.get();
            }
        };
    }

    @Override
    public GameObject staticGameObject(final Vector2 position) {
        return this.movingGameObject(position, 0.0, () -> Vector2.zero());
    }

    @Override
    public GameObject dynamicGameObject(final Vector2 position, final double speed) {
        return this.movingGameObject(position, speed, () -> Vector2.zero());
    }

    @Override
    public GameObject player(final Vector2 position, final double speed, final EventHandler keys) {
        return new BaseGameObject(position, speed) {
            private final double startSpeed = speed;
            private Vector2 oldVelocity = Vector2.zero();
        
            @Override
            public Vector2 movingCriteria() {
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
                    velocity = new Vector2(0.0, velocity.add(-1.0).y());
                }
                if (keys.getEvent("MoveDown")) {
                    velocity = new Vector2(0.0, velocity.add(1.0).y());
                }
                if (keys.getEvent("MoveLeft")) {
                    velocity = new Vector2(velocity.add(-1.0).x(), 0.0);
                }
                if (keys.getEvent("MoveRight")) {
                    velocity = new Vector2(velocity.add(1.0).x(), 0.0);
                }
                final Grid nextPosition = new Grid(this.getGridPosition().x() + (int) velocity.x(), this.getGridPosition().y() + (int) velocity.y());
                if (room.findGameObject(nextPosition).isPresent()) {
                    velocity = Vector2.zero();
                }
                this.oldVelocity = velocity;
                return velocity;
            }
        };
    }
}
