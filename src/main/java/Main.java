import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception {

    }

    public static void start(String problem, long seed, int best){
        // Loads the preferred file from the resources folder from the project
        String fileName = problem + ".tsp";
        ClassLoader classLoader = new Main().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        List<City> listCities;

        // Reads the city info from the loaded file
        listCities = TSPParser.Parse(file);

        //This is the main tour passed to the algorithms
        City[] tour = new City[listCities.size()];
        Random n = new Random(seed);

        TSPAlgorithm tsp = new TSPAlgorithm(listCities);
        tsp.startTime();

        //Nearest
        NearestNeighbor nn = new NearestNeighbor(tsp);
        nn.compute(tour);

        //Simulated
        tour = new SimulatedAnnealing(tsp,n).compute(tour);
        //System.out.println("Lunghezza " + tsp.tourLength(tour));
        //System.out.println("Errore " + tsp.printError(tour, 224094));

        writeSolutions(problem, best, tour);
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

    public static void writeSolutions(String fileName, int bestKnown, City[] tour) {
        StringBuilder sb = new StringBuilder();
        sb.append("NAME : " + fileName + ".opt.tour").append("\n");
        sb.append("COMMENT : Optimum tour for " + fileName + ".tsp (" + bestKnown + ")").append("\n");
        sb.append("TYPE : TOUR").append("\n");
        sb.append("DIMENSION : " + tour.length).append("\n");
        sb.append("TOUR_SECTION").append("\n");
        for (City c: tour) {
            sb.append(c.getId()).append("\n");
        }
        sb.append("-1").append("\n");
        sb.append("EOF");

        FileWriter fileWriter = null;
        String DIRPATH = "/Users/gionasbonardi/Desktop/";
        try {
            fileWriter = new FileWriter(DIRPATH + fileName + ".opt.tour");
            fileWriter.write(sb.toString());
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
