package GUI_practice;

import javax.swing.JFrame;

public class MyJFrame extends JFrame {
    public MyJFrame(String title) {
        super(title);
        // MyJPanel panel = new MyJPanel();
        // setContentPane(panel);
        setSize(750, 750);
        setLocation(850, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
