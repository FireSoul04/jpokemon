package com.firesoul.jpokemon.controller.impl;

import com.firesoul.jpokemon.controller.api.Game;
import com.firesoul.jpokemon.model.api.GameObject;
import com.firesoul.jpokemon.model.api.GameObjectFactory;
import com.firesoul.jpokemon.model.api.Room;
import com.firesoul.jpokemon.model.impl.GameObjectFactoryImpl;
import com.firesoul.jpokemon.model.impl.RoomImpl;
import com.firesoul.jpokemon.model.impl.Vector2;
import com.firesoul.jpokemon.view.api.Renderer;
import com.firesoul.jpokemon.view.impl.KeyHandler;
import com.firesoul.jpokemon.view.impl.RendererImpl;

public class JPokemon implements Game, Runnable {

    private enum GameState {
        RUNNING,
        GAME_OVER,
        MENU
    };

    private static final double MAX_UPDATES = 60.0;

    private final KeyHandler keyHandler = new KeyHandler();
    private final Renderer window = new RendererImpl("JPokemon", this.keyHandler, this);
    private final Room room;
    private GameState state = GameState.MENU;

    public JPokemon() {
        final GameObjectFactory gf = new GameObjectFactoryImpl();
        final GameObject player = gf.player(Vector2.one().multiply(20.0), 1.0, this.keyHandler);
        this.room = new RoomImpl(player);
        this.room.addGameObject(player);
        this.room.addGameObject(gf.staticGameObject(Vector2.one().multiply(80.0)));
        
        this.window.open(480, 320);
    }

    @Override
    public void run() {
        final double ns = 1.0E9 / MAX_UPDATES;
        double dt = 0.0;
        long lastFrame = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long frameTime = System.currentTimeMillis();
        while (!this.isOver()) {
            final long now = System.nanoTime();
            dt = dt + ((now - lastFrame)/ns);
            lastFrame = now;
            while (dt >= 1.0) {
                this.update(dt);
                dt--;

                // DEBUG PURPOSE
                updates++;
                // DEBUG PURPOSE
            }
            this.render();

            // DEBUG PURPOSE
            frames++;
            if (System.currentTimeMillis() - frameTime >= 1000.0) {
                System.out.println("frames: " + frames + ", updates:" + updates);
                frames = 0;
                updates = 0;
                frameTime = System.currentTimeMillis();
            }
            // DEBUG PURPOSE
        }
    }

    @Override
    public void update(final double dt) {
        this.room.update(dt);
    }

    @Override
    public void render() {
        this.window.draw(this.room.getGameObjects());
    }

    @Override
    public Room getCurrentRoom() {
        return this.room;
    }

    @Override
    public boolean isRunning() {
        return this.state.equals(GameState.RUNNING);
    }

    @Override
    public boolean isOnMenu() {
        return this.state.equals(GameState.MENU);
    }

    @Override
    public boolean isOver() {
        return this.state.equals(GameState.GAME_OVER);
    }
}
