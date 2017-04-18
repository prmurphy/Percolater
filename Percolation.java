

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;



public class Percolation{
    private boolean[] grid;
    private WeightedQuickUnionUF uf;
    private int virtualBottom;
    private int virtualTop = 0;
    private int maxRows;
    //private Percolation percArray;
    private int numberOfOpenSites = 0;
    
   
    public Percolation(int n){
    	virtualBottom = n*n +1;
    	grid = new boolean[(n)*(n)+2];
        
    	
    	uf = new WeightedQuickUnionUF(n*n + 2);
    	
    	maxRows = n;
    	if(n != 1){
    		for (int i = 1; i < n+1; i++){
    			uf.union(virtualTop, i );
    		} 
    	
    	
    		for (int i = n*n; i > n*n- (n); i--){
    			uf.union(virtualBottom, i);
    	
    		}
    	}
    	
    	
    	
    	
    }              // create n-by-n grid, with all sites blocked
    
    public    void open(int row, int col){
    	int xytogrid = XYtoInt(row, col);
    	//StdOut.println(xytogrid);	
    	if (grid[xytogrid] == false){
    		numberOfOpenSites ++;
    	}
    	grid[xytogrid] = true;
    	int xytogridAbove = XYtoInt(row -1, col);
    	int xytogridBelow = XYtoInt(row + 1, col);
    	int xytogridLeft = XYtoInt(row, col -1);
    	int xytogridRight = XYtoInt(row, col +1);
    	
    	
    	if (isElementExists(grid, xytogridAbove)){
    		if (grid[xytogridAbove] == true){
    			uf.union(xytogrid, xytogridAbove );
    		}
    		
    	}
    	
    	if (isElementExists(grid, xytogridBelow)){
    		if (grid[xytogridBelow] == true){
    			uf.union(xytogrid, xytogridBelow );
    		}
    		
    	}
    	
    	if (isElementExists(grid, xytogridLeft)){
    		//This if prevents the table grid from looping left to right
    		if (col != 1){
    			if (grid[xytogridLeft] == true){
    				uf.union(xytogrid, xytogridLeft );
    			}
    		}
    	}
    	
    	if (isElementExists(grid, xytogridRight)){
    		if (col != maxRows)
    		if (grid[xytogridRight] == true){
    			uf.union(xytogrid, xytogridRight );
    		}
    		
    	}    	

    }    // open site (row, col) if it is not open already
    	
    
    public boolean isOpen(int row, int col){
    	int xytogrid = XYtoInt(row, col);
    	if (grid[xytogrid] == true){
    	return true;
    	} else{
    	return false;
    	}
    	
    }  // is site (row, col) open?
    
    public boolean isFull(int row, int col){
    	if (maxRows == 1 && grid[1] == true){
        	return true;
        }
    	int xytogrid = XYtoInt(row, col);
    	if (grid[xytogrid] == true && uf.connected(xytogrid, virtualTop)){
    		//StdOut.println(xytogrid + " is full");
    		return true;
    	}else{
    		//StdOut.println(xytogrid + " is NOT full");
    		return false;
    	}
    }  // is site (row, col) full?
    
    public     int numberOfOpenSites(){
        return numberOfOpenSites;
    }      // number of open sites
    
    public boolean percolates(){
        if (maxRows == 1 && grid[1] == true){
        	return true;
        }
    	if(uf.connected(virtualTop, virtualBottom)){
        	//StdOut.println("It's percolating!");
    		return true;
        }else{
        	//StdOut.println("NOT percolating");
        	return false;
        }
    }              // does the system percolate?
    
    private int XYtoInt(int row, int col ){
    	int ArrayNumber = (row -1) * maxRows + col;
    	return ArrayNumber;
    }
    
    private static boolean isElementExists(boolean [] grid, int index){
        try{
          boolean a = grid[index];
          return true;
        } catch(ArrayIndexOutOfBoundsException e){
        	//StdOut.println("element does not exist!");
        	return false;
        }
    }

  


    
    
    public static void main(String[] args)
  {
	 
	int N = StdIn.readInt();

   
   
   Percolation myPerc = new Percolation(N);
/*   myPerc.open(1, 1);
   StdOut.println(myPerc.isOpen(1, 1));
   StdOut.println(myPerc.percolates());
    }
  }*/
   
   
   
   while (!StdIn.isEmpty())
   {
    int p = StdIn.readInt();
    int q = StdIn.readInt();
    myPerc.open(p,q );
    myPerc.percolates();
    StdOut.println(myPerc.isFull(p, q));
   }
  }
}