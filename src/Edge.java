public class Edge {
    public int weight;
    public Node start;
    public Node end;

    public Edge(Node start, Node end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
