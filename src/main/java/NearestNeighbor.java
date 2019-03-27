import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NearestNeighbor {

    private City[] route;
    private int[][] distancesMatrix;
    private List<City> cities;

    public NearestNeighbor(City[] route, int[][] distancesMatrix, List<City> cities){
        this.route = route;
        this.cities = cities;
        this.distancesMatrix = distancesMatrix;
    }

    public City[] compute() {
        Map<Integer, City> visited = new LinkedHashMap<>();

        int startCityIndex = 0;
        int lastCityIndex = startCityIndex;

        visited.put(startCityIndex, cities.get(startCityIndex)); // Add first city to the path

        while (visited.size() < cities.size()) {
            int minDistance = Integer.MAX_VALUE;
            int minCityIndex = 0;

            for (int i = 0; i < cities.size(); i++) {
                if(!visited.containsKey(i) && distancesMatrix[i][lastCityIndex] < minDistance){
                    minDistance = distancesMatrix[i][lastCityIndex];
                    minCityIndex = i;
                }
            }

            lastCityIndex = minCityIndex;
            visited.put(lastCityIndex, cities.get(lastCityIndex));
        }

        return visited.values().toArray(route);
    }
}