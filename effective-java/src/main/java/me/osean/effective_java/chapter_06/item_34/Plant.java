package me.osean.effective_java.chapter_06.item_34;

public enum Plant {

    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6),
    MARS(6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN(5.685e26, 6.027e7),
    URANUS(8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.477e7);

    private final double mass;
    private final double radius;
    private final double surfaceGravity;

    // 중력상수 (단위 m^3 / kg s^2)
    private static final double G = 6.67300E-11;

    Plant(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        this.surfaceGravity = G * mass / (radius * radius);
    }

    public double mass() {
        return this.mass;
    }

    public double radius() {
        return this.radius;
    }

    public double surfaceGravity() {
        return this.surfaceGravity;
    }

    public double surfaceGravityWeight(double mass) {
        return mass * this.surfaceGravity; // F * ma
    }
}

