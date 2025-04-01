package com.firesoul.jmario.model.impl;

public record Vector2(double x, double y) {
    
    public static Vector2 zero() {
        return new Vector2(0.0, 0.0);
    }

    public Vector2 add(final double a) {
        return new Vector2(this.x + a, this.y + a);
    }

    public Vector2 subtract(final double a) {
        return this.add(-a);
    }

    public Vector2 multiply(final double a) {
        return new Vector2(this.x*a, this.y*a);
    }

    public Vector2 divide(final double a) {
        return this.multiply(1/a);
    }

    public Vector2 invert() {
        return new Vector2(-this.x, -this.y);
    }

    public Vector2 add(final Vector2 v) {
        return new Vector2(this.x + v.x, this.y + v.y);
    }

    public Vector2 subtract(final Vector2 v) {
        return this.add(v.invert());
    }
}
