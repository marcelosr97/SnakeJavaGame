import javax.swing.*;

import panel.Snake;
import panel.KeyListenerImpl;
import panel.UpdatePosition;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Main
{
    public static void main(String[] args) {
        /* Create frame */
        JFrame frame = new JFrame();

        /* Create snake object */
        Snake snake = new Snake();

        /* Create key listener */
        KeyListenerImpl keyInputs = new KeyListenerImpl(snake);

        /* Configuring frame */
        frame.getContentPane().setBackground(Color.BLACK);
        frame.add(snake); /* Add snake object */
        frame.addKeyListener(keyInputs);
        frame.setSize(1024, 1024); /* Set size */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /* Close operation */
        frame.setVisible(true); /* Show frame */

        /* Create Update Position Thread */
        UpdatePosition updatePositionThread = new UpdatePosition(snake);

        updatePositionThread.start();

    }
}
