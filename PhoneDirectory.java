// 379.
class PhoneDirectory {
    
    HashSet<Integer> availableNumbers; //set tracking available numbers
    //have a map of id to alloted number to keep track of alloted numbers - if asked
    
    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        availableNumbers = new HashSet<>();
        for(int i = 0; i < maxNumbers; i++)
        {
            availableNumbers.add(i); //add all numbers to available set from 0 to maxNumber    
        }
    }
    
    //time - O(1)
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        //have an iterator over the available set and return next()
        int phNumber = -1; //return -1 if no number is available
        for(Integer number : availableNumbers)
        {
            phNumber = number; //break out of for each blocak after seeing 1st number
            availableNumbers.remove(phNumber);
            break;
        }
        return phNumber;
    }
    
    //time - O(1)
    /** Check if a number is available or not. */
    public boolean check(int number) {
        return availableNumbers.contains(number);
    }
    
    //time - O(1)
    /** Recycle or release a number. */
    public void release(int number) {
        availableNumbers.add(number); //hashset automatically handles duplicates
        return;
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
