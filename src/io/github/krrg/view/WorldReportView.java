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
        this.txtNumDead.setText("Dead: " + world.getNumDead());
        this.txtNumImmune.setText("Immune: " + world.getNumImmune());
        this.txtNumInfected.setText("Infected:" + world.getNumInfected());
        this.txtTotalPopulation.setText("Population Size: " + world.getTotal());
    }

    private void scheduleUpdates() {
        new Timer(1000, actionEvent -> {
           updateText();
        }).start();
    }

}
