package boofcv;

import boofcv.examples.ExampleLauncherApp;

import javax.swing.*;
import java.awt.*;

/**
 * @author Peter Abeles
 */
public class DemoExampleApp extends JPanel {

    JFrame frame;

    public DemoExampleApp() {
        SpringLayout layout = new SpringLayout();
        setLayout(layout);

        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(620,400));

        BoofLogo logo = new BoofLogo();

        layout.putConstraint(SpringLayout.WEST, logo, 0, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, logo, 0, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, logo, 0, SpringLayout.NORTH, this);

        JButton buttonExample = new JButton("Examples");
        JButton buttonDemo = new JButton("Demonstrations");

        // launch example of demonstrations when the buttons are pressed
        buttonExample.addActionListener(e->{
            frame.setVisible(false);
            ExampleLauncherApp.main(new String[0]);
        });

        buttonDemo.addActionListener(e->{
            frame.setVisible(false);
            DemonstrationLauncherApp.main(new String[0]);
        });

        // make button sizes uniform for visual aesthetics
        buttonExample.setPreferredSize(new Dimension(150,30));
        buttonDemo.setPreferredSize(new Dimension(150,30));

        // position the buttons
        layout.putConstraint(SpringLayout.WEST, buttonExample, 50, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, buttonExample, 40, SpringLayout.SOUTH, logo);

        layout.putConstraint(SpringLayout.EAST, buttonDemo, -50, SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, buttonDemo, 40, SpringLayout.SOUTH, logo);

        add(logo);
        add(buttonDemo);
        add(buttonExample);

        logo.animate(2000);
        frame = new JFrame("BoofCV Launcher");
        frame.add(this, BorderLayout.CENTER);
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            DemoExampleApp app = new DemoExampleApp();
        });
    }
}
