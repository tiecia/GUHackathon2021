import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * The connection from the client to the server.
 * How the client communicates with the server.
 */
public class ServerConnection extends Thread {
    private Socket clientSocket;
    private PrintWriter toServer;
    private BufferedReader fromServer;
    private Game game;

    public ServerConnection() {

    }

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        toServer = new PrintWriter(clientSocket.getOutputStream(), true);
        fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.start();
    }

    public void setPlayerName(String name){
        sendMessage(name);
    }

    public void run(){
        try{
            String inputLine;
            while((inputLine = fromServer.readLine()) != null){
                //Handle packets from server here.
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public String sendMessage(String message){
        toServer.println(message);
        String resp = null;
        try {
            resp = fromServer.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }


    public void stopConnection() throws IOException {
        fromServer.close();
        toServer.close();
        clientSocket.close();
    }

    public void makeBet(int bet) {
        sendMessage("bet " + bet);
    }

    public void fold() {
        sendMessage("fold");
    }

}
