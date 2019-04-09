import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {

    private City[] route;
    private int[][] distancesMatrix;
    private List<City> cities;

    public SimulatedAnnealing(City[] route, int[][] distancesMatrix, List<City> cities) {
        this.route = route;
        this.cities = cities;
        this.distancesMatrix = distancesMatrix;
    }

    /*public City[] compute(){
        double t = 100;
        double a = 0.95;

        City[] current = this.route;
        City[] best = current;

        while(t >= 0.3){
            for(int i = 0; i < 100; i++){
                City[] next = doubleBridge(current);
                City[] candidate = new TwoOpt(next, this.distancesMatrix).compute();

                int currentDist = Main.calcPathDistance(current, this.distancesMatrix);
                int candidateDist = Main.calcPathDistance(candidate, this.distancesMatrix);

                if(candidateDist < currentDist){
                    current = candidate;
                    int bestDist = Main.calcPathDistance(best, this.distancesMatrix);

                    if(currentDist < bestDist){
                        best = current;
                    }
                } else if (new Random().nextFloat() < Math.pow(Math.E, -((candidateDist-currentDist)/t))) {
                    current = candidate;
                }
            }

            t = t*a;
        }

        return null;
    }*/

    private City[] doubleBridge(City[] route) {

        return null;
    }
}
