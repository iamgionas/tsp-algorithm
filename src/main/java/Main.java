import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        // Loads the preferred file from the resources folder from the project
        String fileName = "ch130.tsp";
        ClassLoader classLoader = new Main().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        List<City> listCities;

        // Reads the city info from the loaded file
        listCities = TSPParser.Parse(file);
        int nrCities = listCities.size();

        // Matrix that contains the cities' distances, it is initialitzed to 0
        int[][] distancesMatrix = new int[nrCities][nrCities];

        // Fills the matrix with the cities' distance
        for(int x = 0; x < nrCities; x++){
            for(int y = 0; y < nrCities; y++){
                distancesMatrix[x][y] = listCities.get(x).getDistance(listCities.get(y));
            }
        }

        City[] route = new City[nrCities];

        route = new NearestNeighbor(route, distancesMatrix, listCities).compute();
        calcPathDistance(route, distancesMatrix);
        route = new TwoOpt(route, distancesMatrix).compute();
        calcPathDistance(route, distancesMatrix);

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

    public static void calcPathDistance(City[] path, int[][] matrix){
        int length = 0;
        for(int i = 0; i < path.length-1; i++){
            length += matrix[path[i].getIndexMatrix()][path[i+1].getIndexMatrix()];
        }
        length += matrix[path[0].getIndexMatrix()][path[path.length-1].getIndexMatrix()];
        System.out.println(length);
    }


}
