package com.firesoul.jpokemon;

import com.firesoul.jpokemon.controller.api.Game;
import com.firesoul.jpokemon.controller.impl.JPokemon;

public class Start {
    public static void main(String[] args) {
        Game jpokemon = new JPokemon();
        jpokemon.run();
    }
}
