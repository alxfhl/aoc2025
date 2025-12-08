package alxfhl.aoc2025;

import lombok.RequiredArgsConstructor;

import java.util.*;

public class Day08 {

    @RequiredArgsConstructor
    static class Node {
        final long x;
        final long y;
        final long z;
        Graph graph = new Graph(this);

        static Node parse(String s) {
            String[] split = s.trim().split("[^0-9\\-]+");
            if (split.length != 3) {
                throw new IllegalArgumentException("Invalid input for a Node: '" + s + "'");
            }
            return new Node(Long.parseLong(split[0]), Long.parseLong(split[1]), Long.parseLong(split[2]));
        }

        public long distSqr(Node node2) {
            long dx = x - node2.x;
            long dy = y - node2.y;
            long dz = z - node2.z;
            return dx * dx + dy * dy + dz * dz;
        }
    }

    static class Edge {
        final Node node1;
        final Node node2;
        final double distance;

        Edge(Node node1, Node node2) {
            this.node1 = node1;
            this.node2 = node2;
            this.distance = Math.sqrt(node1.distSqr(node2));
        }
    }

    static class Graph {
        final Set<Node> nodes = new HashSet<>();

        Graph(Node... nodes) {
            Collections.addAll(this.nodes, nodes);
        }
    }

    public static long solve(String s, int combine, int multiply) {
        final List<Node> nodes = parseNodes(s);
        final List<Edge> edges = precalculateEdges(nodes);
        for (int i = 0; i < combine; i++) {
            Edge edge = edges.get(i);
            Graph graph1 = edge.node1.graph;
            Graph graph2 = edge.node2.graph;
            if (graph1 != graph2) {
                // merge both graphs
                graph1.nodes.addAll(graph2.nodes);
                for (Node node : graph2.nodes) {
                    node.graph = graph1;
                }
            }
        }

        final Set<Graph> graphs = new TreeSet<>(Comparator.comparingInt(g -> -g.nodes.size()));
        nodes.forEach(n -> graphs.add(n.graph));

        return graphs.stream().limit(multiply).mapToLong(g -> g.nodes.size()).reduce(1, (a, b) -> a * b);
    }

    public static long solve2(String s) {
        final List<Node> nodes = parseNodes(s);
        final List<Edge> edges = precalculateEdges(nodes);
        final List<Graph> graphs = new ArrayList<>();
        nodes.forEach(n -> graphs.add(n.graph));

        for (Edge edge : edges) {
            Graph graph1 = edge.node1.graph;
            Graph graph2 = edge.node2.graph;
            if (graph1 != graph2) {
                // merge both graphs
                graph1.nodes.addAll(graph2.nodes);
                for (Node node : graph2.nodes) {
                    node.graph = graph1;
                }
                graphs.remove(graph2);
                if (graphs.size() == 1) {
                    return edge.node1.x * edge.node2.x;
                }
            }
        }
        throw new IllegalStateException("No solution found");
    }

    private static List<Edge> precalculateEdges(List<Node> nodes) {
        final List<Edge> edges = new ArrayList<>();
        for (int n1 = 0; n1 < nodes.size(); n1++) {
            for (int n2 = n1 + 1; n2 < nodes.size(); n2++) {
                edges.add(new Edge(nodes.get(n1), nodes.get(n2)));
            }
        }
        edges.sort(Comparator.comparing(e -> e.distance));
        return edges;
    }

    private static List<Node> parseNodes(String s) {
        final List<Node> nodes = new ArrayList<>();
        for (String line : s.split("\n")) {
            if (!line.isBlank()) {
                nodes.add(Node.parse(line));
            }
        }
        return nodes;
    }
}
