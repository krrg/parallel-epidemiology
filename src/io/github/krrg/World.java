package io.github.krrg;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by krr428 on 11/30/15.
 */
public class World {

    private Set<Individual> population;

    public World() {
        population = new HashSet<>();
    }

    public Set<Individual> getPopulation() {
        return population;
    }

    public void addIndividual(Individual i) {
        population.add(i);
    }

    public void resetWorld() {
        population.clear();
    }

}
