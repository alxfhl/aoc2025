package alxfhl.aoc2025;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class Day11 {

    @Data
    @EqualsAndHashCode(of = "name")
    static class Node {
        final String name;
        List<Node> connections = new ArrayList<>();
        List<Node> incoming = new ArrayList<>();

        public String toString() {
            return name + "(in: " + incoming.stream().map(Node::getName).collect(Collectors.joining(","))
                    + " / out: " + connections.stream().map(Node::getName).collect(Collectors.joining(",")) + ")";
        }
    }

    public static long solve(String s) {
        Map<String, Node> map = getAllNodes(s);
        return getPathCount(map, "you", "out");
    }

    public static long solve2(String s) {
        Map<String, Node> map = getAllNodes(s);
        return getPathCount(map, "svr", "fft")
                * getPathCount(map, "fft", "dac")
                * getPathCount(map, "dac", "out");
    }

    private static Long getPathCount(Map<String, Node> map, String start, String end) {
        // check which subset of nodes is relevant for this task
        Set<Node> relevant = getReachableNodes(map, start);
        Map<String, Long> paths = new HashMap<>();
        paths.put(start, 1L);
        Queue<Node> todo = new ArrayDeque<>();
        Set<Node> done = new HashSet<>();
        todo.add(map.get(start));
        while (!todo.isEmpty()) {
            Node node = todo.remove();
            done.add(node);
            long value = paths.get(node.name);
            for (Node next : node.connections) {
                paths.merge(next.name, value, Long::sum);
                if (next.incoming.stream().filter(relevant::contains).allMatch(done::contains)) {
                    todo.add(next);
                }
            }
        }
        return paths.get(end);
    }

    private static Set<Node> getReachableNodes(Map<String, Node> map, String start) {
        Node startNode = map.get(start);
        Set<Node> reachable = new HashSet<>();
        reachable.add(startNode);
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(startNode);
        while (!queue.isEmpty()) {
            Node next = queue.remove();
            for (Node connection : next.connections) {
                if (!reachable.contains(connection)) {
                    reachable.add(connection);
                    queue.add(connection);
                }
            }
        }
        return reachable;
    }

    private static Map<String, Node> getAllNodes(String s) {
        List<String> lines = Arrays.stream(s.split("\n")).map(String::trim).filter(not(String::isEmpty)).toList();
        Map<String, Node> map = new HashMap<>();
        lines.stream()
                .flatMap(line -> Stream.of(line.split("[: ]+")))
                .distinct()
                .forEach(name -> map.put(name, new Node(name)));
        for (String line : lines) {
            String name = line.substring(0, line.indexOf(":"));
            String[] connections = line.split(": ")[1].split(" ");
            Node node = map.get(name);
            for (String connection : connections) {
                node.connections.add(map.get(connection));
            }
        }

        // we want to propagate a node's value only when we handled all incoming connections, so we need to keep track of those
        Map<Node, List<Node>> incomingConnections = new HashMap<>();
        for (Node node : map.values()) {
            for (Node connection : node.connections) {
                incomingConnections.computeIfAbsent(connection, _ -> new ArrayList<>()).add(node);
            }
        }
        for (var entry : incomingConnections.entrySet()) {
            entry.getKey().incoming.addAll(entry.getValue());
        }
        return map;
    }
}
