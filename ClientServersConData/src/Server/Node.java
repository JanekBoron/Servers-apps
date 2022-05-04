package Server;

import java.util.HashMap;

public class Node {
    int id;
    HashMap<Character, Integer>resources = new HashMap<>();


    Node(int id) {
        this.id = id;

    }


    public void addResources(String[] dataParts) {

        String[] tmp;
        for (int i = 0; i < dataParts.length; i++) {

            tmp = dataParts[i].split(":");

            char c = tmp[0].charAt(0);
            int d = Integer.parseInt(tmp[1]);

            if (resources.containsKey(c))
            {
                int value=resources.get(c);
                value+=d;
                resources.put(c,value);


            }else{
                resources.put(c, d);
            }

        }



    }
    public String toString() {
        return"" +resources;
    }


}
