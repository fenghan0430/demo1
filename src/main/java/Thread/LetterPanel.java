package Thread;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Callable;

public class LetterPanel extends JPanel {
    int w=500, h=500;
    Letter letter;
    LetterPanel.Callable callable;
    Font font=new Font("黑体", Font.BOLD, 50);
    public LetterPanel() {
        this.setPreferredSize(new Dimension(w, h));
        callable=new Callable() {
            @Override
            public void show(){
                repaint();
            }
        };
        letter=new Letter(w, h, callable);
        letter.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(font);
        g.drawString(letter.getLetter()+"", letter.getX(), letter.getY());
    }
    public static void main(String[] args) {
        JFrame f=new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        f.setContentPane(new LetterPanel());
        f.pack();
    }
    public interface Callable{
        public void show();
    }
}
