import javax.swing.*;

public class GUI {
    private JButton button1;
    private JButton button1Button;
    private JTextField TESTINGRORTextField;

    GUI() {
        JFrame frame = new JFrame("User Management App");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 450);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(button1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
