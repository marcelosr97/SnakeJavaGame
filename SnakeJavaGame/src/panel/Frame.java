package panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static panel.Frame.FrameType.START;

public class Frame extends JFrame implements ActionListener {
    /* Enum */
    public enum FrameType {START, GAME, END}

    /* Private */
    private Paint paint;
    private JButton startButton;
    public int snakeDir = KeyEvent.VK_UP;
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    /* Public */
    public final int FRAME_OFFSET = 32;
    public final int MAX_X = 512;
    public final int MAX_Y = 512;
    public FrameType currentFrame;
    private static JLabel keyInput = new JLabel();

    public Frame() {
        this.currentFrame = START;
    }

    public void configureFrame(Paint paint) {
        /* Set paint object */
        this.paint = paint;
        /* Default frame configuration */
        setDefaultFrameConfiguration();
        /* Set configuration for start frame */
        configureStartFrame();
    }

    private void setDefaultFrameConfiguration() {
        this.getContentPane().setBackground(Color.BLACK); /* Background color */
        addKeyBindings();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /* Close operation */
        this.setVisible(true); /* Show frame */
        /* Set the final size */
        this.setSize(MAX_X + 2 * FRAME_OFFSET + this.getInsets().left + this.getInsets().right,
                MAX_Y + 2 * FRAME_OFFSET + this.getInsets().top + this.getInsets().bottom);
    }

    private void addKeyBindings()
    {
        keyInput.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed UP"), "Move up");
        keyInput.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed DOWN"),"Move down");
        keyInput.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed LEFT"),"Move left");
        keyInput.getInputMap(IFW).put(KeyStroke.getKeyStroke("pressed RIGHT"),"Move right");
        keyInput.getActionMap().put("Move up", new SetDirectionClass(KeyEvent.VK_UP));
        keyInput.getActionMap().put("Move down", new SetDirectionClass(KeyEvent.VK_DOWN));
        keyInput.getActionMap().put("Move left", new SetDirectionClass(KeyEvent.VK_LEFT));
        keyInput.getActionMap().put("Move right", new SetDirectionClass(KeyEvent.VK_RIGHT));
        this.add(keyInput);

    }

    public void configureStartFrame() {
        this.startButton = new JButton("START");
        this.startButton.addActionListener(this);
        this.startButton.setFocusable(false);
        this.add(this.startButton);
        this.setVisible(true); /* Show frame */
    }


    public void configureGameFrame() {
        this.startButton.setVisible(false); /* Hide button */
        this.add(this.paint); /* Add snake object */
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.currentFrame = Frame.FrameType.GAME;
    }

    private class SetDirectionClass extends AbstractAction {
        int keyEvent;

        SetDirectionClass(int keyEvent) {
            this.keyEvent = keyEvent;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if ((keyEvent == KeyEvent.VK_UP) || (keyEvent == KeyEvent.VK_RIGHT)
                    || (keyEvent == KeyEvent.VK_LEFT) || (keyEvent == KeyEvent.VK_DOWN)) {
                /* Check whether the following movement is transverse */
                if ((snakeDir != keyEvent + 2) && (snakeDir != keyEvent - 2)) {
                    snakeDir = keyEvent;
                }
            } else {
                /* Do nothing */
            }
        }
    }
}
