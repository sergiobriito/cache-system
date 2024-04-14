package server;

import cache.CacheService;
import client.ClientConn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private Integer port = 8080;
    private ServerSocket serverSocket;
    private CacheService cacheService;

    public TCPServer(){start();};
    public TCPServer(Integer port){this.port = port;start();};

    public void start(){
        try {
            serverSocket = new ServerSocket(port);
            cacheService = new CacheService(10);
            System.out.println("Server started at port: " + port);
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from: " + serverSocket.getLocalSocketAddress());
                new Thread(new ClientConn(clientSocket,cacheService)).start();
            }
        } catch (IOException e) {
            System.out.println("Failed to start the server");
        }
        close();
    };

    public void close(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Failed to close the server");
        }
    };

}
