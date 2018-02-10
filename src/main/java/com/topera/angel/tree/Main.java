package com.topera.angel.tree;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String args[] ) throws Exception {
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //String input = br.readLine();
        String input = "[\n" +
                "  {\"id\": 1, \"name\": \"San Francisco Bay Area\", \"parent_id\": null},\n" +
                "  {\"id\": 2, \"name\": \"San Jose\", \"parent_id\": 3},\n" +
                "  {\"id\": 3, \"name\": \"South Bay\", \"parent_id\": 1},\n" +
                "  {\"id\": 4, \"name\": \"San Francisco\", \"parent_id\": 1},\n" +
                "  {\"id\": 5, \"name\": \"Manhattan\", \"parent_id\": 6},\n" +
                "  {\"id\": 6, \"name\": \"New York\", \"parent_id\": null}\n" +
        "]";

        JSONParser parser = new JSONParser();
        JSONArray jsonInput = (JSONArray) parser.parse(input);

        Node rootNode = createTree(jsonInput);
        printNode(rootNode, 0);
    }

    private static Node createTree(JSONArray jsonInput) {
        Node rootNode = new Node(null, "ROOT");
        addNodes(jsonInput, rootNode);
        return rootNode;
    }

    private static void addNodes(JSONArray jsonInput, Node parent) {
        // iterates table
        Iterator iterator = jsonInput.iterator();
        while (iterator.hasNext()) {
            JSONObject row = (JSONObject) iterator.next();
            if (row.get("parent_id") == parent.getId()) {
                Node childNode = new Node((Long) row.get("id") , (String) row.get("name"));
                parent.addChild(childNode);
            }
        }

        // calls recursion in children
        for (Node childNode : parent.getNodes()) {
            addNodes(jsonInput, childNode);
        }
    }

    private static void printNode(Node node, Integer deep) {
        node.sortChildren();
        if (node.getId() != null) {
            int i = 1;
            while(i < deep) {
                System.out.print("-");
                i++;
            }
            System.out.println(node.getName());
        }
        for (Node childNode : node.getNodes()) {
            printNode(childNode, deep + 1);
        }
    }

}

class Node implements Comparable<Node> {

    private Long id;
    private String name;
    private List<Node> nodes = new ArrayList<>();

    Node(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    void addChild(Node child) {
        nodes.add(child);
    }

    List<Node> getNodes() {
        return nodes;
    }

    Long getId() {
        return id;
    }

    String getName() {
        return name;
    }

    void sortChildren(){
        Collections.sort(nodes);
    }

    @Override
    public int compareTo(Node node) {
        return name.compareTo(node.getName());
    }
}