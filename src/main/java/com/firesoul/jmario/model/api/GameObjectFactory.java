package com.firesoul.jmario.model.api;

import java.util.function.Function;

import com.firesoul.jmario.model.impl.Vector2;

public interface GameObjectFactory {

    GameObject movingGameObject(Vector2 position, Vector2 speed, Function<Double, Vector2> movingCriteria);

    GameObject staticGameObject(Vector2 position);
    
    GameObject dynamicGameObject(Vector2 position, Vector2 speed);

    GameObject player(Vector2 position, Vector2 speed, EventHandler keys);
}
