package nuttyCRUD1;
import java.io.*;
import java.lang.NumberFormatException;


class Read extends Thread{
	@Override
	
	 public void run()
	    {
		 String nameNumberString;
         String name;
         String number;
         String email=" ";
         String credit;
         String status;
         String paybal;
         BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
         boolean found=false,found1=false;
		 do {
	        try {
	        	System.out.println("Enter ID");
	            String newID = reader.readLine();
	           
	            // Using file pointer creating the file.
	            File file = new File("Customer.csv");
	 
	            if (!file.exists()) {
	 
	                // Create a new file if not exists.
	                file.createNewFile();
	            }
	 
	            // Opening file in reading and write mode.
	 
	            RandomAccessFile ptr
	                = new RandomAccessFile(file, "rw");
	            // Traversing the file
	            // getFilePointer() give the current offset
	            // value from start of the file.
	            while (ptr.getFilePointer() < ptr.length())
	            {
	 
	                // reading line from the file.
	                nameNumberString = ptr.readLine();
	 
	                // splitting the string to get name and
	                // number
	                String[] lineSplit
	                    = nameNumberString.split(",");
	 
	                // separating name and number12
	                number = lineSplit[0];
	                name = lineSplit[1];
	                email = lineSplit[2];
	                credit = lineSplit[3];
	                status = lineSplit[4];
	                paybal = lineSplit[5];
	 if(newID.equals(number))
	 {
	                // Print the contact data
	                System.out.println(
	                    "Customer Id: " + number + "\n"
	                    + "Customer Name: " + name+ "\n"
	                    + "Email Id: " + email+ "\n"
	                    +"Credit period: "+ credit+ "\n"
	                    +"status: "+ status+ "\n"
	                    +"paybal:"+ paybal+ "\n");
	                found1=false; 
	                break;
	                
	 }
	 else
	 {
		 //System.out.println("Not found");
		 found1=true;
	 }
	            }
	            if(found1==true)
	            {
	            	System.out.println("Not found");
	            }
	            }
	        
	 
	            catch (IOException ioe)
	            {
	 
	                System.out.println(ioe);
	            }
	            catch (NumberFormatException nef)
	            {
	 
	                System.out.println(nef);
	            }
	        
	    }while(found==true);
}
}