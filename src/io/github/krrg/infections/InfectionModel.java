package io.github.krrg.infections;

import io.github.krrg.Individual;

/**
 * Created by krr428 on 11/30/15.
 */
public abstract class InfectionModel {

    protected Individual individual;

    public InfectionModel(Individual individual) {
        this.individual = individual;
    }

    public abstract void tick();
    public abstract void exposeTo(Individual other);
    public abstract boolean isContagious();
    public abstract boolean isInfected();
    public abstract boolean isDeceased();


}
