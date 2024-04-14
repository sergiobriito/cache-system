import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        Socket socket;

        System.out.println("-------PUT-------");

        for (int i=0;i<10;i++){
            try {
                socket = new Socket("127.0.0.1",8080);
                String request = putString("KEY_" + i, "VALUE_" + i);
                socket.getOutputStream().write(request.getBytes());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(bufferedReader.readLine());
            } catch (IOException e) {
                System.out.println("Failed");
            }
        };

        System.out.println("-------GET-------");

        for (int i=0;i<10;i++){
            try {
                socket = new Socket("127.0.0.1",8080);
                String request = getString("KEY_" + i);
                socket.getOutputStream().write(request.getBytes());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println(bufferedReader.readLine());
            } catch (IOException e) {
                System.out.println("Failed");
            }
        };
    }

    public static String getString(String key){
        return "get " + key + "\r\n";
    };

    public static String putString(String key, String value){
        return "put " + key + ":" + value + "\r\n";
    };

}
