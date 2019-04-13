public class TwoOpt {
    TSPAlgorithm tsp;

    public TwoOpt(TSPAlgorithm tsp) {
        this.tsp = tsp;
    }

    public void compute(City[] tour) {
        int bestGain = -1;

        while (bestGain < 0) {
            int gain, bestGainI = 0, bestGainJ = 0;
            bestGain = 0;

            for (int i = 0; i < tour.length - 2; i++) {

                for (int j = i + 2; j < tour.length; j++) {
                    gain = computeGain(i, j, tour);

                    if (gain < bestGain) {
                        bestGain = gain;
                        bestGainI = i;
                        bestGainJ = j;
                    }
                }
            }

            if (bestGain < 0)
                swap(bestGainI + 1, bestGainJ, tour);
        }
    }

    private int computeGain(int i, int j, City[] tour) {
        int tempComp = tsp.distancesMatrix[tour[i].getIndexMatrix()][tour[i + 1].getIndexMatrix()] +
                        tsp.distancesMatrix[tour[j].getIndexMatrix()][tour[(j + 1) % tour.length].getIndexMatrix()];
        int tempSwap = tsp.distancesMatrix[tour[i].getIndexMatrix()][tour[j].getIndexMatrix()] +
                        tsp.distancesMatrix[tour[i + 1].getIndexMatrix()][tour[(j + 1) % tour.length].getIndexMatrix()];
        return tempSwap - tempComp;
    }

    private void swap(int indexI, int indexJ, City[] tour) {
        while (indexI < indexJ) {
            City temp = tour[indexI];
            tour[indexI] = tour[indexJ];
            tour[indexJ] = temp;

            indexI++;
            indexJ--;
        }
    }
}
