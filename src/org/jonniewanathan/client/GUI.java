package org.jonniewanathan.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    final private int width = 800;
    final private int height = 800;

    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField downloadTextField;
    private JButton loginButton;
    private JButton logoutButton;
    private JButton uploadButton;
    private JButton downloadButton;
    private JLabel information;
    private JFrame frame;
    ClientLogin login;

    public static void main(String[] args) {
        new GUI();
    }

    public GUI(){
        frame = new JFrame("File Transfer Client");
        Dimension d = new Dimension(width, height);
        frame.setSize(d);

        //layoutManager
        LayoutManager flowLayout = new FlowLayout();
        frame.setLayout(flowLayout);

        information = new JLabel();
        frame.add(information);

        //Login Components
        JLabel usernameLabel = new JLabel("Username");
        usernameTextField = new JTextField(12);
        JLabel passwordLabel = new JLabel("password");
        passwordTextField = new JTextField(12);
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        logoutButton = new JButton("Logout");


        //Adding Items to the Frame
        frame.add(usernameLabel);
        frame.add(usernameTextField);
        frame.add(passwordLabel);
        frame.add(passwordTextField);
        frame.add(loginButton);
        frame.add(logoutButton);
        //End of Login Components

        //upload Components
        uploadButton = new JButton("Upload");
        uploadButton.addActionListener(this);
        frame.add(uploadButton);
        //End of Upload Components

        //Download Components
        JLabel downloadLabel = new JLabel("Download");
        downloadTextField = new JTextField(12);
        downloadButton = new JButton("Download");
        downloadButton.addActionListener(this);
        frame.add(downloadLabel);
        frame.add(downloadTextField);
        frame.add(downloadButton);
        //End of DownLoadComponents

        //JFrame Settings
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            login = new ClientLogin(usernameTextField.getText(), passwordTextField.getText());
            String message = login.loginUser();
            information.setText(message);
            System.out.println("You Have Logged In Congrats!!!!!!");
            logoutButton.addActionListener(this);
            loginButton.removeActionListener(this);
        }
        if(e.getSource() == logoutButton){
            String message = login.logoutUser();
            String[] messages = message.split(",");
            information.setText(messages[1]);
            if(messages[0].equals("301")) {
                System.out.println("You Have Logged out Congrats!!!!!!");
                loginButton.addActionListener(this);
                logoutButton.removeActionListener(this);
            }
        }
        if(e.getSource() == uploadButton){
            JFileChooser uploadFile = new JFileChooser();
            uploadFile.showOpenDialog(this.frame);
            String path = uploadFile.getSelectedFile().getAbsolutePath();
            File file = ClientFile.getFile(path);
            String message = file.upload();
            information.setText(message);
            System.out.println("Upload File");
        }
    }
}
