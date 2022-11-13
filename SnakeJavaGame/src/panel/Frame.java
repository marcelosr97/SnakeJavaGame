package panel;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame
{
    public final int FRAME_OFFSET = 32;
    public final int MAX_X = 512;
    public final int MAX_Y = 512;

    public JFrame jFrame;
    private Paint paint;
    private KeyListenerImpl keyInputs;

    public Frame()
    {
        this.jFrame = new JFrame();
    }
    
    public void configureFrame(Paint paint, KeyListenerImpl keyInputs)
    {
        /* Configuring frame */
        this.jFrame.getContentPane().setBackground(Color.BLACK);
        this.jFrame.add(paint); /* Add snake object */
        this.jFrame.addKeyListener(keyInputs);
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /* Close operation */
        this.jFrame.setVisible(true); /* Show frame */
        this.jFrame.setSize(MAX_X + 2*FRAME_OFFSET + this.jFrame.getInsets().left + this.jFrame.getInsets().right,
                MAX_Y + 2*FRAME_OFFSET + this.jFrame.getInsets().top + this.jFrame.getInsets().bottom);
    }
}
