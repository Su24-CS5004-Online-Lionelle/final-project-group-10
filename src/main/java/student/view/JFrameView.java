package student.view;
import javax.swing.*;


import student.controller.CharacterController;

// feel free to rename
public class JFrameView extends JFrame{

    private CharacterController controller;

    public JFrameView(CharacterController controller){
        setTitle("CharacterApp");
        this.controller = controller;

    }

    public void start() {

    }

}
