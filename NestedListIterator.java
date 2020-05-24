// 341.
//run time - O(n) - iterate over all nested integers in the given i/p list
//space - O(n) - stack size is max, when the i/p list has only lists

//brute force - flatten the nested list into a queue recursively and then dequeue elements from queue
// private void flatten(List<NestedInteger> nestedList) {
//         for(NestedInteger element : nestedList) 
//         {
//             if(element.isInteger())
//             {
//                 support.offer(element.getInteger()); //add to queue
//             }
//             else
//             {
//                 flatten(element.getList()); //recurse on the list
//             }
//         }
//     }
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {
    
    Stack<ListIterator<NestedInteger>> support; //support stack of list iterators
    public NestedIterator(List<NestedInteger> nestedList) {
        support = new Stack<>();
        support.push(nestedList.listIterator()); //push the input nested list as a list iterator initially
    }

    @Override
    public Integer next() {
        hasNext(); //make sure that top of stack has cursor at an integer
        return support.peek().next().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!support.isEmpty())
        {
            //as long as stack has elements
            //has next method ensures that the cursor at the list iterator of stack top always points to an integer
            if(!support.peek().hasNext())
            {
                //if there are no more elements in the list iterator at stack top, then its purpose is done, so pop it
                support.pop();
            }
            else
            {
                NestedInteger current = support.peek().next(); //get the current cusrsor
                if(current.isInteger()) //current is an integer
                {
                    //move the cursor back by one step and return true
                    return(support.peek().previous().getInteger() == current.getInteger());
                }
                else //current is a list
                {
                    support.push(current.getList().listIterator()); //push the current(list) as a list iterator into stack
                }
            }
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
