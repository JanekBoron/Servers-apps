package NetworkClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkClient {


    public static void main(String[] args) {
        System.out.println("Welcome to resource reservation system please turn on nodeNetwork:");
        try {
            int port = Integer.parseInt(args[3]);
            String host = args[5];
            Socket clientSocket = new Socket(host, port);
            PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            if (args[0] .equals("Terminate")) {
                String terminateRequest = args[0];
                outToServer.println(terminateRequest);
            } else {
                String request = String.format("%s %s ", args[1], args[6]);
                for (int i = 7; i < args.length; i++) {

                    request += String.format("%s ", args[i]);
                }
                outToServer.println(request);
            }

            while (true) {
                final String line = inFromServer.readLine();
                if (line.equals("ENDMSG")) break;

                System.out.println(line);
            }


        } catch (
                java.io.IOException IOException) {
            IOException.printStackTrace();
        }

    }


}
