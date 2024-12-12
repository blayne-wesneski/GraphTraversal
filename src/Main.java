import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        
        try {
            File file = new File("./lib/map.dat");
            Scanner scanner = new Scanner(file);

            int nodes = Integer.parseInt(scanner.nextLine());

            System.out.println(nodes);

            for (int i = 0; i < nodes; i++) {
                String line = scanner.nextLine();

                String[] tokens = line.split(",");

                int id = Integer.parseInt(tokens[0]);
                double lat = Double.parseDouble(tokens[1]);
                double lon = Double.parseDouble(tokens[2]);

                // ID, Name, Lat, Lon
                Node node = new Node(id,tokens[4],lat,lon);

                graph.addNode(node);
               // System.out.println("Added node: " + node.id);
            }

            int edges = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < edges; i++) {
                String line = scanner.nextLine();

                String[] tokens = line.split(" ");

                int node1Index = Integer.parseInt(tokens[0]);
                int node2Index = Integer.parseInt(tokens[1]);
                int direction = Integer.parseInt(tokens[2]);

                Node node1 = graph.nodes.get(node1Index);
                Node node2 = graph.nodes.get(node2Index);

                if(direction == 1){
                    graph.addEdge(node1, node2, Haversine.haversine(node1.lat, node1.lon, node2.lat, node2.lon));
                }else if(direction == 2){
                    graph.addEdge(node1, node2, Haversine.haversine(node1.lat, node1.lon, node2.lat, node2.lon));
                    graph.addEdge(node2, node1, Haversine.haversine(node2.lat, node2.lon, node1.lat, node1.lon));
                }

                //System.out.println("Added edge: " + (i + 1));
            }

            int cases = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < cases; i++) {
                String line = scanner.nextLine();

                String[] tokens = line.split(" ");

                int node1Index = Integer.parseInt(tokens[0]);
                int node2Index = Integer.parseInt(tokens[1]);

                Node node1 = graph.nodes.get(node1Index);
                Node node2 = graph.nodes.get(node2Index);

                System.out.println("Beginning search for case: " + (i+1));
                graph.dijkstra(node1, node2);
                System.out.println("Shortest Path Found");
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

    }
}
