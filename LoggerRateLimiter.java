// 359.
//approach - have a map (message to a node with message and timestamp) and a dll os size 10 (dll tracks the 10 recenntly printed messages)
//case 1- if message is new, then 2 cases, if size of dll is hit, remove last node from dll(removes oldest printed message), add new msg to map and head of dll, return true;
//case 2 - if message is already seen, check for time stamp diff of 10, if so return true after updating map(node's timestamp changes to current time stamp) and remove this node from dll and add to head of dll, if timestamp diff is not 10 also update map and dll but return false
//dll is not needed to just return true or false but needed if asked about the information on n recently printed msgs(here n is 10)

//time - all opns - O(1)
class Node {
    String message;
    int timestamp;
    Node prev;
    Node next;
    
    public Node(String message, int timestamp) {
        this.message = message;
        this.timestamp = timestamp;
        this.prev = null;
        this.next = null;
    }
}

class Logger {
    
    HashMap<String, Node> msgDict; //map of message string to its node in dll
    Node head; //dummy head and tail of dll
    Node tail;
    int dllSize; //dll size which should be atmost message_gap
    int MESSAGE_GAP; //10 in this prob
    
    /** Initialize your data structure here. */
    public Logger() {
        this.msgDict = new HashMap<>();
        this.head = new Node("", -1);
        this.tail = new Node("", -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.dllSize = 0;
        this.MESSAGE_GAP = 10;
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) { 
        //case 1 - incoming message is never seen before (not in msgDict)
        if(!msgDict.containsKey(message))
        {
            Node current = new Node(message, timestamp); //create the equivalent node
            if(dllSize == MESSAGE_GAP) //if capacity is reached, remove last node in dll(last node is the oldest msg that was printed)
            {
                Node last = removeLast();
                msgDict.remove(last.message);
            }
            msgDict.put(message, current); //add current node to map and dll's head
            addToHead(current);
            return true;
        }
        //case 2 - incoming msg is already seen
        else
        {
            Node current = msgDict.get(message); //get that message node
            //later than 10 secs
            if(timestamp - current.timestamp >= MESSAGE_GAP)
            {
                current.timestamp = timestamp; //update timestamp in that node
                msgDict.put(message, current);  //update node in msgDict map
                removeNode(current); //remove that node in dll and add to head
                addToHead(current);
                return true;
            }
            //within 10 secs
            else
            {
                removeNode(current); //remove that node in dll and add to head
                addToHead(current);
                return false;
            }
        }
    }
    
    //time - O(1)
    private void addToHead(Node current) {
        current.next = head.next;
        current.prev = head;
        head.next = current;
        current.next.prev = current;
        dllSize++;
        return;
    }
    
    //time - O(1)
    private Node removeLast() {
        Node last = tail.prev;
        last.prev.next = last.next;
        last.next.prev = last.prev;
        dllSize--;
        return last;
    }
    
    //time - O(1)
    private void removeNode(Node current) {
        current.next.prev = current.prev;
        current.prev.next = current.next;
        return;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */
