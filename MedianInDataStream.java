// 295.
// brute force - have an array to store the numbers, when a new number comes in each time, find the place where this num has to be inserted and shift the other numbers accordingly (runs at O(n) time for 1 number), then find median in O(1) time for 1 number - total run time - O(n^2)

// median - middle element in sorted array if size is odd else average of middle 2 elements
// have a max heap to store the first half numbers and min heap to store the last half numbers of the array
// the top of max heap and top of min heap gives the middle 2 elements of sorted array
// time - O(nlogn)
// space - O(n)
class MedianFinder {

    /** initialize your data structure here. */
    PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    
    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
        //current num is the first num or current num is less than top of max heap (meaning current has to go in the max heap)
        if(maxHeap.size() == 0 || num < maxHeap.peek())
        {
            maxHeap.offer(num);
        }
        else //current num is in the 2nd half
        {
            minHeap.offer(num);
        }
        
        //the heaps must be of same size or max heap (1st half) can be larger than 2nd half by 1 (happens when size is odd)
        if(maxHeap.size() > 1 + minHeap.size())
        {
            minHeap.offer(maxHeap.peek());
            maxHeap.poll();
        }
        else if(maxHeap.size() < minHeap.size())
        {
            maxHeap.offer(minHeap.peek());
            minHeap.poll();
        }
    }
    
    public double findMedian() {
        int size = maxHeap.size() + minHeap.size();
        
        if(size % 2 != 0)
        {
            return maxHeap.peek();
        }
        return (double)(maxHeap.peek() + minHeap.peek()) / 2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
