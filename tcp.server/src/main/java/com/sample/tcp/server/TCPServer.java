package com.sample.tcp.server;

/**
 *Simple TCP server implementation.
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.io.IOException;


public class TCPServer {

    public static final int MAX_REQUEST = 100;
    public static final int SERVICE_PORT = 50001;
    static int defaultPoolSize = 5;
    static int maxPoolSize;
    static long threadAliveTime = 1000;

    public static void main(String[] args) throws IOException {

        int maxCon = Integer.parseInt(args[0]);
        maxPoolSize = maxCon;

        BlockingQueue<Runnable> queue = (BlockingQueue<Runnable>)new ArrayBlockingQueue(10);

        ExecutorService executorService = new ThreadPoolExecutor(defaultPoolSize, maxPoolSize, threadAliveTime, TimeUnit.MILLISECONDS, queue);
        ServerSocket serverSocket = null;
        Socket socket = null;

        serverSocket = new ServerSocket(SERVICE_PORT);

        try {
            for(;;){

                socket = serverSocket.accept();

                RequestProcessor reqProcessor = new RequestProcessor(socket);

                System.out.println("Request Processor created and handed over to the connection " + "Thr["+reqProcessor.toString()+"] Soc ["+socket.toString()+"]");

                executorService.execute(reqProcessor);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {

            executorService.shutdown();

            while(executorService.isTerminated()){}

            System.out.println("Finished all threads");

            serverSocket.close();
        }
    }
}
