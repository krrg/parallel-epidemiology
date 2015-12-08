package io.github.krrg.infections;

import io.github.krrg.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by krr428 on 12/3/15.
 */
public abstract class IterativeInfectionModel extends InfectionModel {

    private List<Runnable> mutations;

    public IterativeInfectionModel(Individual individual, boolean infected) {
        super(individual, infected);
        mutations = new ArrayList<>();
    }

    @Override
    public void tick() {
        mutations.forEach(r -> {
            r.run();
        });
        mutations.clear();
    }

    protected void addMutation(Runnable r) {
        mutations.add(r);
    }


}
