public class Edge {
    public double weight;
    public Node start;
    public Node end;

    public Edge(Node start, Node end, double weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString(){
        return this.start.name + " -" + this.weight + " -> " + this.end.name;
    }
}
