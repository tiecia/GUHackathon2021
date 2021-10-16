import java.io.*;
import java.net.*;

/**
 * The connection from the client to the server.
 * How the client communicates with the server.
 */
public class ServerConnection extends Thread {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String message){
        out.println(message);
        String resp = null;
        try {
            resp = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }


    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }


}
