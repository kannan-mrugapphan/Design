class ParkingSpot {
    int floorNumber; //unique id of parking spot (composite key in table structure)
    int spotNumber;
    
    public ParkingSpot(int floorNumber, int spotNumber) {
        this.floorNumber = floorNumber;
        this.spotNumber = spotNumber;
    }
    
    //getters for the fields
    public int getFloorNumber()
    {
        return this.floorNumber;
    }
    public int getspotNumber()
    {
        return this.spotNumber;
    }
    //setters go here - if needed
}

class ParkingLot {
    int floors; //number of floors
    int spots; //spots per floor;
    PriorityQueue<ParkingSpot> support; //support pq to get next available slot, park and unpark - min heap based on precedence of spot filling
    
    public ParkingLot(int floors, int spots) {
        this.floors = floors;
        this.spots = spots;
        //slots aare assigned in increasing order of spot numbers in each floor followd by increasing order of floor nummbers
        support = new PriorityQueue<>((a, b) -> (a.floorNumber == b.floorNumber) ? a.spotNumber - b.spotNumber : a.floorNumber - b.floorNumber);
    }
    
    //time - O(capacity of lot)
    public void addSpots() {
        for(int i = 0; i < floors; i++)
        {
            for(int j = 0; j < spots; j++)
            {
                support.offer(new ParkingSpot(i, j));
            }
        }
        return;
    }
    
    //time - O(log(capacity of lot))
    public void addSpot(int floorNumber, int spotNumber) {
        if(support.size() == spots * floors)
        {
            throw new IllegalArgumentException("Parking Lot is full");
        }
        support.offer(new ParkingSpot(floorNumber, spotNumber));
        return;
    }
    
    //time - O(log(capacity of lot))
    public ParkingSpot getNextAvailableSlot() {
        if(support.size() == 0)
        {
            throw new IllegalStateException("Parking Lot is full");
        }
        return support.peek();
    }
    
    //time - O(log(capacity of lot))
    public ParkingSpot park() {
        if(support.size() == 0)
        {
            throw new IllegalStateException("Parking Lot is full");
        }
        return support.poll();
    }
    
    //time - O(log(capacity of lot))
    public void unpark(int floorNumber, int spotNumber) {
        //make sure that this spot is not already in heap (by a seperate set if needed)
        support.offer(new ParkingSpot(floorNumber, spotNumber));
        return;
    }
}

public class Driver {
    public static void main(String[] args) {
        ParkingLot pl = new ParkingLot(2, 2);
        
        pl.addSpots();
        //pl.addSpot(300,400);
        
        ParkingSpot next = pl.getNextAvailableSlot();
        System.out.println("Next Available Slot is at floor " + next.floorNumber + " and spot " + next.spotNumber); //expected(0,0)
        
        ParkingSpot one = pl.park();
        
        next = pl.getNextAvailableSlot();
        System.out.println("Next Available Slot is at floor " + next.floorNumber + " and spot " + next.spotNumber); //expected(0,1)
        
        ParkingSpot two = pl.park();
        
        next = pl.getNextAvailableSlot();
        System.out.println("Next Available Slot is at floor " + next.floorNumber + " and spot " + next.spotNumber); //expected(1,0)
        
        pl.unpark(one.floorNumber, one.spotNumber);
        next = pl.getNextAvailableSlot();
        System.out.println("Next Available Slot is at floor " + next.floorNumber + " and spot " + next.spotNumber); //expected(0,0) 
    }
}
