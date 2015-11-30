package io.github.krrg.infections;

import io.github.krrg.Individual;

/**
 * Created by krr428 on 11/30/15.
 */
public abstract class InfectionModel {

    private Individual individual;

    public InfectionModel(Individual individual) {
        this.individual = individual;
    }

    // If the individual is infected, then we
    //  give the model an opportunity to infect the individual.
    public abstract void expose(Individual other);
    public abstract boolean isInfected()


}
