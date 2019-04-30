import java.util.*;
import java.util.concurrent.TimeUnit;

public class SimulatedAnnealing {
    TSPAlgorithm tsp;
    Random rand;

    public SimulatedAnnealing(TSPAlgorithm tsp, Random rand) {
        this.tsp = tsp;
        this.rand = rand;
    }

    public City[] compute(City[] tour) {
        double t = 100;
        double a = 0.95;

        City[] current = tour.clone();
        City[] best = current;
        City[] candidate;

        long elapsedTime = 0;

        while (elapsedTime < 175) {
            for (int i = 0; i < 100; i++) {
                candidate = doubleBridge(current);
                new TwoOpt(this.tsp).compute(candidate);

                int currentDist = this.tsp.tourLength(current);
                int candidateDist = this.tsp.tourLength(candidate);

                if (candidateDist < currentDist) {
                    current = candidate;
                    int bestDist = this.tsp.tourLength(best);

                    if (currentDist < bestDist) {
                        best = current;
                    }
                } else if (this.rand.nextFloat() < Math.exp(( -((double)candidateDist - currentDist)) / t)) {
                    current = candidate;
                }
            }

            elapsedTime = TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - tsp.startTime);
            t = t * a;
        }

        System.out.println(elapsedTime);

        return best;
    }

    private City[] doubleBridge(City[] tour) {
        City[] candidate = new City[tour.length];

        List<Integer> index = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            int temp;

            do {
                temp = rand.nextInt(tour.length);
            } while (index.contains(temp) && index.contains(temp + 1) && temp != tour.length);

            index.add(temp);
            index.add(temp + 1);
        }

        Collections.sort(index);

        int a = index.get(0);
        int a1 = index.get(1);
        int b = index.get(2);
        int b1 = index.get(3);
        int c = index.get(4);
        int c1 = index.get(5);
        int d = index.get(6);
        int d1 = index.get(7);

        int destPos = 0;
        System.arraycopy(tour, 0, candidate, destPos, a1);

        destPos += a1;
        System.arraycopy(tour, c1, candidate, destPos, d-c1+1);

        destPos += d-c1+1;
        System.arraycopy(tour, b1, candidate, destPos, c-b1+1);

        destPos += c-b1+1;
        System.arraycopy(tour, a1, candidate, destPos, b-a1+1);

        destPos += b-a1+1;
        System.arraycopy(tour, d1, candidate, destPos, tour.length-d1);

        return candidate;
    }
}
