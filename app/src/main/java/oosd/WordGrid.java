package oosd;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

public class WordGrid extends JPanel {
    
    private JButton[] buttons = new JButton[16];
    
    public WordGrid() {
        
        Color lightGray = new Color(239, 239, 230);
        int width = 130;
        int height = 95;
        int[] x = new int[]{75,215,355,495,75,215,355,495,75,215,355,495,75,215,355,495};
        int[] y = new int[]{95,95,95,95,200,200,200,200,305,305,305,305,410,410,410,410};

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
