import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        // Loads the preferred file from the resources folder from the project
        String fileName = "rat783.tsp";
        ClassLoader classLoader = new Main().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        List<City> listCities;

        // Reads the city info from the loaded file
        listCities = TSPParser.Parse(file);

        //This is the main tour passed to the alorithms
        City[] tour = new City[listCities.size()];
        Random n = new Random(24323424);

        TSPAlgorithm tsp = new TSPAlgorithm(listCities);

        System.out.println("----------NearestNeighbor----------");
        NearestNeighbor nn = new NearestNeighbor(tsp);
        nn.compute(tour);
        System.out.println("Lunghezza " + tsp.tourLength(tour));
        System.out.println("Errore " + tsp.printError(tour, 8806));

        /*System.out.println("----------TwoOpt----------");
        TwoOpt to = new TwoOpt(tsp);
        to.compute(tour);
        System.out.println("Lunghezza " + tsp.tourLength(tour));
        System.out.println("Errore " + tsp.printError(tour, 6110));*/

        System.out.println("----------SA----------");
        tour = new SimulatedAnnealing(tsp,n).compute(tour);
        System.out.println("Lunghezza " + tsp.tourLength(tour));
        System.out.println("Errore " + tsp.printError(tour, 8806));

        Set<City> set = new HashSet<>();

        // Iterate through the array
        for (City t : tour) {
            // Add each element into the set
            set.add(t);
        }

        System.out.print(set.size());
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
