package com.firesoul.jmario.view.impl;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

import com.firesoul.jmario.view.api.Window;

public class WindowImpl implements Window {

    private static final int MAP_WIDTH = 50;
    private static final int MAP_HEIGHT = 50;

    private final JFrame frame;
    private double scaleX;
    private double scaleY;

    public WindowImpl(final String title) {
        this.frame = new JFrame(title);

        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.scaleX = screenSize.getWidth() / 10000.0 * 9.0;
        this.scaleY = screenSize.getHeight() / 10000.0 * 16.0;
    }

    @Override
    public void open(final int width, final int height) {
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(width, height);

        this.frame.setVisible(true);
    }
}
