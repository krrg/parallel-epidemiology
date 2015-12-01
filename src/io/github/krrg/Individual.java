package io.github.krrg;

import io.github.krrg.geom.Position;
import io.github.krrg.geom.PositionGenerator;
import io.github.krrg.infections.InfectionModel;

import java.util.Random;
import java.util.UUID;

/**
 * Created by krr428 on 11/28/15.
 */
public class Individual {

    private final UUID uuid;
    private final Position position;
    private final int immuneStrength;
    private InfectionModel infectionModel;

    public Individual(InfectionModel infectionModel) {
        this.uuid = UUID.randomUUID();
        this.infectionModel = infectionModel;
        this.position = PositionGenerator.getRandom();
        this.immuneStrength = getRandomImmunity();
    }

    public Position getPosition() {
        return position;
    }

    /* A number between 0 and 1000 indicating the intrinsic
        strength of the individuals immune system.

        The immune strength is used in two ways:
        1) To determine whether an individual is infected or not,
        2) To determine how quickly an individual recovers from
           a disease.

        The infection model is in charge of the logistics of
        actually computing the above items.

     */
    public int getImmuneStrength() {
        return immuneStrength;
    }

    public boolean isContagious () {
        return infectionModel.isContagious();
    }

    private static Random rand = new Random();

    private static int getRandomImmunity() {
        final int MAX_IMMUNE_STRENGTH = 1000;
        final int MIN_IMMUNE_STRENGTH = 0;
        return rand.nextInt(MAX_IMMUNE_STRENGTH) + MIN_IMMUNE_STRENGTH;
    }
}
