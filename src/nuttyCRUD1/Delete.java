package nuttyCRUD1;
import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

class Delete extends Thread{
	@Override
	public void run()
    {
		
		String nameNumberString;
        String name="";
        String number="";
        String email=" ";
        String credit="";
        String status="";
        String paybal="";
        String numb="";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
 
        try {
 
        	
            // Get the name of the contact to be updated
            // from the Command line argument
        	System.out.println("Enter ID");
            String newID = reader.readLine();
 
           
            int index;
 
            // Using file pointer creating the file.
            File file = new File("Customer.csv");
 
            if (!file.exists()) {
 
                // Create a new file if not exists.
                file.createNewFile();
            }
 
            // Opening file in reading and write mode.
            RandomAccessFile ptr
                = new RandomAccessFile(file, "rw");
            boolean found = false;
 
            // Checking whether the name of contact exists.
            // getFilePointer() give the current offset
            // value from start of the file.
            while (ptr.getFilePointer() < ptr.length()) {
 
                // reading line from the file.
                nameNumberString = ptr.readLine();
 
                // splitting the string to get name and
                // number
                String[] lineSplit
                    = nameNumberString.split(",");
 
                // separating name and number.
                number = lineSplit[0];
                name = lineSplit[1];
                email = lineSplit[2];
                credit = lineSplit[3];
                status = lineSplit[4];
                paybal = lineSplit[5];
 
                // if condition to find existence of record.
                if (newID == number) {
                    found = true;
                    break;
                }
            }
 
            // Delete the contact if record exists.
            if (found == false) {
 
                // Creating a temporary file
                // with file pointer as tmpFile.
                File tmpFile = new File("temp.csv");
 
                // Opening this temporary file
                // in ReadWrite Mode
                RandomAccessFile tmpptr
                    = new RandomAccessFile(tmpFile, "rw");
 
                // Set file pointer to start
                ptr.seek(0);
 
                // Traversing the friendsContact.txt file
                while (ptr.getFilePointer()
                       < ptr.length()) {
 
                    // Reading the contact from the file
                    nameNumberString = ptr.readLine();
 
                    index = nameNumberString.indexOf(',');
                    numb = nameNumberString.substring(
                        0, index);
 
                    // Check if the fetched contact
                    // is the one to be deleted
                    if (numb.equals(newID)) {
 
                        // Skip inserting this contact
                        // into the temporary file
                        continue;
                    }
 
                    // Add this contact in the temporary
                    // file
                    tmpptr.writeBytes(nameNumberString);
 
                    // Add the line separator in the
                    // temporary file
                    tmpptr.writeBytes(
                        System.lineSeparator());
                }
 
                // The contact has been deleted now
                // So copy the updated content from
                // the temporary file to original file.
 
                // Set both files pointers to start
                ptr.seek(0);
                tmpptr.seek(0);
 
                // Copy the contents from
                // the temporary file to original file.
                while (tmpptr.getFilePointer()
                       < tmpptr.length()) {
                    ptr.writeBytes(tmpptr.readLine());
                    ptr.writeBytes(System.lineSeparator());
                }
 
                // Set the length of the original file
                // to that of temporary.
                ptr.setLength(tmpptr.length());
 
                // Closing the resources.
                tmpptr.close();
                ptr.close();
 
                // Deleting the temporary file
                tmpFile.delete();
 
                System.out.println(" Customer data deleted. ");
            }
 
            // The contact to be deleted
            // could not be found
            else {
 
                // Closing the resources.
                ptr.close();
 
                // Print the message
                System.out.println(" ID does not exists. ");
            }
        }
 
        catch (IOException ioe) {
            System.out.println(ioe);
        }
    }
}
