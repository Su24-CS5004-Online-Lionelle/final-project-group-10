package student.controller;

import student.model.Character;
import student.model.ICharacter;
import student.model.formatters.DataFormatter;
import student.model.formatters.Formats;
import student.view.JFrameView;

import java.util.List;

// feel free to rename
public class CharacterController {

    private ICharacter character;

    /** The format to output. */
    private Formats format = Formats.TXT;

    private JFrameView view;

    public CharacterController(JFrameView view) {
        this.view = view;
    }




}
