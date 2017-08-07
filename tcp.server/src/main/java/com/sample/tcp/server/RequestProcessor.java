package com.sample.tcp.server;

/**
 *Client request process.
 */

import java.net.Socket;
import java.io.*;

import java.util.Date;


public class RequestProcessor implements Runnable {

    private Socket clientServerSocket;

    public RequestProcessor(Socket soc) {
        this.clientServerSocket = soc;
    }

    @Override
    public void run() {

        System.out.println(this.toString() + " : Thread started. Processing client" + clientServerSocket);

        try {

            InputStream is = clientServerSocket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ReceivedData = br.readLine();

            System.out.println("Message received from client is "+ReceivedData);

            //Multiplying the number by 2 and forming the return message
            String returnMessage;
            try
            {
                int numberInIntFormat = Integer.parseInt(ReceivedData);
                int returnValue = numberInIntFormat*2;
                returnMessage = String.valueOf(returnValue) + "\n";
            }
            catch(NumberFormatException e)
            {
                //Input was not a number. Sending proper message back to client.
                returnMessage = "Please send a proper number\n";
            }

            //Sending the response back to the client.
            OutputStream os = clientServerSocket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(returnMessage);


            System.out.println("Number sent to the client is "+returnMessage);
            bw.flush();


            clientServerSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(this.toString() + " : Thread exiting");
    }

}
