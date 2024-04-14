package client;

import cache.CacheService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientConn implements Runnable {
    private Socket clientSocket;
    private CacheService cacheService;

    public ClientConn(Socket clientSocket, CacheService cacheService){
        this.clientSocket = clientSocket;
        this.cacheService = cacheService;
    };

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ClientRequest clientRequest = new ClientRequest(bufferedReader.readLine(),cacheService);
            String response = clientRequest.handleRequest();
            clientSocket.getOutputStream().write(response.getBytes());
            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Failed to read the input stream");
        }
    }
}
