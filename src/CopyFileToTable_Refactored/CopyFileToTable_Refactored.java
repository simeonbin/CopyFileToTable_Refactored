/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Simeon
 */
package CopyFileToTable_Refactored;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class CopyFileToTable_Refactored extends JFrame {
    // Text file info

    private JTextField jtfFilename = new JTextField();
    private JTextArea jtaFile = new JTextArea();

    // JDBC and table info
    private JComboBox jcboDriver = new JComboBox(new String[]{
        "com.mysql.jdbc.Driver", "sun.jdbc.odbc.JdbcOdbcDriver",
        "oracle.jdbc.driver.OracleDriver"});

    private JComboBox jcboURL = new JComboBox(new String[]{
        "jdbc:mysql://localhost/test",
        "jdbc:odbc:exampleMDBDataSource",
        "jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl"});

    private JPasswordField jtfPassword = new JPasswordField();
    private JTextField jtfTableName = new JTextField();
    private JTextField jtfUsername = new JTextField();
    private JButton jbtViewFile = new JButton("View File");
    private JButton jbtCopy = new JButton("Copy");
    private JLabel jlblStatus = new JLabel();
    

    public JTextArea getJtaFile() {
        return jtaFile;
    }

    public void setJtaFile(JTextArea jtaFile) {
        this.jtaFile = jtaFile;
    }

    public JTextField getJtfFilename() {
        return jtfFilename;
    }

    public void setJtfFilename(JTextField jtfFilename) {
        this.jtfFilename = jtfFilename;
    }

    public JComboBox getJcboDriver() {
        return jcboDriver;
    }

    public void setJcboDriver(JComboBox jcboDriver) {
        this.jcboDriver = jcboDriver;
    }

    public JTextField getJtfUsername() {
        return jtfUsername;
    }

    public void setJtfUsername(JTextField jtfUsername) {
        this.jtfUsername = jtfUsername;
    }

    public JPasswordField getJtfPassword() {
        return jtfPassword;
    }

    public void setJtfPassword(JPasswordField jtfPassword) {
        this.jtfPassword = jtfPassword;
    }

    public JTextField getJtfTableName() {
        return jtfTableName;
    }

    public void setJtfTableName(JTextField jtfTableName) {
        this.jtfTableName = jtfTableName;
    }

    public JLabel getJlblStatus() {
        return jlblStatus;
    }

    public void setJlblStatus(JLabel jlblStatus) {
        this.jlblStatus = jlblStatus;
    }

    public CopyFileToTable_Refactored() {
        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new BorderLayout());
        jPanel1.add(new JLabel("Filename"), BorderLayout.WEST);
        jPanel1.add(jbtViewFile, BorderLayout.EAST);
        jPanel1.add(jtfFilename, BorderLayout.CENTER);

        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new BorderLayout());
        jPanel2.setBorder(new TitledBorder("Source Text File"));
        jPanel2.add(jPanel1, BorderLayout.NORTH);
        jPanel2.add(new JScrollPane(jtaFile), BorderLayout.CENTER);

        JPanel jPanel3 = new JPanel();
        jPanel3.setLayout(new GridLayout(5, 0));
        jPanel3.add(new JLabel("JDBC Driver"));
        jPanel3.add(new JLabel("Database URL"));
        jPanel3.add(new JLabel("Username"));
        jPanel3.add(new JLabel("Password"));
        jPanel3.add(new JLabel("Table Name"));

        JPanel jPanel4 = new JPanel();
        jPanel4.setLayout(new GridLayout(5, 0));
        jcboDriver.setEditable(true);
        jPanel4.add(jcboDriver);
        jcboURL.setEditable(true);
        jPanel4.add(jcboURL);
        jPanel4.add(jtfUsername);
        jPanel4.add(jtfPassword);
        jPanel4.add(jtfTableName);

        JPanel jPanel5 = new JPanel();
        jPanel5.setLayout(new BorderLayout());
        jPanel5.setBorder(new TitledBorder("Target Database Table"));
        jPanel5.add(jbtCopy, BorderLayout.SOUTH);
        jPanel5.add(jPanel3, BorderLayout.WEST);
        jPanel5.add(jPanel4, BorderLayout.CENTER);

        add(jlblStatus, BorderLayout.SOUTH);
        add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                jPanel2, jPanel5), BorderLayout.CENTER);

        jbtViewFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ShowFile SF = new ShowFile();
                SF.showFile();
            }
        });

        jbtCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    CopyFile CF = new CopyFile();
                    CF.copyFile();
                } catch (Exception ex) {
                    jlblStatus.setText(ex.toString());
                }
            }
        });
    }

    public static void main(String args[]) {
        JFrame frame = new CopyFileToTable_Refactored();
        frame.setTitle("CopyFileToTable");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
