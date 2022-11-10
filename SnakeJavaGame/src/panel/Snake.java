package panel;

/* Imports */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class Snake extends JComponent{
    int x = 512;
    int y = 512;
    int direction = KeyEvent.VK_UP;

    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(this.x, this.y, 32, 32);
    }

    public void moveSnake(KeyEvent e) {
        if((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_RIGHT)
            || (e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_DOWN))
        {
            this.direction = e.getKeyCode();
        }
        else
        {
            /* Do nothing */
        }
    }
}
