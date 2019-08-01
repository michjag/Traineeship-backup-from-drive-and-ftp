package praktykibackupv2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import static praktykibackupv2.Window.pathToDirectoryTempBackup;


public class ReadFileFromFtp  
{ 
    public ReadFileFromFtp(){}

    public ReadFileFromFtp(String addressRead, String userRead, String passwordRead, String fileRead) throws Exception 
    {
        Ftp downloadFileFromFtp = null;
        try 
        {
            downloadFileFromFtp = new Ftp(addressRead, userRead, passwordRead);
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(FillInFtp.class.getName()).log(Level.SEVERE, null, ex);
        }

        downloadFileFromFtp.downloadFile(fileRead, (pathToDirectoryTempBackup +"//" + fileRead)); 
        downloadFileFromFtp.disconnect();

    }
    
    public static void reader() throws FileNotFoundException, IOException, Exception
    {
        String pathWithFtpData = "ftp date.txt";
        
        BufferedReader countLine = new BufferedReader(new FileReader(pathWithFtpData));
        int numberOfLines = (int) countLine.lines().count();
      //  System.out.println(numberOfLines);
        
        BufferedReader file = null;
        file = new BufferedReader(new FileReader(pathWithFtpData));
        String readFile = file.readLine();
        
        String [] table = new String[numberOfLines];
        int index = 0;
        
        while(readFile != null)
        {
            table[index] = readFile; 
            readFile = file.readLine();
            index++;
        }
        
        file.close();
        
        String [] tableReturnFromSplit = new String[table.length];
        
        String tempAddress = null;
        String tempUser = null;
        String tempPassword = null;
        String tempFile = null;

            for (int i = 0; i < table.length; i++) 
            {
                    String [] temp = table[i].split(Pattern.quote("="));
                    tableReturnFromSplit[i] = temp[1];
                    
                    if(temp[0].equals("address"))
                        tempAddress = temp[1];
                    
                    if(temp[0].equals("user"))
                        tempUser = temp[1];

                   if(temp[0].equals("password"))
                        tempPassword = temp[1];

                   if(temp[0].equals("file"))
                        tempFile = temp[1];
                    
                    if((i+1)%4==0)
                    {
                        ReadFileFromFtp readFileFromFtp = new ReadFileFromFtp(tempAddress, tempUser, tempPassword, tempFile); 
                    }
            }
         
    }
  
} 


