import javax.swing.*;
import java.awt.*;

public class Editor extends JTextArea {

    Editor(Dimension dimension) {
        super();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setPreferredSize(dimension);
    }

}
