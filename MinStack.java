// 155.
class MinStack {
    
    Stack<Integer> mainStack; //2 stacks to support min at O(1)
    Stack<Integer> supportStack;
    /** initialize your data structure here. */
    public MinStack() {
        mainStack = new Stack<>();
        supportStack = new Stack<>();
    }
    
    //time - O(1)
    public void push(int x) {
        //if x is smaller than or equal to top element of support stack or support stack is empty - push into both stacks, else push only into main stack
        if(supportStack.isEmpty() || x <= supportStack.peek())
        {
            supportStack.push(x);
        }
        mainStack.push(x);
        return;
    }
    
    //time - O(1)
    public void pop() {
        //if top of both stacks are same, pop from both, else pop from main stack only
        if(mainStack.size() > 0 && supportStack.size() > 0 && mainStack.peek().intValue() == supportStack.peek().intValue())
        {
            //.intValue() is used as auto unboxing is not working properly for large numbers(-1024, 512)
            supportStack.pop();
        }
        mainStack.pop();
        return;
    }
    
    //time - O(1)
    public int top() {
        //return top of main stack
        //if main stack is empty return -1
        return (mainStack.size() > 0) ? mainStack.peek() : -1;
    }
    
    //time - O(1)
    public int getMin() {
        //return top of support stack
        //if support stack is empty return -1
        return (mainStack.size() > 0) ? supportStack.peek() : -1;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
