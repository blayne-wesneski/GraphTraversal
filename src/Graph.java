import java.util.LinkedList;
import java.util.Stack;

public class Graph {
    private LinkedList<Node> nodes;

    public Graph() {
        nodes = new LinkedList<>();
    }

    // Add node to the graph
    public boolean addNode(Node v) {
        return nodes.add(v);
    }

    public boolean addEdge(Node from, Node to, int weight) {
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
        curr.distance = 0;

        while (curr != null) {
            System.out.println("Current node is " + curr);

            if (curr == end) {
                break;
            }

            // process all the edges
            for (int i = 0; i < curr.edges.size(); i++) {
                Edge edge = curr.edges.get(i);
                System.out.println("\tEdge " + (i + 1) + " " + edge + " visited? " + edge.end.visited);

                if (edge.end.visited) {
                    continue;
                }

                int newDistance = curr.distance + edge.weight;
                System.out.println("\t\t New distance: " + newDistance);

                System.out.println(
                        "\t\t New Distance < Edge Distance: " + newDistance + " < " + edge.end.distance + " ?");
                if (newDistance < edge.end.distance) {
                    System.out.println("\t\t Smaller distance found. Updating.");
                    edge.end.distance = newDistance;
                    edge.end.prev = curr;
                }
            } // done proc edges

            curr.visited = true;

            curr = getSmallestDistanceVisitedNode();
            System.out.println("curr is now " + curr);

            Stack<Node> path = new Stack<>();
            curr = end;
            while (curr != null) {
                path.push(curr);
                curr = curr.prev;
            }
            while (!path.isEmpty()) {
                Node n = path.pop();
                System.out.println(n + " (" + n.distance + ")");
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
        int min = Integer.MAX_VALUE;
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
