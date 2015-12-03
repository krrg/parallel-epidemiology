package io.github.krrg.infections;

import io.github.krrg.Individual;

/**
 * Created by krr428 on 11/30/15.
 */
public abstract class InfectionModel {

    protected Individual individual;
    protected boolean isInfected = false;

    public InfectionModel(Individual individual, boolean infected) {
        this.individual = individual;
        this.isInfected = infected;
    }

    public abstract void tick();
    public abstract void exposeTo(Individual other);
    public abstract boolean isContagious();
    public abstract boolean isDeceased();
    public boolean isInfected() {
        return isInfected;
    }

}
