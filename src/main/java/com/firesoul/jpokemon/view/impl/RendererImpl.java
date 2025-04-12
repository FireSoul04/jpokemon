package com.firesoul.jpokemon.view.impl;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.firesoul.jpokemon.controller.api.Game;
import com.firesoul.jpokemon.model.api.GameObject;
import com.firesoul.jpokemon.model.api.Room;
import com.firesoul.jpokemon.view.api.Renderer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

public class RendererImpl extends JPanel implements Renderer {

    private final Game game;
    private JFrame window;
    private int startWidth;
    private int startHeight;
    private double scaleX;
    private double scaleY;

    public RendererImpl(final String title, final KeyHandler keyHandler, final Game game) {
        this.window = new JFrame(title);
        this.window.addKeyListener(keyHandler.getKeyListener());
        this.game = game;

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.scaleX = screenSize.getWidth() / 7000.0 * 9.0;
        this.scaleY = screenSize.getHeight() / 7000.0 * 16.0;
    }

    @Override
    public void open(final int width, final int height) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.startWidth = width;
        this.startHeight = height;

        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setBounds(
            (int) (screenSize.getWidth() - width*this.scaleX)/2,
            (int) (screenSize.getHeight() - height*this.scaleY)/2,
            width, 
            height
        );
        this.window.add(this);
        this.window.setVisible(true);
        this.window.getContentPane().setPreferredSize(new Dimension((int) (width*this.scaleX), (int) (height*this.scaleY)));
        this.window.pack();
        this.window.setMinimumSize(this.window.getSize());
        this.window.addComponentListener(new ComponentAdapter() {
            public void componentResized(final ComponentEvent componentEvent) {
                final RendererImpl w = RendererImpl.this;
                final Dimension d = ((JFrame) componentEvent.getComponent()).getContentPane().getSize();
                w.setScaleX(d.getWidth()/w.startWidth);
                w.setScaleY(d.getHeight()/w.startHeight);
            }
        });
    }

    @Override
    public void setScaleX(final double scaleX) {
        this.scaleX = scaleX;
    }

    @Override
    public void setScaleY(final double scaleY) {
        this.scaleY = scaleY;
    }

    @Override
    public void draw(final List<GameObject> gameObjects) {
        this.repaint();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, (int) (this.window.getWidth()*this.scaleX), (int) (this.window.getHeight()*this.scaleY));
        g2.setColor(Color.WHITE);

        for (final GameObject go : this.game.getCurrentRoom().getGameObjects()) {
            g2.fillRect(
                (int) go.getPosition().x(),
                (int) go.getPosition().y(),
                (int) Room.TILE_SIZE, 
                (int) Room.TILE_SIZE
            );
        }

        g2.dispose();
    }
}
