// 642.
class Pair {
    //class with sentence text and its frequency
    String sentence;
    int count;
    
    public Pair(String sentence, int count) {
        this.sentence = sentence;
        this.count = count;
    }
}

class AutocompleteSystem {
    
    HashMap<String, Integer> database; //keeps track of frequency of each string
    String inputTracker; //keeps track of query
    int SUGGESTION_SIZE; //number os fuggestions to be returned
    
    public AutocompleteSystem(String[] sentences, int[] times) {
        database = new HashMap<>();
        inputTracker = "";
        SUGGESTION_SIZE = 3;
        
        for(int i = 0; i < sentences.length; i++)
        {
            database.put(sentences[i], times[i]); //no duplicates in sentences[] else handle accordingly
        }
    }
    
    public List<String> input(char c) {
        if(c == '#') //enter is pressed
        {
            if(inputTracker.length() != 0)
            {
                //add current query to db with respective count
                if(database.containsKey(inputTracker))
                {
                    database.put(inputTracker, database.get(inputTracker) + 1);
                }
                else
                {
                    database.put(inputTracker, 1);
                }
            }
            inputTracker = ""; //reset query string to empty and return empty result
            return new ArrayList<>();
        }
        
        //time - O(length of databse * length of longest sentence in db)
        //space - pq size
        
        inputTracker += c; //append current char to the query string
        //pq is heap which has matching strings(with input tracker) based on counts (if same lexicographic order)
        PriorityQueue<Pair> support = new PriorityQueue<>((a, b) -> (a.count == b.count) ? a.sentence.compareTo(b.sentence) : b.count - a.count);
        //add all matching strings into pq
        for(String sentence : database.keySet())
        {
            //alternatively add only till suggestion size is not reached to save space
            if(sentence.startsWith(inputTracker))
            {
                support.offer(new Pair(sentence, database.get(sentence))); //add all matching strings into pq
            }
        }
            
        List<String> result = new ArrayList<>(); //return
        while(!support.isEmpty() && result.size() < SUGGESTION_SIZE)
        {
            result.add(support.poll().sentence);
        }
            
        return result;
    }
}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */
