public class TwoOpt {

    private City[] tour;
    private int[][] distanceMatrix;

    public TwoOpt(City[] tour, int[][] distanceMatrix){
        this.tour = tour;
        this.distanceMatrix = distanceMatrix;
    }

    public City[] compute(){
        int bestGain = -1;

        while(bestGain < 0) {
            int gain, bestGainI = 0, bestGainJ = 0;
            bestGain = 0;

            for (int i = 0; i < tour.length - 2; i++) {

                for (int j = i + 2; j < tour.length; j++) {
                    gain = computeGain(i, j);

                    if (gain < bestGain) {
                        bestGain = gain;
                        bestGainI = i;
                        bestGainJ = j;
                    }
                }
            }

            if(bestGain < 0)
                swap(bestGainI+1, bestGainJ);
        }

        return tour;
    }

    private int computeGain(int i, int j){
        int tempComp = distanceMatrix[tour[i].getIndexMatrix()][tour[i + 1].getIndexMatrix()] + distanceMatrix[tour[j].getIndexMatrix()][tour[(j + 1) % tour.length].getIndexMatrix()];
        int tempSwap = distanceMatrix[tour[i].getIndexMatrix()][tour[j].getIndexMatrix()] + distanceMatrix[tour[i + 1].getIndexMatrix()][tour[(j + 1) % tour.length].getIndexMatrix()];
        return tempSwap - tempComp;
    }

    private void swap(int indexI, int indexJ){
        System.out.println(indexI + " - " + indexJ);

        while(indexI < indexJ){
            City temp = tour[indexI];
            tour[indexI] = tour[indexJ];
            tour[indexJ] = temp;

            indexI++;
            indexJ--;
        }
    }
}
