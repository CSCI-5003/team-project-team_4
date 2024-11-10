package oosd.view;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

public class WordGrid extends JPanel {
    
    private JButton[] buttons = new JButton[16];
    
    public WordGrid() {
        
        Color lightGray = new Color(239, 239, 230);
        int width = 130;
        int height = 95;
        int[] x = new int[]{55,195,335,475,55,195,335,475,55,195,335,475,55,195,335,475};
        int[] y = new int[]{25,25,25,25,130,130,130,130,235,235,235,235,340,340,340,340};

        for (int i = 0; i < buttons.length; i++) {
            String word = "Word " + i;
            buttons[i] = new JButton(String.valueOf(word));
            buttons[i].setEnabled(true);
            buttons[i].setBounds(x[i], y[i], width, height);
            buttons[i].setOpaque(true);
            buttons[i].setBorderPainted(false);
            buttons[i].setBackground(lightGray);

            this.add(buttons[i]);
        }

    }
}
