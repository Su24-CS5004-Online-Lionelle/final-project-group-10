package student.view;

import javax.swing.*;
import student.Settings;
import student.controller.CharacterController;
import student.model.ICharacter;

import java.awt.*;
import java.util.List;

// feel free to rename
public class JFrameView extends JFrame implements IView {

    private static JFrameView instance;
    private static final Settings SETTINGS = Settings.getInstance();
    private CharacterController controller;
    private Menu menu;
    private JTextField search_field;
    private Button search_button;
    private Button export_button;
    private Button next_button;
    private Button previous_button;
    private JFileChooser file_chooser;
    private JPanel display_area;
    private JPanel resultsPanel;
    private JScrollPane scrollPane;
    private JPanel prevNextPanel;

    public JFrameView(CharacterController controller){
        super(SETTINGS.CAPTION);
        this.controller = controller;
        this.controller.setView(this);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu = new Menu();

        createWindowAndButtons();
        addComponents();
        pack();
        setSize(600, 600);
    }

    public static JFrameView getInstance(CharacterController controller) {
        if (instance == null) {
            instance = new JFrameView(controller);
        }
        return instance;
    }

    private void createWindowAndButtons() {
        search_field = new JTextField(24);
        display_area = new JPanel();
        display_area.setLayout(new BoxLayout(display_area, BoxLayout.Y_AXIS));
        file_chooser = new JFileChooser();

        search_button = new Button(Button.ButtonType.SEARCH, controller, search_field,
                menu.getGenderBox(), menu.getStatusBox(), menu.getSpeciesBox(), menu.getSortBox());
        search_button.setText(SETTINGS.SEARCH);

        export_button = new Button(Button.ButtonType.EXPORT, controller, search_field,
                menu.getGenderBox(), menu.getStatusBox(), menu.getSpeciesBox(), menu.getSortBox());
        export_button.setText(SETTINGS.EXPORT);

        next_button = new Button(Button.ButtonType.NEXT, controller, search_field,
                menu.getGenderBox(), menu.getStatusBox(), menu.getSpeciesBox(), menu.getSortBox());
        next_button.setText(SETTINGS.NEXT);
        next_button.setVisible(false);

        previous_button = new Button(Button.ButtonType.PREVIOUS, controller, search_field,
                menu.getGenderBox(), menu.getStatusBox(), menu.getSpeciesBox(), menu.getSortBox());
        previous_button.setText(SETTINGS.PREVIOUS);
        previous_button.setVisible(false);
    }

    private void addComponents() {
        setLayout(new BorderLayout());
        JPanel northPanel = new JPanel(new GridLayout(5, 2, 0, 0));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.add(search_field);
        searchPanel.add(search_button);
        northPanel.add(searchPanel);

        JPanel filterPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filterPanel1.add(new JLabel(SETTINGS.GENDER));
        filterPanel1.add(menu.getGenderBox());
        filterPanel1.add(new JLabel(SETTINGS.STATUS));
        filterPanel1.add(menu.getStatusBox());
        northPanel.add(filterPanel1);

        JPanel filterPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filterPanel2.add(new JLabel(SETTINGS.SPECIES));
        filterPanel2.add(menu.getSpeciesBox());
        filterPanel2.add(new JLabel(SETTINGS.SORT));
        filterPanel2.add(menu.getSortBox());
        northPanel.add(filterPanel2);

        JPanel exportPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exportPanel.add(export_button);
        northPanel.add(exportPanel);

        add(northPanel, BorderLayout.NORTH);

        resultsPanel = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane(display_area);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);

        prevNextPanel = new JPanel(new GridLayout(1, 2));
        prevNextPanel.add(previous_button);
        prevNextPanel.add(next_button);
        resultsPanel.add(prevNextPanel, BorderLayout.SOUTH);

        add(resultsPanel, BorderLayout.CENTER);
    }

    public void displayResults(List<ICharacter.CharacterRecord> characters) {
        display_area.removeAll();
        for (ICharacter.CharacterRecord character : characters) {
            JPanel characterPanel = new JPanel();
            characterPanel.setLayout(new BoxLayout(characterPanel, BoxLayout.X_AXIS));
            characterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel imageLabel = new JLabel(controller.getModel().getImageIcon(character));
            imageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            textArea.setBackground(getBackground());
            textArea.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

            String textResults = controller.txtPrint(character);
            textArea.setText(textResults);

            characterPanel.add(imageLabel);
            characterPanel.add(textArea);
            display_area.add(characterPanel);
        }
        display_area.revalidate();
        display_area.repaint();

        resetScrollBar();
    }

    public void toggleNextButton(boolean hasNext) {
        next_button.setVisible(hasNext);
        prevNextPanel.revalidate();
        prevNextPanel.repaint();
    }

    public void togglePrevButton(boolean hasPrev) {
        previous_button.setVisible(hasPrev);
        prevNextPanel.revalidate();
        prevNextPanel.repaint();
    }

    /** Resets the scrollbar to the top. */
    private void resetScrollBar() {
        scrollPane.getVerticalScrollBar().setValue(0);
    }

    /** Starts the JFrameView. */
    @Override
    public void start() {
        setVisible(true);
    }

}
