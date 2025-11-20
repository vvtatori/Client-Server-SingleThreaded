/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcpechoclient;
import java.io.*;
import java.net.*;

/**
 *
 * @author vvtat
 */
public class TCPEchoClient {
    private static InetAddress host;
        private static final int PORT = 1234; //defining the port number to be used for the client-server communication

        public static void main(String[] args) {
           try 
           {
              host = InetAddress.getLocalHost(); //let the server be in the machine the client is running on
           } 
           catch(UnknownHostException e) 
           {
              System.out.println("Host ID not found!");
              System.exit(1);
           }
           run();
        }

        private static void run() {
           Socket link = null;	//creating the client socket			//Step 1.
           try 
           {
               link = new Socket(host,PORT);		//Step 1. initializing the ip address and the port number
               //link = new Socket( "192.168.0.59", PORT);
               BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));//Step 2. creating the communication channels (BufferedReader: for reading texts)
               PrintWriter out = new PrintWriter(link.getOutputStream(),true);	 //Step 2. PrintWriter: for sending texts

               //Set up stream for keyboard entry...
               BufferedReader userEntry =new BufferedReader(new InputStreamReader(System.in));
               String message = null;
               String response= null;

               System.out.println("Enter message to be sent to server: ");
               message =  userEntry.readLine();  //User writes the message on the client side
               out.println(message); 		//Step 3. message is sent to the server
               response = in.readLine();		//Step 3. client reads the incoming line/response
               System.out.println("\nSERVER RESPONSE> " + response); //response sent back to the client
           } 
           catch(IOException e)
           {
               e.printStackTrace();
           } 
           finally 
           {
               try 
               {
                   System.out.println("\n* Closing connection... *");
                   link.close();				//Step 4. closing the link connection
               }catch(IOException e)
               {
                   System.out.println("Unable to disconnect/close!");
                   System.exit(1);
               }
        }
    } // finish run method
}  //finish the class
