package nuttyCRUD1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Clientside {
	public static void main(String args[]){
		Socket s;
	
		try {
			s = new Socket("localhost",6086); 
			
			System.out.println("Connected");
			
			DataOutputStream dout=new DataOutputStream(s.getOutputStream());
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			String str="";
			String cont="a";
			
			while(!str.equals("stop"))
			{
			 do {
				System.out.println("1.Create records");
			    System.out.println("2.Read records");
			    System.out.println("3.Update records");
			    System.out.println("4.Delete records");
			 
			    System.out.println("\n Enter the choice");
				str=br.readLine();
				dout.writeUTF(str);
				
				System.out.println("***Choose if you wish to continue***");
			 }while(cont.equals("a"));
			}
			dout.close();
			s.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
