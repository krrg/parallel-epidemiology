package io.github.krrg.infections;

import io.github.krrg.Individual;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krr428 on 11/30/15.
 *
 * An infection model representing the common cold.
 */
public class SimpleCommonCold extends IterativeInfectionModel {


    public SimpleCommonCold(Individual individual, boolean infected) {
        super(individual, infected);
    }

    private boolean isInfectedBy(Individual other) {
        if (!other.getInfectionModel().isContagious()) {
            return false;
        } else {
            return other.getPosition().distanceTo(this.individual.getPosition()) < 96.0;
        }
    }

    @Override
    public void exposeTo(Individual other) {

        if (isInfectedBy(other)) {
            addMutation(() -> this.isInfected = true);
        }

    }

    @Override
    public boolean isContagious() {
        return isInfected();
    }

    @Override
    public boolean isInfected() {
        return isInfected;
    }

    @Override
    public boolean isDeceased() {
        return false;
    }

}
