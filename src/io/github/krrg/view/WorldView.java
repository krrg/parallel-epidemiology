package io.github.krrg.view;

import io.github.krrg.Individual;
import io.github.krrg.World;
import io.github.krrg.draw.ModelDrawer;
import io.github.krrg.geom.PositionGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.util.Date;

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

        public void handleAutoTick(Runnable repaint) {
            final int PAUSE_INTERVAL = 250;

            new Timer(PAUSE_INTERVAL, (ActionEvent e) -> {
                System.out.println("Updating at " + new Date());
                handleTick();
                repaint.run();
            }).start();
        }

        public void handleDraw(Graphics2D g2d) {
            ModelDrawer modelDrawer = new ModelDrawer(g2d);
            this.world.getPopulation().stream().forEach(modelDrawer::plotIndividual);
        }

    }

    private WorldController controller;

    public WorldView (final World world) {
        controller = new WorldController(world);
        worldReportView = new WorldReportView(world);

        this.initLayout();
        this.initComponents();
    }

    private JPanel diseasePanel;
    private JPanel bottomPanel;
    private WorldReportView worldReportView;

    private void initLayout() {
        this.setLayout(new BorderLayout());
    }

    private void initComponents() {

        final int PANEL_WIDTH = 720;
        final int PANEL_HEIGHT = 720;


        diseasePanel = new JPanel() {


            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                BufferedImage bi = new BufferedImage(PositionGenerator.MAX_DIM, PositionGenerator.MAX_DIM, BufferedImage.TYPE_INT_RGB);
                Graphics2D g2d = bi.createGraphics();
                g2d.setBackground(Color.black);
                controller.handleDraw(bi.createGraphics());

                g.drawImage(bi, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, null);
            }
        };
        bottomPanel = new JPanel();

        diseasePanel.setLayout(null);
        diseasePanel.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        diseasePanel.setMinimumSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
//        diseasePanel.setBackground(Color.BLACK);


        bottomPanel.setLayout(new FlowLayout());

        JButton tickButton = new JButton();
        tickButton.setText("Single Tick");
        tickButton.addActionListener(actionEvent -> {
            controller.handleTick();
            diseasePanel.repaint();
        });

        JButton autoTickButton = new JButton();
        autoTickButton.setText("Auto Run");
        autoTickButton.addActionListener(actionEvent -> {
            controller.handleAutoTick(diseasePanel::repaint);
        });

        bottomPanel.add(tickButton);
        bottomPanel.add(autoTickButton);
        bottomPanel.add(worldReportView);

        JScrollPane diseaseScrollPane = new JScrollPane(diseasePanel);

        this.add(diseaseScrollPane, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }


}
