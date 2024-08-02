package student;

// feel free to rename

import student.controller.CharacterController;
import student.model.ICharacter;
import student.model.Character;
import student.view.JFrameView;

/**
 * Main driver for the program.
 */
public class CharacterApp {
    public static void main(String[] args) {
        ICharacter model = new Character();
        CharacterController controller = new CharacterController(model);
        JFrameView view = new JFrameView(controller);
        view.start();
    }
}
