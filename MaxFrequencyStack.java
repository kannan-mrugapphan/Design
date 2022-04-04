// 895.
// space - O(n) -> all elements are stored in data and map
class FreqStack {
    
    private List<Stack<Integer>> data;
    private HashMap<Integer, Integer> frequency; //freq map
    
    public FreqStack() {
        data = new ArrayList<>();
        frequency = new HashMap<>();
    }
    
    //time - O(1)
    public void push(int val) {
        int currentFreq = frequency.getOrDefault(val, 0); //get the current freq of val to be pushed
        frequency.put(val, currentFreq + 1); //update freq of val
        if(data.size() < currentFreq + 1) 
        {
            data.add(new Stack<Integer>()); //if there is no stack corresponding to current freq, create new stack at end
            data.get(data.size() - 1).push(val); //add val to it
        }
        else
        {
            data.get(currentFreq).push(val); //get the stack corresponding to current freq and push val into it
        }
        return;
    }
    
    //time - O(1)
    public int pop() {
        //most frequent (last inserted in case of tie) is at top of last stack
        int popValue = data.get(data.size() - 1).pop(); 
        //after removing most frequent element, if last stack becomes empty remove it
        if(data.get(data.size() - 1).size() == 0)
        {
            data.remove(data.size() - 1);
        }
        //reduce freq by 1 in the map and remove entry if freq becomes 0
        int currentFreq = frequency.get(popValue);
        frequency.put(popValue, currentFreq - 1);
        if(frequency.get(popValue) == 0)
        {
            frequency.remove(popValue);
        }
        return popValue;
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */
