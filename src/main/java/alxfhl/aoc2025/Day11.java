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
        long result = 0;
        return result;
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
