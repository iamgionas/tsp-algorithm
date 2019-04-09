import java.util.LinkedHashMap;
import java.util.Map;

public class NearestNeighbor {
    TSPAlgorithm tsp;

    public NearestNeighbor(TSPAlgorithm tsp) {
        this.tsp = tsp;
    }

    public void compute(City[] tour) {
        Map<Integer, City> visited = new LinkedHashMap<>();

        int startCityIndex = 0;
        int lastCityIndex = startCityIndex;
        int nrCities = tsp.cities.size();

        visited.put(startCityIndex, tsp.cities.get(startCityIndex)); // Add first city to the path

        while (visited.size() < nrCities) {
            int minDistance = Integer.MAX_VALUE;
            int minCityIndex = 0;

            for (int i = 0; i < nrCities; i++) {
                if (!visited.containsKey(i) && tsp.distancesMatrix[i][lastCityIndex] < minDistance) {
                    minDistance = tsp.distancesMatrix[i][lastCityIndex];
                    minCityIndex = i;
                }
            }

            lastCityIndex = minCityIndex;
            visited.put(lastCityIndex, tsp.cities.get(lastCityIndex));
        }

        visited.values().toArray(tour);
    }
}