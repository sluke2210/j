import javax.swing.*;
import java.awt.*;
public class JardFrame extends JFrame {

    public JardFrame() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout( panel, BoxLayout.X_AXIS));
        panel.add(new Jard());
        panel.add(new ColorSwitcher());
        // System.out.println("jardframe called");
        this.setResizable(false);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("a jframe");
        this.pack();
        this.setVisible(true);

    }
}





//    public static void main() {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new JardFrame();
//            }
//        });
//    }


