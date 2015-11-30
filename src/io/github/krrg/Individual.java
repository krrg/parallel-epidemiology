package io.github.krrg;

import io.github.krrg.geom.Position;
import io.github.krrg.geom.PositionGenerator;

import java.util.UUID;

/**
 * Created by krr428 on 11/28/15.
 */
public class Individual {

    private UUID uuid;
    private Position position;
    private InfectionModel infectionModel;

    public Individual(InfectionModel infectionModel) {
        this.uuid = UUID.randomUUID();
        this.infectionModel = infectionModel;
        this.position = PositionGenerator.getRandom();
    }

    public abstract class InfectionModel {



    }

}
