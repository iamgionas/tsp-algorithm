import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NearestNeighbor {

    public static void compute(List<City> cities, int[][] dMatrix) {
        Map<Integer, City> visited = new LinkedHashMap<>();

        int startCityIndex = 0;
        int lastCityIndex = startCityIndex;
        int sum = 0;

        visited.put(startCityIndex, cities.get(startCityIndex)); // Add first city to the path

        while (visited.size() < cities.size()) {
            int minDistance = Integer.MAX_VALUE;
            int minCityIndex = 0;

            for (int i = 0; i < cities.size(); i++) {
                if(!visited.containsKey(i) && dMatrix[i][lastCityIndex] < minDistance){
                    minDistance = dMatrix[i][lastCityIndex];
                    minCityIndex = i;
                }
            }

            sum += minDistance;
            lastCityIndex = minCityIndex;
            visited.put(lastCityIndex, cities.get(lastCityIndex));
        }

        Object[] t = visited.values().toArray();
        System.out.println(((City)t[1]).getId());

        sum += dMatrix[lastCityIndex][startCityIndex];
        System.out.println(sum);
    }
}