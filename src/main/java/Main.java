import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        // Loads the preferred file from the resources folder from the project
        String fileName = "ch130.tsp";
        ClassLoader classLoader = new Main().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        List<City> listCities = new ArrayList();

        // Reads the city info from the loaded file
        listCities = TSMParser.Parse(file);
        int nrCities = listCities.size();

        // Matrix that contains the cities' distances, it is initialitzed to 0
        int[][] distancesMatrix = new int[nrCities][nrCities];


        // Fills the matrix with the cities' distance
        for(int x = 0; x < nrCities; x++){
            for(int y = 0; y < nrCities; y++){
                distancesMatrix[x][y] = listCities.get(x).getDistance(listCities.get(y));
            }
        }

        NearestNeighbor.compute(listCities, distancesMatrix);
    }

    // Static method that print any matrix to terminal
    public static void printMatrix(int[][] matrix) {
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                System.out.print(matrix[x][y] + "\t");
            }
            System.out.println();
        }
    }
}
