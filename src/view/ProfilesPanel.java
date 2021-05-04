package view;

import javax.swing.*;
import java.awt.*;

public class ProfilesPanel extends JPanel {
    private MainFrame mainFrame;

    private JList<String> profiles;


    public ProfilesPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        createPanel();
    }

    public void createPanel() {
        setLayout(new BorderLayout());
        setBounds(100, 250, 200, 300);

        profiles = new JList<>();
        JScrollBar jScrollBar = new JScrollBar(Adjustable.VERTICAL);
        profiles.setBounds(0, 0, 200, 100);


        profiles.add(jScrollBar);
        add(profiles);
        setVisible(false);
    }
}
