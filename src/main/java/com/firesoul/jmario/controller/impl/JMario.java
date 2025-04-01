package com.firesoul.jmario.controller.impl;

import com.firesoul.jmario.controller.api.Game;
import com.firesoul.jmario.view.api.Window;
import com.firesoul.jmario.view.impl.WindowImpl;

public class JMario implements Game, Runnable {

    private enum GameState {
        RUNNING,
        GAME_OVER,
        MENU
    };

    private static final double MAX_UPDATES = 60.0;

    private final Window window = new WindowImpl("JMario");
    private GameState state = GameState.MENU;

    public JMario() {
        
    }

    @Override
    public void run() {
        this.window.open(800, 600);
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
        
    }

    @Override
    public void render() {

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
