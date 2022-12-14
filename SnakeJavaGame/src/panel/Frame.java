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
    /* Constants */
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    /* Private */
    private Paint paint;
    private JButton startButton = new JButton("START");
    private JLabel keyInput = new JLabel();
    private JPanel panel = new JPanel();
    public int snakeDir = KeyEvent.VK_UP;
    /* Public */
    public static final int FRAME_OFFSET = 32;
    public static final int MAX_X = 512;
    public static final int MAX_Y = 512;
    public FrameType currentFrame;

    public Frame()
    {
        super("Snake Game Java");
        currentFrame = START;
    }

    public void configureFrame(Paint paint)
    {
        /* Set paint object */
        this.paint = paint;
        /* Default frame configuration */
        setDefaultFrameConfiguration();
        /* Set configuration for start frame */
        configureStartFrame();
    }

    private void setDefaultFrameConfiguration()
    {
        getContentPane().setBackground(Color.BLACK); /* Background color */
        addKeyBindings();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /* Close operation */
        setVisible(true); /* Show frame */
        /* Set the final size */
        setSize(MAX_X + 2 * FRAME_OFFSET + getInsets().left + getInsets().right,
                MAX_Y + 2 * FRAME_OFFSET + getInsets().top + getInsets().bottom);
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

    public void configureStartFrame()
    {
        startButton.addActionListener(this); /* Add action when button is pressed */
        startButton.setBackground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.PLAIN, 40));
        startButton.setFocusable(false); /* To not show the border */
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        panel.add(startButton);
        add(panel);
        setVisible(true); /* Show frame */
    }


    public void configureGameFrame()
    {
        panel.setVisible(false); /* Hide button */
        add(this.paint); /* Add snake object */
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        currentFrame = Frame.FrameType.GAME;
    }

    private class SetDirectionClass extends AbstractAction
    {
        int keyEvent;

        SetDirectionClass(int keyEvent)
        {
            this.keyEvent = keyEvent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if ((keyEvent == KeyEvent.VK_UP) || (keyEvent == KeyEvent.VK_RIGHT)
                    || (keyEvent == KeyEvent.VK_LEFT) || (keyEvent == KeyEvent.VK_DOWN))
            {
                /* Check whether the following movement is transverse */
                if ((snakeDir != keyEvent + 2) && (snakeDir != keyEvent - 2))
                {
                    snakeDir = keyEvent;
                }
            }
            else
            {
                /* Do nothing */
            }
        }
    }
}
