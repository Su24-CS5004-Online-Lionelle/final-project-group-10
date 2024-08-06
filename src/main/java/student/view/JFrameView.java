package student.view;
import javax.swing.*;


import student.Settings;
import student.controller.CharacterController;

import java.awt.*;

// feel free to rename
public class JFrameView extends JFrame implements IView {

    private static JFrameView instance;
    private static final Settings SETTINGS = Settings.getInstance();
    private CharacterController controller;
    private Menu menu;
    private JTextField search_field;
    private Button search_button;
    private Button export_button;
    private JFileChooser file_chooser;
    private JTextArea display_area;

    public JFrameView(CharacterController controller){
        super(SETTINGS.CAPTION);
        this.controller = controller;
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu = new Menu();

        createWindowAndButtons();
        addComponents();
    }

    private void createWindowAndButtons() {
        search_field = new JTextField(SETTINGS.SEARCH);
        display_area = new JTextArea(20, 50);
        display_area.setEditable(false);
        file_chooser = new JFileChooser();

        /** placeholder name for menu dropdowns*/
        search_button = new Button(Button.ButtonType.SEARCH, controller, search_field,
                menu.GETGENDER(), menu.GETSTATUS(), menu.GETSPECIES(), menu.GETSORT());

        /** placeholder name for menu dropdowns*/
        export_button = new Button(Button.ButtonType.EXPORT, controller, search_field,
                menu.GETGENDER(), menu.GETSTATUS(), menu.GETSPECIES(), menu.GETSORT());
    }

    private void addComponents() {
        setLayout(new BorderLayout());
        add(search_field);
        add(search_button);
        add(export_button);
        add(display_area);
        add(new JLabel(SETTINGS.GENDER));
        add(menu.getGenderBox());
        add(new JLabel(SETTINGS.STATUS));
        add(menu.getSpeciesBox());
        add(new JLabel(SETTINGS.SPECIES));
        add(menu.getSortBox());
        add(new JLabel(SETTINGS.SORT));
        add(menu.getStatusBox());
    }

    /** Starts the JFrameView. */
    @Override
    public void start() {
        setVisible(true);
    }

}
