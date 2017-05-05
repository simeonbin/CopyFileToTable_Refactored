/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CopyFileToTable_Refactored;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Simeon
 */
public class InsertRows {

    private void BatchUpdates(Scanner input, Statement statement, String sqlInsert, CopyFileToTable_Refactored CF) throws SQLException {
        // Read a line and add the insert table command to the batch
        while (input.hasNext()) {
            statement.addBatch(sqlInsert + input.nextLine() + ")");
        }
        statement.executeBatch();
        CF.getJlblStatus().setText("Batch updates completed");
    }

    private void IndividualUpdates(Scanner input, Statement statement, String sqlInsert, CopyFileToTable_Refactored CF) throws SQLException {
        // Read a line and execute insert table command
        while (input.hasNext()) {
            statement.executeUpdate(sqlInsert + input.nextLine() + ")");
        }
        CF.getJlblStatus().setText("Single row update completed");
    }

    void insertRows(Connection connection) {

        CopyFileToTable_Refactored CF = new CopyFileToTable_Refactored();

        // Build the SQL INSERT statement
        String sqlInsert = "insert into " + CF.getJtfTableName().getText()
                + " values (";

        // Use a Scanner to read text from the file
        Scanner input = null;
        // Get file name from the text field
        String filename = CF.getJtfFilename().getText().trim();

        try {
            // Create a scanner
            input = new Scanner(new File(filename));
            // Create a statement
            Statement statement = connection.createStatement();
            System.out.println("Driver major version? "
                    + connection.getMetaData().getDriverMajorVersion());

            // Determine if batchUpdatesSupported is supported
            boolean batchUpdatesSupported = false;

            try {
                if (connection.getMetaData().supportsBatchUpdates()) {
                    batchUpdatesSupported = true;
                    System.out.println("batch updates supported");
                } else {
                    System.out.println("The driver is of JDBC 2 type, but "
                            + "does not support batch updates");
                }
            } catch (UnsupportedOperationException ex) {
                System.out.println("The driver does not support JDBC 2");
            }

            // Determine if the driver is capable of batch updates
            if (batchUpdatesSupported) {
                BatchUpdates(input, statement, sqlInsert, CF);
            } else {
                IndividualUpdates(input, statement, sqlInsert, CF);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + filename);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }

    }

}
