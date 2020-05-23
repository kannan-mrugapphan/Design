// 146.
//node class of doublly linked list with key, value, prev and next
public class Node {
    int key;
    int value;
    Node prev;
    Node next;
    
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}

class LRUCache {
    
    HashMap<Integer, Node> support; //map of key to corresponding node reference
    Node head; //head and tail pointers of dll
    Node tail;
    int capacity; //cache size
    public LRUCache(int capacity) {
        this.support = new HashMap<>();
        this.head = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.tail = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.capacity = capacity;
    }
    
    //time - O(1)
    public int get(int key) {
        //get the node reference from map, delete it, add to head to mark it as most recently used 
        //return its value
        if(!support.containsKey(key))
        {
            return -1; //key is not present
        }
        Node current = support.get(key);
        deleteNode(current);
        insertAtHead(current);
        return current.value;
    }
    
    //time - O(1)
    public void put(int key, int value) {
        //check if key is already present
        if(support.containsKey(key))
        {
            Node current = support.get(key);
            current.value = value; //update value
            deleteNode(current); //delete and move it to head to mark it as most recently used
            insertAtHead(current);
        }
        else
        {
            Node current = new Node(key, value); // create a new node
            //capacity is not reached
            if(support.size() < capacity)
            {
                //add to map and add to head of dll
                support.put(key, current);
                insertAtHead(current);
            }
            else
            {
                Node leastRecentlyUsed = tail.prev; //last node of dll
                support.remove(leastRecentlyUsed.key); //remove lru from dll and map(key of lru node)
                deleteNode(leastRecentlyUsed); 
                //add to map and add to head of dll
                support.put(key, current);
                insertAtHead(current);
            }
        }
        return;
    }
    
    //time - O(1)
    private void insertAtHead(Node current) {
        //insert current at head of dll
        Node temp = head.next;
        head.next = current;
        current.prev = head;
        current.next = temp;
        temp.prev = current;
        return;
    }
    
    //time - O(1)
    private void deleteNode(Node current) {
        //delete current from dll
        current.prev.next = current.next;
        current.next.prev = current.prev;
        return;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
