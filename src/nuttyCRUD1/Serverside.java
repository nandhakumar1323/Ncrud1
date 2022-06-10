package nuttyCRUD1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Serverside {
	public static void main(String args[]) {
		ServerSocket ss;
		Create obj1 = new Create();
		Read obj2 = new Read();
		Update obj3 = new Update();
		Delete obj4 = new Delete();
		try {
			ss = new ServerSocket(6086);
			System.out.println("Server Started");
			System.out.println("waiting for client");
			Socket s = ss.accept();
			
			System.out.println("Client accepted");
			DataInputStream din = new DataInputStream(s.getInputStream());
			BufferedReader br=new BufferedReader (new InputStreamReader(System.in));
			
			String str="";
			while(!str.equals("stop"))
				
			{
				str=din.readUTF();
				switch (str) 
				{

			     // Case
			     case "1":
			         obj1.start();
			         break;

			     // Case
			     case "2":
			    	 obj2.start();
			         break;

			         // Case
			     case "3":
			    	 obj3.start();
			         break;

			         // Case
			     case "4":
			    	 obj4.start();
			         break;

			     // Default case
			     default:
			         System.out.println(" Enter a valid option");
			         break;
			     }

			}
			din.close();
			s.close();
			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
