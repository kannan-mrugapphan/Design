class SkipIterator {
    Iterator<Integer> it; //i/p iteartor of integers
    HashMap<Integer, Integer> skips; //skips maintains numbers to be skipped with count
    Integer nextElement; //cursor pointing to next valid element
    
    public SkipIterator(Iterator<Integer> it) {
        this.it = it;
        this.skips = new HashMap<>();
        advance(); //places nextElement cursor at a valid position
    }
    
    public boolean hasNext() {
        return(nextElement != null);
    }
    
    public int next(){
        //take val in nextElement and place the cursor in next valid position
        int answer = nextElement; 
        advance();
        return answer; //return stored value
    }
    
    public void skip(int number) {
        //if nextElement and number to be skipped are same, call advance to place nextElemnt at next valid place else add number to skips map and return
        if(nextElement == number) 
        {
            advance();
            return;
        }
        skips.put(number, skips.getOrDefault(number, 0) + 1);
        return;
    }
    
    public void advance() {
        //get it.next(), check if it is map, if so reduce count by 1 and get next() and check again
        nextElement = null;
        while(nextElement == null && it.hasNext())
        {
            Integer temp = it.next();
            if(skips.containsKey(temp))
            {
                skips.put(temp, skips.get(temp) - 1);
                skips.remove(temp, 0);
            }
            else
            {
                nextElement = temp;
            }
        }
        return;
    }
}

class Solution {
    public static void main(String[] args) {
        SkipIterator itr = new SkipIterator(Arrays.asList(2, 3, 5, 6, 5, 7, 5, -1, 5, 10).iterator());
        System.out.println(itr.hasNext()); // true
        System.out.println(itr.next()); // returns 2
        itr.skip(5);
        System.out.println(itr.next()); // returns 3
        System.out.println(itr.next()); // returns 6 because 5 should be skipped
        System.out.println(itr.next()); // returns 5
        itr.skip(5);
        itr.skip(5);
        System.out.println(itr.next()); // returns 7
        System.out.println(itr.next()); // returns -1
        System.out.println(itr.next()); // returns 10
        System.out.println(itr.hasNext()); //false
        System.out.println(itr.next()); //error
    }
}
