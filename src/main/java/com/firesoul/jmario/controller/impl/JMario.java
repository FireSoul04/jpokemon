package com.firesoul.jmario.controller.impl;

import com.firesoul.jmario.controller.api.Game;
import com.firesoul.jmario.model.api.GameObject;
import com.firesoul.jmario.model.api.GameObjectFactory;
import com.firesoul.jmario.model.impl.GameObjectFactoryImpl;
import com.firesoul.jmario.model.impl.Vector2;
import com.firesoul.jmario.view.api.Renderer;
import com.firesoul.jmario.view.impl.RendererImpl;

public class JMario implements Game, Runnable {

    private enum GameState {
        RUNNING,
        GAME_OVER,
        MENU
    };

    private static final double MAX_UPDATES = 60.0;

    private final Renderer window = new RendererImpl("JPokemon");
    private GameState state = GameState.MENU;

    GameObjectFactory gf = new GameObjectFactoryImpl();
    GameObject player;

    public JMario() {
        player = gf.movingGameObject(Vector2.one().multiply(20), Vector2.one(), dt -> new Vector2(1.0, 0.0).multiply(dt));
    }

    @Override
    public void run() {
        this.window.open(480, 320);
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
                updates++;
                dt--;
            }
            this.render();
            frames++;
            if (System.currentTimeMillis() - frameTime >= 1000.0) {
                System.out.println("frames: " + frames + ", updates:" + updates);
                frames = 0;
                updates = 0;
                frameTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void update(final double dt) {
        player.update(dt);
    }

    @Override
    public void render() {
        window.draw(player);
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
