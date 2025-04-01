package com.firesoul.jmario;

import com.firesoul.jmario.controller.api.Game;
import com.firesoul.jmario.controller.impl.JMario;

public class Start {
    public static void main(String[] args) {
        Game jmario = new JMario();
        jmario.run();
    }
}
