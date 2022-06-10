package nuttyCRUD1;
import java.io.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

class Create extends Thread{
    @Override
	public void run()
    {

      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
 
            // Get the name of the contact to be updated
            // from the Command line argument
        	 System.out.println("Enter ID");
             String newNumber = reader.readLine();
             
            System.out.println("Enter Name");
            String newName = reader.readLine();
            
            System.out.println("Enter Email");
            String newEmail = reader.readLine();
            
            System.out.println("Enter Credit Period ");
            String newCrprd = reader.readLine();
            
            System.out.println("Enter Customer status");
            String newStatus = reader.readLine();
            
            System.out.println("Enter Payment Balance");
            String newPaybal = reader.readLine();
 
            // Get the number to be updated
            // from the Command line argument
           
 
            String nameNumberString;
            String name;
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
 
            // Checking whether the name
            // of contact already exists.
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
                name = lineSplit[0];
//                number = Long.parseLong(lineSplit[1]);
 
                // if condition to find existence of record.
                if (name == newName ) {
                    found = true;
                    break;
                }
            }
 
            if (found == false) {
 
                // Enter the if block when a record
                // is not already present in the file.
                nameNumberString
                    = String.valueOf(newNumber)+","+ newName +","+ newEmail +","+ newCrprd +","+ newStatus +","+ newPaybal;
 
                // writeBytes function to write a string
                // as a sequence of bytes.
                ptr.writeBytes(nameNumberString);
 
                // To insert the next record in new line.
                ptr.writeBytes(System.lineSeparator());
 
                // Print the message
                System.out.println(" Customer Created ");
 
                // Closing the resources.
                ptr.close();
            }
            // The contact to be updated
            // could not be found
            else {
 
                // Closing the resources.
                ptr.close();
 
                // Print the message
                System.out.println("Customer Id already exists");
            }
        }
 
        catch (IOException ioe) {
 
            System.out.println(ioe);
        }
        catch (NumberFormatException nef) {
 
            System.out.println(nef);
        }
    }
}
