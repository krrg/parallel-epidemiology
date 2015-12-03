package io.github.krrg;

import io.github.krrg.infections.InfectionModel;
import io.github.krrg.infections.SimpleCommonCold;

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

class WorldFactory {

    public static World createWorld(int populationSize) {
        World world = new World();

        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual();
            InfectionModel model = new SimpleCommonCold(individual, i % 2 == 0);
            individual.setInfectionModel(model);
            world.addIndividual(individual);
        }

        return world;
    }

}
