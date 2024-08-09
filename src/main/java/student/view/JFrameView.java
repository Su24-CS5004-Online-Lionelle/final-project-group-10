package student.view;

import javax.swing.*;

import student.Settings;
import student.controller.CharacterController;
import student.model.ICharacter;

import java.awt.*;
import java.util.List;

/**
 * Class to represent the JFrame view of the application.
 * Extends JFrame class.
 * Implements the IView interface.
 */
public class JFrameView extends JFrame implements IView {
    /**
     * The instance of the JFrameView.
     */
    private static JFrameView instance;
    /**
     * The Setting object for the application.
     * Used to get information like font, font size, and String properties for Buttons and JLabels.
     */
    private static final Settings SETTINGS = Settings.getInstance();
    /**
     * Tbe controller for the application.
     */
    private CharacterController controller;
    /**
     * Dropdown boxes for the filters.
     */
    private Menu menu;
    /**
     * The search bar for the user to input a character name to search.
     */
    private JTextField searchField;
    /**
     * The search button to search for a character.
     */
    private Button searchButton;
    /**
     * The export file button to export search results to a file.
     */
    private Button exportButton;
    /**
     * The next button for the next page of results.
     */
    private Button nextButton;
    /**
     * The previous button for the previous page of results.
     */
    private Button previousButton;
    /**
     * The file chooser to save files.
     */
    private JFileChooser fileChooser;
    /**
     * The JPanel to display the search resuts.
     */
    private JPanel displayArea;
    /**
     * The JPanel that holds the display area and next and previous buttons.
     */
    private JPanel resultsPanel;
    /**
     * Scrollpane for the display area to scroll through results.
     */
    private JScrollPane scrollPane;
    /**
     * The JPanel that holds the next and previous buttons.
     */
    private JPanel prevNextPanel;

    /**
     * Constructor for the JFrameView class.
     * Intializes the JFrame with the controller.
     *
     * @param controller the controller for the application.
     */
    public JFrameView(CharacterController controller) {
        super(SETTINGS.getCaption());
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

    /**
     * Getter method to retrieve an instance of the JFrameView class.
     *
     * @param controller the controller for the application.
     * @return the instance of the JFrameView.
     */
    public static JFrameView getInstance(CharacterController controller) {
        if (instance == null) {
            instance = new JFrameView(controller);
        }
        return instance;
    }

    /**
     * Creates the buttons, menus, and display area for the JFrame.
     *
     * @see Button#Button()
     * @see ButtonListener#actionPerformed(ActionEvent e)
     */
    private void createWindowAndButtons() {
        searchField = new JTextField(24);
        displayArea = new JPanel();
        displayArea.setLayout(new BoxLayout(displayArea, BoxLayout.Y_AXIS));
        fileChooser = new JFileChooser();

        searchButton = new Button(Button.ButtonType.SEARCH, controller, searchField,
                menu.getGenderBox(), menu.getStatusBox(), menu.getSpeciesBox(), menu.getSortBox());
        searchButton.setText(SETTINGS.getSearch());

        exportButton = new Button(Button.ButtonType.EXPORT, controller, searchField,
                menu.getGenderBox(), menu.getStatusBox(), menu.getSpeciesBox(), menu.getSortBox());
        exportButton.setText(SETTINGS.getExport());

        nextButton = new Button(Button.ButtonType.NEXT, controller, searchField,
                menu.getGenderBox(), menu.getStatusBox(), menu.getSpeciesBox(), menu.getSortBox());
        nextButton.setText(SETTINGS.getNext());
        nextButton.setVisible(false);

        previousButton = new Button(Button.ButtonType.PREVIOUS, controller, searchField,
                menu.getGenderBox(), menu.getStatusBox(), menu.getSpeciesBox(), menu.getSortBox());
        previousButton.setText(SETTINGS.getPrevious());
        previousButton.setVisible(false);
    }

    /**
     * Create JPanels and adds the buttons, menus, and display area to the respective panels.
     *
     * @see Menu#Menu()
     */
    private void addComponents() {
        setLayout(new BorderLayout());
        JPanel northPanel = new JPanel(new GridLayout(5, 2, 0, 0));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        northPanel.add(searchPanel);

        JPanel filterPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filterPanel1.add(new JLabel(SETTINGS.getGender()));
        filterPanel1.add(menu.getGenderBox());
        filterPanel1.add(new JLabel(SETTINGS.getStatus()));
        filterPanel1.add(menu.getStatusBox());
        northPanel.add(filterPanel1);

        JPanel filterPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        filterPanel2.add(new JLabel(SETTINGS.getSpecies()));
        filterPanel2.add(menu.getSpeciesBox());
        filterPanel2.add(new JLabel(SETTINGS.getSort()));
        filterPanel2.add(menu.getSortBox());
        northPanel.add(filterPanel2);

        JPanel exportPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        exportPanel.add(exportButton);
        northPanel.add(exportPanel);

        add(northPanel, BorderLayout.NORTH);

        resultsPanel = new JPanel(new BorderLayout());
        scrollPane = new JScrollPane(displayArea);
        resultsPanel.add(scrollPane, BorderLayout.CENTER);

        prevNextPanel = new JPanel(new GridLayout(1, 2));
        prevNextPanel.add(previousButton);
        prevNextPanel.add(nextButton);
        resultsPanel.add(prevNextPanel, BorderLayout.SOUTH);

        add(resultsPanel, BorderLayout.CENTER);
    }

    /**
     * @param characters
     */
    public void displayResults(List<ICharacter.CharacterRecord> characters) {
        displayArea.removeAll();
        if (characters.isEmpty()) {
            JTextArea errorArea = new JTextArea();
            errorArea.setEditable(false);
            errorArea.setBackground(getBackground());
            errorArea.setLineWrap(true);
            errorArea.setText("                            No Results Found." +
                    "\n                            Please try again.");
            displayArea.add(errorArea);
        } else {
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
                displayArea.add(characterPanel);
            }
        }
        displayArea.revalidate();
        displayArea.repaint();

        resetScrollBar();
    }

    /**
     * Set the next button to be visible if there is a next page to load.
     *
     * @param hasNext a boolean value, returns true if there is a next page to load, false otherwise
     */
    public void toggleNextButton(boolean hasNext) {
        nextButton.setVisible(hasNext);
        prevNextPanel.revalidate();
        prevNextPanel.repaint();
    }

    /**
     * Set the previous button to be visible if there is a previous page to load.
     *
     * @param hasPrev boolean, returns true if there is a previous page to load, false otherwise
     */
    public void togglePrevButton(boolean hasPrev) {
        previousButton.setVisible(hasPrev);
        prevNextPanel.revalidate();
        prevNextPanel.repaint();
    }

    /**
     * Resets the scrollbar to the top.
     */
    private void resetScrollBar() {
        scrollPane.getVerticalScrollBar().setValue(0);
    }

    /**
     * Starts the JFrameView.
     */
    @Override
    public void start() {
        setVisible(true);
    }

}
