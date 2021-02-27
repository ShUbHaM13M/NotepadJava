import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Event implements ActionListener {

    JFileChooser fileChooser = null;
    FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
    Editor editor;
    JFrame frame;

    Event(Editor editor, JFrame frame) {
        this.editor = editor;
        this.frame = frame;
    }

    public void saveFile() {
        fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save File");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            if (!file.getName().endsWith(".txt"))
                file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");

            try {
                FileWriter fWriter = new FileWriter(file, false);
                BufferedWriter bWriter = new BufferedWriter(fWriter);

                bWriter.write(editor.getText());

                bWriter.flush();
                bWriter.close();

            } catch (IOException ioex) {
                JOptionPane.showMessageDialog(frame, ioex.getMessage());
            }

        }
    }

    public void startNewWindow(JFrame previousWindow) {
        previousWindow.dispose();
        new Main();
    }

    public void openFile() {
        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Select a file to choose");
        fileChooser.addChoosableFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                StringBuilder fileContent = new StringBuilder();
                String string = "";
                FileReader fReader = new FileReader(selectedFile);
                BufferedReader bReader = new BufferedReader(fReader);

                string = bReader.readLine();
                fileContent.append(string);

                while((string = bReader.readLine()) != null) {
                    fileContent.append(string).append("\n").append(string);
                }

                frame.setTitle(selectedFile.toString() + " - Notepad");
                editor.setText(fileContent.toString());

            } catch (IOException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }
    }

    public void exit() {
        frame.setVisible(false);
        frame.dispose();
        System.exit(1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonString = e.getActionCommand();

        if (buttonString.equals("Cut"))
            editor.cut();
        if (buttonString.equals("Copy"))
            editor.copy();
        if (buttonString.equals("Paste"))
            editor.paste();

    }
}
