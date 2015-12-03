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
        final double radius = 12;
        final double gridx = individual.getPosition().getX() - 0.5 * radius;
        final double gridy = individual.getPosition().getY() - 0.5 * radius;

        Shape marker = new Ellipse2D.Double(gridx, gridy, radius, radius);

        final InfectionModel model = individual.getInfectionModel();

        if (model.isDeceased()) {
            g2d.setColor(Color.gray);
        }
        else{
            if (model.isImmune()) {
                g2d.setColor(Color.cyan);
            }
            else if (model.isContagious()) {
                g2d.setColor(Color.red);
            }
            else if (model.isInfected()){
                g2d.setColor(Color.orange);
            }
            else {
                g2d.setColor(Color.green);
            }
        }

        g2d.fill(marker);
    }

}
