package io.github.krrg.view;

import io.github.krrg.Individual;
import io.github.krrg.World;

/**
 * Created by krr428 on 12/8/15.
 */
public class HeadlessWorldView {

    private final World world;

    public HeadlessWorldView(World world) {
        this.world = world;
    }

    public void handleAutoTick() {
        for (int round = 0; handleTick(); round++) {
            System.out.println("Round " + round);
        }
    }

    public boolean handleTick() {

        // Fork
        this.world.getPopulation().parallelStream().forEach((Individual i) -> {
            this.world.getPopulation().stream().forEach((Individual other) -> {
                if (i == other) {
                    return;
                }
                i.getInfectionModel().exposeTo(other);
            });
        });
        // Join


        // Fork
        this.world.getPopulation().parallelStream().forEach((Individual i) -> {
            i.getInfectionModel().tick();
        });
        // Join

        int oldNumDead = world.getNumDead();
        int oldNumImmune = world.getNumImmune();
        int oldNumInfected = world.getNumInfected();

        world.updateStats();

        return !(world.getNumDead() == oldNumDead && world.getNumImmune() == oldNumImmune && world.getNumInfected() == oldNumInfected);

    }

}
