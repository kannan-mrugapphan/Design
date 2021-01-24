// 1396.
//space - O(number of unique stations ^ 2 + number of unique passangers) - for both maps
class UndergroundSystem {
    
    HashMap<Integer, arrivalInfo> arrivals; //map with key = id and value = object containing arrival info of id
    HashMap<String, travelInfo> travelDetails; //time taken to travel between 2 stations in the key for count times is cumulativeTime
    
    public UndergroundSystem() {
        arrivals = new HashMap<>();
        travelDetails = new HashMap<>();
    }
    
    //time - O(1)
    public void checkIn(int id, String stationName, int t) {
        //if id is already in map, return (one passanger can check into only one station)
        if(arrivals.containsKey(id))
        {
            return;
        }
        //add the entry into arrivals map
        arrivals.put(id, new arrivalInfo(id, stationName, t));
        return;
    }
    
    //time - O(1)
    public void checkOut(int id, String stationName, int t) {
        //if this station pair is new, add new entry in travel details
        arrivalInfo checkInDetails = arrivals.get(id);
        String stationPair = checkInDetails.stationName + ":" + stationName; //id traveled between these 2 stations
        int timeTaken = t - checkInDetails.t; //time taken to travel by id
        if(!travelDetails.containsKey(stationPair))
        {
            travelDetails.put(stationPair, new travelInfo(timeTaken, 1)); //first record of travel between stations 
        }
        //else update already pesent details
        else
        {
            travelInfo details = travelDetails.get(stationPair); //this station pair is already present
            //update cumulativeTime and count
            details.cumulativeTime += timeTaken; //time for current record
            details.count += 1; 
            travelDetails.put(stationPair, details);
        }
        //checkout id by removing it from arrivals map
        arrivals.remove(id);
        return;
    }
    
    //time - O(1)
    public double getAverageTime(String startStation, String endStation) {
        //station pair is present in travel details map
        String stationPair = startStation + ":" + endStation;
        travelInfo details = travelDetails.get(stationPair); //get the travel info of current record
        return (double) details.cumulativeTime / details.count; //average time taken by all passangers so far
    }
}

class arrivalInfo {
    int id; 
    String stationName; //passanged id checked into stationName at time t
    int t;
    
    public arrivalInfo(int id, String stationName, int t) {
        this.id = id;
        this.stationName = stationName;
        this.t = t;
    }
}

class travelInfo {
    int cumulativeTime; //time taken to travel between 2 stations count times = cumulativeTime
    int count;
    
    public travelInfo(int cumulativeTime, int count) {
        this.cumulativeTime = cumulativeTime;
        this.count = count;
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
