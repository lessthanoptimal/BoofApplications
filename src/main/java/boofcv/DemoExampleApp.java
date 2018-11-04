package boofcv;

import boofcv.examples.ExampleLauncherApp;
import boofcv.gui.BoofLogo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

        // set a border so that the window is distinctive in Windows
        setBorder(BorderFactory.createLineBorder(Color.GRAY));

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

        addKeyListener(new ListenQuit());

        frame = new JFrame("BoofCV Launcher");
        frame.add(this, BorderLayout.CENTER);
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);

        // Don't start animating it until it's shown. On windows the initial opening
        // from a jar can be very slow
        logo.setRadius(1);
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                logo.animate(4000);
            }
        });

        frame.setVisible(true);
        requestFocus();
    }

    public class ListenQuit implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {
            if( e.isAltDown() || e.isControlDown() ) {
                if( e.getKeyCode() == KeyEvent.VK_Q ) {
                    System.exit(0);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DemoExampleApp::new);
    }
}
