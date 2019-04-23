import java.util.List;

public class TSPAlgorithm {

    protected List<City> cities;
    protected int[][] distancesMatrix;
    protected long startTime;

    public TSPAlgorithm(List<City> cities) {
        this.cities = cities;

        int nrCities = this.cities.size();
        this.distancesMatrix = new int[nrCities][nrCities];
        generateDistancesMatrix();
    }

    // Fills the matrix with the cities' distance
    private void generateDistancesMatrix() {
        for (int x = 0; x < this.distancesMatrix[0].length; x++) {
            for (int y = 0; y < this.distancesMatrix[0].length; y++) {
                this.distancesMatrix[x][y] = this.cities.get(x).getDistance(this.cities.get(y));
            }
        }
    }

    public int tourLength(City[] tour) {
        int length = 0;

        for (int i = 0; i < tour.length - 1; i++) {
            length += this.distancesMatrix[tour[i].getIndexMatrix()][tour[i + 1].getIndexMatrix()];
        }

        length += this.distancesMatrix[tour[0].getIndexMatrix()][tour[tour.length - 1].getIndexMatrix()];
        return length;
    }

    public double printError(City[] tour, int best) {
        return ((double)(tourLength(tour) - best) / best) * 100;
    }

    public void startTime(){
        this.startTime = System.nanoTime();
    }

}
