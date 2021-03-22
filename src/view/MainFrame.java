package view;

import javax.swing.*;

public class MainFrame {

    JFrame window;

    public MainFrame() {

        createMainWindow();

    }

    public void createMainWindow() {
        window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Inga restriktioner på fönstret. Kan lägga överallt.
        window.setLayout(null);
        window.setTitle("Climb the Tower");
        //Startar på mitten av skärmen.
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
