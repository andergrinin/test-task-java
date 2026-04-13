import java.util.*;

class City //class to store data in more managable way
{
    String Name;
    int p;//number of neighbors
    int index; //city index
    HashMap<Integer,Integer> paths; //graph data
    City(int index, String Name, int p) //city constructor
    {
        this.index=index;
        this.Name=Name;
        this.p=p;
        paths = new HashMap<Integer,Integer>();
        Scanner sc = new Scanner(System.in);
        for (int i=0;i<p;i++) { //paths input
            int temp_destination = sc.nextInt();
            int temp_cost = sc.nextInt();
            paths.put(temp_destination,temp_cost);
        }
    }
}
public class Task2Main {
        int calculatePath(ArrayList<City> cities, String source, String destination)
    {
        int n = cities.size(); //total number of cities (vertices in the graph)
        HashMap<String, Integer> nameToIndex = new HashMap<>();//map to convert city name -> its index
        for (City c : cities) { //fill the map
            nameToIndex.put(c.Name, c.index);
        }
        int src = nameToIndex.get(source); //index of source city
        int dest = nameToIndex.get(destination); //index of destination city
        //array to store shortest distances from source to every city
        int[] dist = new int[n + 1]; //+1 because cities indexes starting from 1
        Arrays.fill(dist, Integer.MAX_VALUE); //initially all distances are "infinity"
        dist[src] = 0; // distance from source to itself is 0
        //priority queue storing pairs: [cityIndex, currentDistance]
        //comparator ensures that the smallest distance is always on top
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0}); //start with source city and distance 0
        while (!pq.isEmpty()) { //main loop
            int[] current = pq.poll(); //extract the city with the smallest distance
            int cityIndex = current[0]; //current city index
            int currentDist = current[1]; //current known shortest distance to this city
            //if destination reached, can stop early
            if (cityIndex == dest) return currentDist;
            //if this distance is outdated (not equal to best known), skip it
            if (currentDist > dist[cityIndex]) continue;
            City city = cities.get(cityIndex - 1);
            //get the City object to iterate through all neighbors of the current city
            for (Map.Entry<Integer, Integer> entry : city.paths.entrySet()) {
                int neighbor = entry.getKey(); //neighbor city index
                int cost = entry.getValue(); //cost to travel to that neighbor
                //check if going through current city gives a shorter path
                if (dist[neighbor] > dist[cityIndex] + cost) {
                    dist[neighbor] = dist[cityIndex] + cost;
                    //update shortest distance to neighbor
                    pq.add(new int[]{neighbor, dist[neighbor]});
                    //push updated distance into priority queue
                }
            }
        }
        //if destination was not reached in loop, return distance
        return dist[dest];
    }
    void main() {
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();//number of tests
        for(int i=0;i<s;i++){
            int n = sc.nextInt();//number of cities
            ArrayList<City> cities = new ArrayList<City>();//array to store Cities
            for (int j = 0; j < n; j++) cities.add(new City(j + 1, sc.next(), sc.nextInt()));//filling array
            int r = sc.nextInt();
            int []results = new int[r];//way to store results to meet I/O requirements
            for(int j=0;j<r;j++)
            {
                String source= sc.next();
                String destination = sc.next();
                results[j]=calculatePath(cities, source,destination); //using main function to calculate distance
            }
            for (int j=0;j<r;j++) System.out.println(results[j]);
        }

    }
}
