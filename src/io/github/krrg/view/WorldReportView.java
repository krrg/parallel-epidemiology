package io.github.krrg.view;

import io.github.krrg.World;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * Created by krr428 on 12/3/15.
 */
public class WorldReportView extends JPanel {

    private JLabel txtNumInfected;
    private JLabel txtNumImmune;
    private JLabel txtTotalPopulation;
    private JLabel txtNumDead;
    final private World world;

    public WorldReportView(World world) {
        this.world = world;

        initLayout();
        initComponents();
        updateText();
        scheduleUpdates();
    }

    private void initLayout() {
        this.setLayout(new FlowLayout());
    }

    private void initComponents() {
        this.txtNumDead = new JLabel();
        this.txtNumImmune = new JLabel();
        this.txtNumInfected = new JLabel();
        this.txtTotalPopulation = new JLabel();

        Arrays.asList(txtNumDead, txtNumImmune, txtNumInfected, txtTotalPopulation).forEach(this::add);
    }

    private void updateText() {

        int numDead = world.getPopulation().parallelStream().mapToInt(individual -> {
            return individual.getInfectionModel().isDeceased() ? 1 : 0;
        }).parallel().sum();

        int numImmune = world.getPopulation().parallelStream().mapToInt(individual -> {
            return individual.getInfectionModel().isImmune() ? 1 : 0;
        }).parallel().sum();

        int numInfected = world.getPopulation().parallelStream().mapToInt(individual -> {
            return individual.getInfectionModel().isInfected() ? 1 : 0;
        }).parallel().sum();

        int numTotal = world.getPopulation().size();

        this.txtNumDead.setText("Dead: " + numDead);
        this.txtNumImmune.setText("Immune: " + numImmune);
        this.txtNumInfected.setText("Infected:" + numInfected);
        this.txtTotalPopulation.setText("Population Size: " + numTotal);
    }

    private void scheduleUpdates() {
        new Timer(1000, actionEvent -> {
           updateText();
        }).start();
    }

}
