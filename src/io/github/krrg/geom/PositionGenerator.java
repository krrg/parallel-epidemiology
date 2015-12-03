package io.github.krrg.geom;

import java.util.Random;

/**
 * Created by krr428 on 11/28/15.
 */
public class PositionGenerator {

    private static Random random = new Random();
    public static final int MIN_DIM = 0;
    public static final int MAX_DIM = 1024;

    private static int getRandomDimension() {
        return random.nextInt(MAX_DIM) + MIN_DIM;
    }

    public static Position getRandom() {
        return new Position(getRandomDimension(), getRandomDimension());
    }

}
