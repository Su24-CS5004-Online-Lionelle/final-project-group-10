package student;

// feel free to rename

import student.controller.CharacterController;
import student.model.ICharacter;
import student.model.Character;
import student.view.JFrameView;

/**
 * Main driver for the program.
 */
public final class CharacterApp {

    /** Private constructor to prevent instantiation. */
    private CharacterApp() {
        //empty
    }
    
    /**
     * Main entry point for the program.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ICharacter model = new Character();
        CharacterController controller = new CharacterController(model);
        JFrameView view = JFrameView.getInstance(controller);
        view.start();
    }
}
