import java.util.LinkedList;
import java.util.Stack;

public class Graph {
    LinkedList<Node> nodes;

    public Graph() {
        nodes = new LinkedList<>();
    }

    // Add node to the graph
    public boolean addNode(Node v) {
        return nodes.add(v);
    }

    public boolean addEdge(Node from, Node to, double weight) {
        return from.edges.add(new Edge(from, to, weight));
    }


    /**
     * Dijkstra's Algorithm 
     * @param start - Starting Node
     * @param end - Ending Node
     */
    public void dijkstra(Node start, Node end) {

        // init
        unvisitAllNodes();
        resetDistanceData();

        Node curr = start;
        start.distance = 0;

        System.out.println("Routing from " + start + " (" + start.id + ")" + " to " + end + "(" + end.id + ")");
        System.out.println("Start art " + start + " (" + start.id + ")");
        while (curr != null) {
            if (curr == end) {
                break;
            }

            // process all the edges
            for (int i = 0; i < curr.edges.size(); i++) {
                Edge edge = curr.edges.get(i);

                if (edge.end.visited) {
                    continue;
                }

                double newDistance = curr.distance + edge.weight;

                if (newDistance < edge.end.distance) {
                    edge.end.distance = newDistance;
                    edge.end.prev = curr;
                }
            } // done proc edges

            curr.visited = true;

            curr = getSmallestDistanceVisitedNode();

            Stack<Node> path = new Stack<>();
            while (end != null) {
                path.push(end);
                end = end.prev;
            }
            while (!path.isEmpty()) {
                Node n = path.pop();
                System.out.println("Go to " + n + " (" + n.id + ")");
            }
        }
    }

    private void unvisitAllNodes() {
        for (Node n : nodes) {
            n.visited = false;
        }
    }

    private void resetDistanceData() {
        for (Node n : nodes) {
            n.distance = Integer.MAX_VALUE - 1;
        }
    }

    /**
     * Get the node with the smallest distance that has been visited
     * 
     * @return
     */
    private Node getSmallestDistanceVisitedNode() {
        double min = Integer.MAX_VALUE;
        Node candidate = null;

        for (Node n : nodes) {
            // Short-circuit
            if (!n.visited && n.distance < min) {
                min = n.distance;
                candidate = n;
            }
        }

        return candidate;
    }
}
