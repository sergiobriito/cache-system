package client;

import cache.CacheService;

public class ClientRequest {
    private String method;
    private String key;
    private String value;
    private CacheService cacheService;

    public ClientRequest(String request, CacheService cacheService){
        this.cacheService = cacheService;
        parse(request);
    };

    public void parse(String request){
        String[] arr = request.split(" ");
        method = arr[0];
        String[] data = arr[1].split(":");
        key = data[0];
        if (data.length > 1){
            value = data[1];
        };
    };

    public String handleRequest(){
        return switch (method) {
            case "get" -> handleGet();
            case "put" -> handlePut();
            default -> "method not allowed";
        };
    };

    public String handleGet(){
        return cacheService.get(key);
    };

    public String handlePut(){
        cacheService.put(key,value);
        return "Saved in cache: " + key + "->" + value;
    };


}
