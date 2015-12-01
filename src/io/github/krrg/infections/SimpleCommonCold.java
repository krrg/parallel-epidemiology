package io.github.krrg.infections;

import io.github.krrg.Individual;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by krr428 on 11/30/15.
 *
 * An infection model representing the common cold.
 * Low number of initial indivduals
 */
public class SimpleCommonCold extends InfectionModel {

    private boolean isInfected = false;
    private List<Runnable> mutations;

    public SimpleCommonCold(Individual individual) {
        super(individual);
        mutations = new ArrayList<>();
    }

    @Override
    public void tick() {
        mutations.stream().forEachOrdered((r) -> r.run());
    }

    private boolean isInfectedBy(Individual other) {
        if (!other.isContagious()) {
            return false;
        } else {
            return other.getPosition().distanceTo(this.individual.getPosition()) < 20.0;
        }
    }

    @Override
    public void exposeTo(Individual other) {

        if (isInfectedBy(other)) {
            mutations.add(() -> this.isInfected = true);
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
