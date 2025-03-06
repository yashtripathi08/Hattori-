package main;

import javax.swing.JFrame;

public class main {

    public static JFrame window;
    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("HATTORI");
       // window.setUndecorated(true);

        gamepanel gamepanel = new gamepanel();
        window.add(gamepanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamepanel.setUpGame();
        gamepanel.startGameThread();
    }
}
