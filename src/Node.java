import java.util.LinkedList;

public class Node {
    
    public int id;
    public String name;
    public LinkedList<Edge> edges;// Adjancency List

    public boolean visited;

    //Dijkstra's
    public double distance;
    public Node prev;

    // lat and lon values for haversine
    public double lat;
    public double lon;

    public Node(int id, String name, double lat, double lon){
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        edges = new LinkedList<>();
        this.visited = false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
