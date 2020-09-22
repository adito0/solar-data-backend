package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GetPVData {
    private String SERVER_IP = null;
    private int SERVER_PORT = -1;

    public GetPVData(String ip, int port) {
        this.SERVER_IP = ip;
        this.SERVER_PORT = port;
    }

    public String run() throws IOException {
        Socket socket = new Socket(SERVER_IP, SERVER_PORT);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        String command = "GSPV ";
        out.println(command);
        String serverResponse = input.readLine();
        System.out.println("Server says: " + serverResponse);
        socket.close();
        return serverResponse;
    }
}
