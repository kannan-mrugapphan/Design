// 380.
// space - O(number of elements) 
class RandomizedSet {

    /** Initialize your data structure here. */
    List<Integer> elements; //to keep track of the elements
    HashMap<Integer, Integer> index; //to keep track of the index of each element
    Random rand; //random number generator
    
    public RandomizedSet() {
        elements = new ArrayList<>();
        index = new HashMap<>();
        rand = new Random();
    }
    
    //time - O(1)
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        //check for duplicate
        if(index.containsKey(val))
        {
            return false; //val is present in the elements list
        }
        index.put(val, elements.size()); //else add val to end of elements list
        elements.add(val);
        return true;
    }
    
    //time - O(1)
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        //check for non exisestent val
        if(!index.containsKey(val))
        {
            return false; //val is not present in the elements list
        }
        int valIndex = index.get(val); //val is at valIndex in elements list
        int lastElement = elements.get(elements.size() - 1); //last element in elements list
        elements.set(valIndex, lastElement); //put the last element in the valIndex position
        index.put(lastElement, valIndex); //update poition of last element in valIndex
        index.remove(val); //remove val from index map
        elements.remove(elements.size() - 1); //remove the duplicate last element
        return true;
    }
    
    //time - O(1)
    /** Get a random element from the set. */
    public int getRandom() {
        int randIndex = rand.nextInt(elements.size()); //generates a random number between 0 and elements.size() - 1
        return elements.get(randIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
