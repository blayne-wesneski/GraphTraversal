import java.util.LinkedList;

public class Node {
    
    public String name;
    public LinkedList<Edge> edges;// Adjancency List

    public boolean visited;

    //Dijkstra's
    public int distance;
    public Node prev;

    // lat and lon values for haversine
    public double lat;
    public double lon;

    public Node(String name){
        this.name = name;
        edges = new LinkedList<>();
        this.visited = false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
