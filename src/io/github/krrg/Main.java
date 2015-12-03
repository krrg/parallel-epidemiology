package io.github.krrg;

import io.github.krrg.view.WorldView;

import javax.swing.*;

/**
 * Created by krr428 on 11/28/15.
 */
public class Main {

    public static void main(String[] args) {
        World world = WorldFactory.createWorld();

        SwingUtilities.invokeLater(() -> {
            JFrame jf = new JFrame();
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setTitle("krrg/parallel-epidemiology");
            jf.getContentPane().add(new WorldView(world));
            jf.pack();
            jf.setVisible(true);
        });

    }

}
