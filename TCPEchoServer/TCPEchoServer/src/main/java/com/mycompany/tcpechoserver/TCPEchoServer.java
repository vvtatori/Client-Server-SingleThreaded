/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tcpechoserver;
import java.io.*;
import java.net.*;

/**
 *
 * @author vvtat
 */

public class TCPEchoServer { 
    private static ServerSocket servSock; //defining the server socket
    private static final int PORT = 1234;  //intializing the same port number as the client
    private static int clientConnections = 0;

    public static void main(String[] args) {
        System.out.println("Opening port...\n");
        try 
        {
            servSock = new ServerSocket(PORT);      //Step 1. start the server connection
        }
        catch(IOException e) 
        {
             System.out.println("Unable to attach to port!");
             System.exit(1);
        }

        do 
        {
            run();
        }while (true);

    }
  
    private static void run(){
        Socket link = null;      //the client socket                  //Step 2.
        try 
        {
          link = servSock.accept();               //Step 2.  //server waits here until a client communicated with it
          clientConnections++;

          BufferedReader in = new BufferedReader( new InputStreamReader(link.getInputStream())); //Step 3.  //reads input from the socket(link)
          PrintWriter out = new PrintWriter(link.getOutputStream(),true); //Step 3.

          String message = in.readLine();         //Step 4. server reads the incoming message
          System.out.println("Message received from client: " + clientConnections + "  "+ message);
          out.println("Echo Message: " + message);     //Step 4.  //writing a message to the client, on the socket
         }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally 
        {
           try {
                System.out.println("\n* Closing connection... *");
                link.close();				    //Step 5. closing the link connection.
            }
           catch(IOException e)
           {
                System.out.println("Unable to disconnect!");
                System.exit(1);
           }
        }
    } // finish run method 
} // finish the class
