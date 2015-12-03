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
    private InfectionModel infectionModel;

    public Individual() {
        this.uuid = UUID.randomUUID();

        /* In this constructor, we generate some random defaults */
        this.position = PositionGenerator.getRandom();
    }

    public Position getPosition() {
        return position;
    }

    public void setInfectionModel(InfectionModel model) {
        this.infectionModel = model;
    }

    public InfectionModel getInfectionModel() {
        return infectionModel;
    }


}
