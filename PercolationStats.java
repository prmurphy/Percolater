//import Percolation.Percolation.PercolationStats;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
//import static java.lang.Math.sqrt;

public class PercolationStats {
	//private Percolation myPerc;
	private double N;
	private double numberOfTrials;
	private double[] arrayForMeans;
	private double percMean;
	private double SumOfpercMean = 0;
	private double mean;
	private double standardDev;
	private double high;
	private double low;
	
	
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();                                  
        }

		arrayForMeans = new double[trials];
		numberOfTrials = trials; 
		N = n *n;
		
		
		for (int i = 0; i < trials; i++){
			Percolation myPerc = new Percolation(n);
			while ( !myPerc.percolates()){
				int a = StdRandom.uniform(1, n + 1);
				
				int b = StdRandom.uniform(1, n + 1);
				
				myPerc.open(a, b);
				
				
				
			}
			
			percMean = myPerc.numberOfOpenSites()/N;
			
			SumOfpercMean = SumOfpercMean + percMean;
			
			arrayForMeans[i] = percMean;
		}	
			
			mean = SumOfpercMean/numberOfTrials;
			standardDev = StdStats.stddev(arrayForMeans);
			high =  mean - (1.96*standardDev)/(Math.sqrt(numberOfTrials));
			low =  mean  + (1.96*standardDev)/(Math.sqrt(numberOfTrials));
	}   
		
	
	public double mean() {
		
		return mean;
	}                         // sample mean of percolation threshold
	
	public double stddev()  {
		//standardDev = StdStats.stddev(arrayForMeans);		
		return standardDev;
	}                      // sample standard deviation of percolation threshold
	public double confidenceLo() {
		
		return high;
	}                 // low  endpoint of 95% confidence interval
	public double confidenceHi() {
		
		return low;
	}
	   public static void main(String[] args)
	   {
	 	 
	 	int N = StdIn.readInt();
	 	int T = StdIn.readInt();

	    PercolationStats myStats = new PercolationStats(N, T);
	    StdOut.println("mean                    = "+ myStats.mean());
	    StdOut.println("stddev                  = " + myStats.stddev());	   
	    StdOut.println("95% confidence interval = [" +myStats.confidenceLo() + ", "+ myStats.confidenceHi() + "]");

	   }
}
