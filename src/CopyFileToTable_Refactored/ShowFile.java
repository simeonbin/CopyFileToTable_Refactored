/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CopyFileToTable_Refactored;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Simeon
 */
public class ShowFile {
    
    
     /** Display the file in the text area */
 void showFile() {
    Scanner input = null;    
     CopyFileToTable_Refactored CF = new CopyFileToTable_Refactored();
     
    try {
     // Use a Scanner to read text from the file
      input = new Scanner(new File(CF.getJtfFilename().getText().trim() ) );

      // Read a line and append the line to the text area
      while (input.hasNext())
        CF.getJtaFile().append(input.nextLine() + '\n');
    }
    catch (FileNotFoundException ex) {
      System.out.println("File not found: " + CF.getJtfFilename().getText() );
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
    finally {
      if (input != null) input.close();
    }
  }
    
}
