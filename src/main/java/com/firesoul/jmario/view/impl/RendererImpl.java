package com.firesoul.jmario.view.impl;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import com.firesoul.jmario.model.api.GameObject;
import com.firesoul.jmario.view.api.Renderer;

public class RendererImpl extends JPanel implements Renderer {

    private static final int MAP_WIDTH = 50;
    private static final int MAP_HEIGHT = 50;

    private JFrame window;
    private int startWidth;
    private int startHeight;
    private double scaleX;
    private double scaleY;

    GameObject pl = null;

    public RendererImpl(final String title, final KeyHandler keyHandler) {
        this.window = new JFrame(title);
        this.window.addKeyListener(keyHandler.getKeyListener());

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.scaleX = screenSize.getWidth() / 10000.0 * 9.0;
        this.scaleY = screenSize.getHeight() / 10000.0 * 16.0;
    }

    @Override
    public void open(final int width, final int height) {
        this.startWidth = width;
        this.startHeight = height;

        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.setSize(width, height);
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
    public void draw(final GameObject player) {
        pl = player;
        this.repaint();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, (int) (this.window.getWidth()*this.scaleX), (int) (this.window.getHeight()*this.scaleY));
        g2.setColor(Color.WHITE);
        g2.fillRect((int) (pl.getPosition().x()*this.scaleX), (int) (pl.getPosition().y()*this.scaleX), (int) (20*this.scaleX), (int) (20*this.scaleY));
        g2.dispose();
    }
}
