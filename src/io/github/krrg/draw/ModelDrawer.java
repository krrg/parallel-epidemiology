package io.github.krrg.draw;

import io.github.krrg.Individual;
import io.github.krrg.infections.InfectionModel;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by krr428 on 11/30/15.
 */
public class ModelDrawer {

    private Graphics2D g2d;

    public ModelDrawer(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public void plotIndividual(Individual individual) {
        final double radius = 16;
        final double gridx = individual.getPosition().getX() - 0.5 * radius;
        final double gridy = individual.getPosition().getY() - 0.5 * radius;

        Shape marker = new Ellipse2D.Double(gridx, gridy, radius, radius);

        if (individual.getInfectionModel().isInfected()) {
            g2d.setColor(Color.orange);
        }
        else if (individual.getInfectionModel().isContagious()) {
            g2d.setColor(Color.red);
        }
        else {
            g2d.setColor(Color.green);
        }

        g2d.fill(marker);
    }

}
