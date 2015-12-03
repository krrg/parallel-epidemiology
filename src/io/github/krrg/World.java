package io.github.krrg;

import io.github.krrg.infections.InfectionModel;
import io.github.krrg.infections.Measles;
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

    public static World createWorld() {
        World world = new World();

        int populationSize = 1000;
        final int INITIAL_INFECTED = 20;
        final int IMMUNIZATION_LEVEL = 90;

        for (int i = 0; i < INITIAL_INFECTED; i++) {
            populationSize--;
            Individual individual = new Individual();
            InfectionModel model = new Measles(individual, true);
            individual.setInfectionModel(model);
            world.addIndividual(individual);
        }

        for (int i = 0; i < populationSize; i++) {
            Individual individual = new Individual();
            InfectionModel model = new Measles(individual, false, i % 100 <= IMMUNIZATION_LEVEL);
            individual.setInfectionModel(model);
            world.addIndividual(individual);
        }

        System.out.println(world.getPopulation().size());

        return world;
    }

}
