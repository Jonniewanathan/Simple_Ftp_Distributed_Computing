package org.jonniewanathan.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {
    final private int width = 700;
    final private int height = 500;

    private JTextField usernameTextField;
    private JTextField passwordTextField;
    private JTextField downloadTextField;
    private JButton loginButton;
    private JButton logoutButton;
    private JButton uploadButton;
    private JButton downloadButton;
    private JLabel information;
    private JFrame frame;
    private ClientLogin login;

    public static void main(String[] args) {
        new GUI();
    }

    public GUI(){
        frame = new JFrame("File Transfer Client");
        Dimension d = new Dimension(width, height);
        frame.setSize(d);

        //layoutManager
        LayoutManager flowLayout = new FlowLayout();
        LayoutManager gridLayout = new GridLayout(4, 1);
        frame.setLayout(gridLayout);
        JPanel loginPanel = new JPanel();
        JPanel downloadPanel = new JPanel();
        JPanel uploadPanel = new JPanel();

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
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameTextField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordTextField);
        loginPanel.add(loginButton);
        loginPanel.add(logoutButton);
        loginPanel.setLayout(flowLayout);
        frame.add(loginPanel);
        //End of Login Components

        //upload Components
        uploadButton = new JButton("Upload");
        uploadButton.addActionListener(this);
        uploadPanel.add(uploadButton);
        uploadPanel.setLayout(flowLayout);
        frame.add(uploadPanel);
        //End of Upload Components

        //Download Components
        JLabel downloadLabel = new JLabel("Download");
        downloadTextField = new JTextField(12);
        downloadButton = new JButton("Download");
        downloadButton.addActionListener(this);

        downloadPanel.add(downloadLabel);
        downloadPanel.add(downloadTextField);
        downloadPanel.add(downloadButton);
        frame.add(downloadPanel);
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
            String[] messages = message.split(",");
            information.setText(messages[1]);
            if(messages[0].equals("201")) {
                System.out.println("You Have Logged In Congrats!!!!!!");
                logoutButton.addActionListener(this);
                loginButton.removeActionListener(this);
            }
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
            try{
                JFileChooser uploadFile = new JFileChooser();
                int option = uploadFile.showOpenDialog(this.frame);
                if( option == JFileChooser.APPROVE_OPTION) {
                    String path = uploadFile.getSelectedFile().getAbsolutePath();
                    File file = ClientFile.getFile(path);
                    String message = file.upload();
                    information.setText(message);
                    System.out.println("Upload File");
                }
                else {
                    System.out.println("No file Chosen");
                }
            }catch(NullPointerException exception){
                System.out.println("No File Selected");
            }

        }
        if(e.getSource() == downloadButton){
            String fileName = downloadTextField.getText();
            JFileChooser downloadFile = new JFileChooser();
            downloadFile.setSelectedFile(new java.io.File(fileName));
            int option = downloadFile.showSaveDialog(this.frame);
            if( option == JFileChooser.APPROVE_OPTION){
                String path = downloadFile.getCurrentDirectory().getAbsolutePath() + "\\" + fileName;
                System.out.println(path);
                byte[] bytes = new  byte[1]; // dummy data
                File file = new File(fileName,bytes, bytes.length);
                String message = file.download(path);
                information.setText(message);
                System.out.println("Download File");
            }
            else {
                System.out.println("No file chosen");
            }

        }
    }
}
