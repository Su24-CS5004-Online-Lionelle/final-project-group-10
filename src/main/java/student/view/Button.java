package student.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum ButtonType {
    SEARCH,
    EXPORT
}
public class Button extends JButton {
    private ButtonType bt;
    public Button(ButtonType buttonType) {
        this.bt = buttonType;
        addActionListener(new ButtonListener() {})
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch (bt) {
                case SEARCH:

                case EXPORT:

            }
        }
    }
}
