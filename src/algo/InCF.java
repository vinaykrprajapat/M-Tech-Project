package algo;

import java.lang.reflect.Array;

import calculate.DownloadsApi;

public class InCF {
	public static void main(String args[]){
	float[][] user_risk = new float[][]{
		  { 2, 3, 0, 3, 2 },
		  { 0, 2, 0, 0, 2 },
		  { 2, 2, 0, 3, 2 }
		};
		
		System.out.println();
		System.out.println("User Risk matrx");
		for ( int i = 0; i < 3; i++ ){
	        for ( int j= 0; j < 5; j++ ){
	            System.out.print(user_risk[i][j] + " ");
	        	}
	        System.out.println();
	        }		
		
	float[][] w1 = new float[][]{
		{2},
		{3},
		{0},
		{3},
		{2}
		};
	float[][] p1 = w1;	
	
	System.out.println();
	System.out.println("Input first p1");
	for ( int i = 0; i < 5; i++ ){
        for ( int j= 0; j < 1; j++ ){
            System.out.print(p1[i][j] + " ");
        	}
        System.out.println();
        }
	
	float rho = (float) 0.99, dth = (float) 0.5;
	System.out.println();
    System.out.println("rho ="+rho+" dth = "+dth);
	float[][] In = new float[][]{
	  { 1, 0, 0, 0, 0 },
	  { 0, 1, 0, 0, 0 },
	  { 0, 0, 1, 0, 0 },
	  { 0, 0, 0, 1, 0 },
	  { 0, 0, 0, 0, 1 }
	};

    System.out.println();
	System.out.println("In =");

	for ( int i = 0; i < 5; i++ ){
        for ( int j= 0; j < 5; j++ ){
            System.out.print(In[i][j] + " ");
        	}
        System.out.println();
        }
	
	float[][] Ki = new float[][]{
	  { 0, 0, 0, 0, 0 },
	  { 0, 0, 0, 0, 0 },
	  { 0, 0, 0, 0, 0 },
	  { 0, 0, 0, 0, 0 },
	  { 0, 0, 0, 0, 0 }
	};
	
	for ( int i = 0; i < 5; i++ )
        for ( int j= 0; j < 5; j++ )
            Ki[i][j] = rho * In[i][j];

    System.out.println();
	System.out.println("Ki =");
	for ( int i = 0; i < 5; i++ ){
        for ( int j= 0; j < 5; j++ ){
            System.out.print(Ki[i][j] + " ");
        	}
        System.out.println();
        }
	
	float[][] Knew = new float[][]{
		  { 0, 0, 0, 0, 0 },
		  { 0, 0, 0, 0, 0 },
		  { 0, 0, 0, 0, 0 },
		  { 0, 0, 0, 0, 0 },
		  { 0, 0, 0, 0, 0 }
		};

	for ( int i = 0; i < 5; i++ )
        for ( int j= 0; j < 5; j++ )
         Knew[i][j] =   Ki[i][j] + (rho * In[i][j]);

	System.out.println();
	System.out.println("Knew = Ki + rho * In");
	for ( int i = 0; i < 5; i++ ){
        for ( int j= 0; j < 5; j++ ){
            System.out.print(Knew[i][j] + " ");
        	}
        System.out.println();
        }

	float[][] p2 = new float[][]{
		{0},
		{2},
		{0},
		{0},
		{2}
		};
	
	System.out.println();
	System.out.println("Input p2");
	for ( int i = 0; i < 5; i++ ){
        for ( int j= 0; j < 1; j++ ){
            System.out.print(p2[i][j] + " ");
        	}
        System.out.println();
        }
	
	Inverse in = new Inverse();
	double Kinverse[][] = in.invert(Knew);
	
	System.out.println();
	System.out.println("Kinverse");
	for ( int i = 0; i < 5; i++ ){
        for ( int j= 0; j < 5; j++ ){
            System.out.print(Kinverse[i][j] + " ");
        	}
        System.out.println();
        }
	
	double[][] pt = new double[1][5];
	double[][] pi = new double[][]{
		{0},
		{0},
		{0},
		{0},
		{0}
		};
		
	for ( int i = 0; i < 5; i++ ){
        for ( int j= 0; j < 1; j++ ){
            pi[i][j] = p2[i][j] - p1[i][j];
        	}
	}
	
	for ( int i = 0; i < 1; i++ ){
        for ( int j= 0; j < 5; j++ ){
            pt[i][j] = pi[j][i] ;
        	}
	}
        
	Multiply mi = new Multiply();
	double[][] c = mi.multiply(pt, Kinverse);

	double[][] cs = mi.multiply(c, pi);
	double distM = 0;
	

	for ( int i = 0; i < 1; i++ ){
        for ( int j= 0; j < 1; j++ ){
        	distM = cs[i][j];
        	}
        System.out.println();
        }
		
	System.out.println("distM = "+Math.sqrt(distM));
	System.out.println("mem(p2,w1) = "+Math.exp(-(Math.sqrt(distM)/2)));
	
	}
	
	
	
}	