package io.github.krrg.geom;

import java.util.Random;

/**
 * Created by krr428 on 11/28/15.
 */
public class PositionGenerator {

    private static Random random = new Random();

    private static int getRandomDimension() {
        final int MIN_DIM = 0;
        final int MAX_DIM = 4096;

        return random.nextInt(MAX_DIM) + MIN_DIM;
    }

    public static Position getRandom() {
        return new Position(getRandomDimension(), getRandomDimension());
    }

}
