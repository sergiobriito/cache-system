package cache;

import java.util.HashMap;

public class CacheService {

    int capacity;
    HashMap<String, Node> map;
    Node head;
    Node tail;

    public CacheService(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public String get(String key) {
        Node node = map.get(key);
        if (node!=null){
            remove(node);
            add(node, head, head.next);
            return node.val;
        };
        return "";
    }

    public void put(String key, String value) {
        Node node = map.get(key);
        if (node!=null){
            remove(node);
            node.val = value;
            map.put(key, node);
            add(node, head, head.next);
        } else {
            if (map.size() == capacity){
                map.remove(tail.prev.key);
                remove(tail.prev);
            };

            Node newNode = new Node();
            newNode.key = key;
            newNode.val = value;

            map.put(key, newNode);
            add(newNode, head, head.next);
        };

    }

    public void add(Node node, Node head, Node next){
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
    };

    public void remove(Node node){
        Node next = node.next;
        Node prev = node.prev;
        prev.next = next;
        next.prev = prev;
    };

    public static class Node {
        String key;
        String val;
        Node prev;
        Node next;
        public Node(){};
    }

}
