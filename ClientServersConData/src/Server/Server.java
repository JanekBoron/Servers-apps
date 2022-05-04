package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) throws Exception {


        ServerSocket serverSocket = new ServerSocket(9000);
        List<Node> nodes = new ArrayList<Node>();


        while (true) {
            Socket clientSocket = serverSocket.accept();

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String data = in.readLine();
            if (data.equals("Terminate")) {
                serverSocket.close();
            } else {

                String[] dataPartsWID = data.split(" ");

                String[] dataParts = new String[dataPartsWID.length - 1];
                System.arraycopy(dataPartsWID, 1, dataParts, 0, dataPartsWID.length - 1);

                Node node = nodes.stream().filter(n -> Integer.parseInt(dataPartsWID[0]) == (n.id)).findFirst().orElse(null);

                if ((node == null)) {
                    node = new Node(Integer.parseInt(dataPartsWID[0]));
                    node.addResources(dataParts);
                    nodes.add(node);
                } else {
                    node.addResources(dataParts);

                }

                out.println("Allocated");
                for (int i = 0; i < nodes.size(); i++) {

                    out.println(nodes.get(i) + ": " + node.id + " " + 9000);
                }
                out.println("ENDMSG");

            }
        }
    }


}

