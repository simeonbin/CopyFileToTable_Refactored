/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CopyFileToTable_Refactored;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Simeon
 */
public class CopyFile {

    void copyFile() throws Exception {
        CopyFileToTable_Refactored CF = new CopyFileToTable_Refactored();
        InsertRows IR = new InsertRows();
        // Load the JDBC driver
        Class.forName(((String) CF.getJcboDriver().getSelectedItem()).trim());
        System.out.println("Driver loaded");

        // Establish a connection
        Connection conn = DriverManager.getConnection(((String) CF.getJcboDriver().getSelectedItem()).trim(),
                CF.getJtfUsername().getText().trim(),
                String.valueOf(CF.getJtfPassword().getPassword()).trim());
        System.out.println("Database connected");

        // Read each line from the text file and insert it to the table
        IR.insertRows(conn);
    }

}
