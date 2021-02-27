import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;

public class Main {

    public JFrame frame = null;
    ToolBar toolBar;
    Editor editor;
    JFileChooser fileChooser = null;
    FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
    Font defaultFont = new Font("Poppins", Font.PLAIN, 18);
    Event event;

    Main () {
        frame = new JFrame("Notepad");
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
            e.printStackTrace();
        }
        toolBar = new ToolBar();
        initEditor();
        initMainWindow(toolBar);
        initButtonEvents();
    }

    private void initEditor() {
        editor = new Editor(new Dimension(500, 400));
        editor.setFont(defaultFont);
        event = new Event(editor, frame);
        addToMainWindow(editor);
    }

    private void initMainWindow(ToolBar toolBar) {

        frame.setJMenuBar(toolBar);
        frame.setSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void initButtonEvents() {
        exitButtonEvent();
        OpenButtonEvent();
        saveButtonEvent();
        newButtonEvent();
        editButtonEvent();
        toolBar.newWindow.addActionListener(e -> {
            this.newInstance();
        });
    }

    private void editButtonEvent() {
        toolBar.cut.addActionListener(event);
        toolBar.copy.addActionListener(event);
        toolBar.paste.addActionListener(event);
    }

    private void newButtonEvent() {
        toolBar.newMenu.addActionListener(e -> {
             if (!editor.getText().equals("")) {
                 String[] options = {"Save", "Don't save", "Cancel"};
                 int result = JOptionPane.showOptionDialog(
                         frame,
                         "Exit without Saving ?",
                         "Exit ?",
                         JOptionPane.YES_NO_OPTION,
                         JOptionPane.QUESTION_MESSAGE,
                         null,
                         options,
                         options[0]
                 );

                 if (result == JOptionPane.YES_OPTION)
                     event.saveFile();
                 else if (result == JOptionPane.NO_OPTION)
                     event.startNewWindow(frame);

             } else {
                event.startNewWindow(frame);
             }
        });
    }

    private void newInstance() {
        new Main();
    }

    private void saveButtonEvent() {
        toolBar.saveMenu.addActionListener(e -> {
            event.saveFile();
        });
    }

    private void exitButtonEvent() {
        toolBar.exitMenu.addActionListener(e -> {

            if (editor.getText().equals("")) {
                event.exit();
            }
            else{
                String[] options = {"Save", "Exit"};
                int result = JOptionPane.showOptionDialog(
                        frame,
                        "Exit without Saving ?",
                        "Exit ?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );
                if (result == JOptionPane.YES_OPTION)
                    event.saveFile();
                else if (result == JOptionPane.NO_OPTION)
                    event.exit();
            }
        });
    }

    private void OpenButtonEvent() {
        toolBar.openMenu.addActionListener(e -> {
            event.openFile();
        });
    }

    private void addToMainWindow(Component comp) {
        frame.add(comp);
    }

    public static void main(String[] args) {
        new Main();
    }
}
