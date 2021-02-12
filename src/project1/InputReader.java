package project1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class InputReader {
    public static Input read(String filePath){
        HashMap<Integer, ArrayList<Node>> adjacencyList = new HashMap<>();
        HashMap<Integer, Integer> vertexSquare = new HashMap<>();
        int startVertex = 0, destinationVertex = 99;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {

                if(line.isEmpty() || line.charAt(0) == '#') continue;

                String[] args = line.split(",");

                if(args[0].equals("S")){
                    // get source vertex
                    startVertex = Integer.parseInt(args[1]);
                }else if(args[0].equals("D")){
                    // get destination vertex
                    destinationVertex = Integer.parseInt(args[1]);
                }else if(args.length == 2){
                    // read vertices
                    // Vertex ID, Square ID
                    vertexSquare.put(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                }else if(args.length == 3){
                    // read edges
                    // From, To, Distance
                    int from = Integer.parseInt(args[0]);
                    int to = Integer.parseInt(args[1]);
                    double distance = Double.parseDouble(args[2])/100; // divide by 100 because input is given for 1000x1000 board

                    // initialize lists if absent
                    adjacencyList.computeIfAbsent(from, ArrayList::new);
                    adjacencyList.computeIfAbsent(to, ArrayList::new);

                    // It is undirected graph so add it to both of them
                    adjacencyList.get(from).add(new Node(to, vertexSquare.get(to), distance));
                    adjacencyList.get(to).add(new Node(from, vertexSquare.get(from), distance));
                }
            }
        } catch (Exception e) {
            System.out.println("Your input file format is not correct!");
            e.printStackTrace();
            System.exit(1);
        }

        return new Input(adjacencyList, vertexSquare, startVertex, destinationVertex);
    }
}
