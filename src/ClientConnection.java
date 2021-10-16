import java.io.*;
import java.net.*;

public class ClientConnection extends Thread {
    private Socket clientSocket;
    private PrintWriter toClient;
    private BufferedReader fromClient;

    public ClientConnection(Socket socket){
        this.clientSocket = socket;
    }

    public void start(int port) throws IOException {
        toClient = new PrintWriter(clientSocket.getOutputStream(), true);
        fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void run() {
        System.out.println("New client created...waiting for packets");
        //boolean listening = true;
        try {
            toClient = new PrintWriter(clientSocket.getOutputStream(), true);
            fromClient = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            String inputLine;
            while ((inputLine = fromClient.readLine()) != null) {
                System.out.println("Server received the line: " + inputLine);
                sendMessage(inputLine);
                //Analyze input packets here
            }
            fromClient.close();
            toClient.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        toClient.println(message);
    }
}
