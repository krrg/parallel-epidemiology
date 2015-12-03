package io.github.krrg.infections;

import io.github.krrg.Individual;

import java.util.Random;

/**
 * Created by krr428 on 12/3/15.
 */
public class Measles extends IterativeInfectionModel {

    private int daysInfected;
    private boolean isImmune;
    private boolean isDeceased;

    public Measles(Individual individual, boolean infected, boolean isImmunized) {
        super(individual, infected);
        this.isImmune = isImmunized;
        this.isDeceased = false;
    }

    public Measles(Individual individual, boolean infected) {
        this(individual, infected, false);
    }

    private static Random random = new Random();

    private static boolean prob(int percent) {
        return random.nextInt(100) < percent;
    }

    @Override
    public void exposeTo(Individual other) {
        if (isImmune) {
            return;
        }

        if (other.getPosition().distanceTo(individual.getPosition()) > 100.0) {
            return;
        }

        if (other.getInfectionModel().isContagious() && prob(90)) {
            this.addMutation(() -> {
                this.isInfected = true;
            });
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.isInfected) {
            daysInfected ++;
        }

        if (daysInfected > 1 && daysInfected < 10 && prob(5)) {
            isInfected = false;
            isImmune = true;
        }

        if (daysInfected >= 14) {
            isInfected = false;
            isDeceased = true;
        }

    }

    @Override
    public boolean isImmune() {
        return isImmune;
    }

    @Override
    public boolean isContagious() {
        return isInfected() && !isDeceased();
    }

    @Override
    public boolean isDeceased() {
        return isDeceased;
    }
}
