import javax.swing.*;
import java.awt.*;

public class ToolBar extends JMenuBar {

    protected JMenuItem newMenu, newWindow, openMenu, saveMenu, exitMenu;
    protected JMenuItem cut, copy, paste;
    protected JMenuItem about;

    private void initItems () {
        newMenu = new JMenuItem("New");
        newWindow = new JMenuItem("New Window");
        openMenu = new JMenuItem("Open");
        saveMenu = new JMenuItem("Save");
        exitMenu = new JMenuItem("Exit");

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
    }

    public ToolBar () {
        initItems();

        JMenuItem[] fileMenuItems = {newMenu, newWindow,openMenu, saveMenu, exitMenu};
        Menu fileMenu = new Menu("File", fileMenuItems);
        add(fileMenu);

        Menu editMenu = new Menu("Edit", new JMenuItem[]{cut, copy, paste});
        add(editMenu);

        about = new JMenuItem("About");
        add(about);
        about.addActionListener(e -> {
            showAboutWindow();
        });
    }

    private void showAboutWindow() {

        JFrame frame = new JFrame("About Notepad");
        String aboutText = "Notepad - made with Java\n" + System.lineSeparator() +
                " in IJ Idea";
        String by = " - by Shubham";

        JLabel label = new JLabel(aboutText);
        JLabel byLabel = new JLabel(by);

        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font("Poppins", Font.PLAIN, 20));

        byLabel.setHorizontalAlignment(JLabel.RIGHT);
        byLabel.setVerticalAlignment(JLabel.BOTTOM);
        byLabel.setFont(new Font("Poppins", Font.ITALIC, 12));

        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(byLabel);
        panel.setPreferredSize(new Dimension(370, 200));

        frame.add(panel);
        frame.pack();
        frame.setMaximumSize(new Dimension(370, 200));
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}

class Menu extends JMenu {
    Menu (String name, JMenuItem[] items) {
        super(name);
        for (JMenuItem item: items)
            add(item);
    }
}
