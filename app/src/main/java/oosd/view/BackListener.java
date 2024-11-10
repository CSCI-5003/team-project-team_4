package oosd.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class BackListener implements ActionListener {

    JFrame oldFrame;
    JFrame newFrame;

    public void setNewFrame(JFrame newFrame) {
        this.newFrame = newFrame;
    }

    public void setOldFrame(JFrame oldFrame) {
        this.oldFrame = oldFrame;
    }

    public JFrame getNewFrame() {
        return newFrame;
    }

    public JFrame getOldFrame() {
        return oldFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        oldFrame.setVisible(false);
        newFrame.setVisible(true);
    }

}
