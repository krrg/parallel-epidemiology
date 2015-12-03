package io.github.krrg.view;

import io.github.krrg.Individual;
import io.github.krrg.World;
import io.github.krrg.draw.ModelDrawer;
import io.github.krrg.geom.Position;
import io.github.krrg.geom.PositionGenerator;
import sun.awt.image.OffScreenImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by krr428 on 12/2/15.
 */
public class WorldView extends JPanel {

    private class WorldController {

        private World world;

        public WorldController(World world) {
            this.world = world;
        }

        public void handleTick() {

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
        }

        public void handleDraw(Graphics2D g2d) {
            ModelDrawer modelDrawer = new ModelDrawer(g2d);
            this.world.getPopulation().stream().forEach(modelDrawer::plotIndividual);
        }

    }

    private WorldController controller;

    public WorldView (final World world) {
        controller = new WorldController(world);

        this.initLayout();
        this.initComponents();
    }

    private JPanel diseasePanel;
    private JPanel bottomPanel;

    private void initLayout() {
        this.setLayout(new BorderLayout());
    }

    private void initComponents() {
        diseasePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                BufferedImage bi = new BufferedImage(PositionGenerator.MAX_DIM, PositionGenerator.MAX_DIM, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = bi.createGraphics();
                g2d.setBackground(Color.black);
                controller.handleDraw(bi.createGraphics());

                g.drawImage(bi, 0, 0, 1024, 1024, null);
            }
        };
        bottomPanel = new JPanel();

        diseasePanel.setLayout(null);
        diseasePanel.setPreferredSize(new Dimension(1024, 1024));
        diseasePanel.setMinimumSize(new Dimension(1024, 1024));
//        diseasePanel.setBackground(Color.BLACK);

        bottomPanel.setLayout(new FlowLayout());
        JButton tickButton = new JButton();
        tickButton.setText("Single Tick");
        tickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                controller.handleTick();
                diseasePanel.repaint();
            }
        });
        bottomPanel.add(tickButton);

        this.add(diseasePanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }


}
