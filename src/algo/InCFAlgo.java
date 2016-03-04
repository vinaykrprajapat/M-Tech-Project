package algo;

import java.lang.reflect.Array;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import org.decimal4j.util.DoubleRounder;

import calculate.DownloadsApi;

public class InCFAlgo {
	public static void main(String args[]){

	Scanner sc=new Scanner(System.in);
	
	double[][] user_risk_matrix = new double[][]{
		  { 2, 3, 0, 3, 2 },
		  { 0, 2, 0, 0, 2 },
		  { 2, 2, 0, 3, 2 }
		};
		
		float rho = (float) 0.99, d_th = (float) 0.5; float C=1,beta;
		double distM=0,mem=0;
	float[][] In = new float[][]{
		  { 1, 0, 0, 0, 0 },
		  { 0, 1, 0, 0, 0 },
		  { 0, 0, 1, 0, 0 },
		  { 0, 0, 0, 1, 0 },
		  { 0, 0, 0, 0, 1 }
		};
		
		float[][] Ki = new float[][]{
		  { 0, 0, 0, 0, 0 },
		  { 0, 0, 0, 0, 0 },
		  { 0, 0, 0, 0, 0 },
		  { 0, 0, 0, 0, 0 },
		  { 0, 0, 0, 0, 0 }
		};	
		
		float[][] Knew = new float[][]{
		  { 0, 0, 0, 0, 0 },
		  { 0, 0, 0, 0, 0 },
		  { 0, 0, 0, 0, 0 },
		  { 0, 0, 0, 0, 0 },
		  { 0, 0, 0, 0, 0 }
		};		
	
	double[][] W = new double[5][3];
	double[][] sub = new double[5][1];
	double[][] subt = new double[1][5];
	double[][] p = new double[][]{
		{0},
		{0},
		{0},
		{0},
		{0}
	};
	double[][] pi = new double[5][1];
	double[][] pt = new double[1][5];
	
	for ( int o = 0; o < 5; o++ )
        for ( int s= 0; s < 5; s++ )
            Ki[o][s] = rho * In[o][s];
            
	System.out.println(Arrays.deepToString(Ki));        
	
	
	for(int i=1;i<=3;i++)
	{
		for(int k=0;k<5;k++)
		{
			for(int j=i-1;j<i;j++)
			{
				p[k][0]=user_risk_matrix[j][k];
				//System.out.println( p[k][0]);
			}
		}	
				if(i==1)
				{
					for ( int m = 0; m < 5; m++ ){
				       W[m][i-1] =  p[m][0];
				       //System.out.println(W[m][i-1]);
				       }
		
					for ( int o = 0; o < 5; o++ )
				        for ( int s= 0; s < 5; s++ )
				         Knew[o][s] =  Ki[o][s] + (rho * In[o][s]);
					
					System.out.println(Arrays.deepToString(Knew));
				}
				else
				{
					int count = i ;
					HashMap<Double, Double> list_mem = new HashMap<Double, Double>();
					while(count > 1)
					{
						
				//	Inverse in = new Inverse();
					double Kinverse[][] = Inverse.invert(Knew);
					
					for ( int q = 0; q < 5; q++ ){
				            pi[q][0] = p[q][0] - user_risk_matrix[i-count][q];
				            //System.out.println(pi[q][0]);
				            pt[0][q] = pi[q][0] ;
				        }
					System.out.println("pt"+Arrays.deepToString(pt));
					System.out.println("pi"+Arrays.deepToString(pi));
					Multiply mi = new Multiply();
					double[][] c = mi.multiply(pt, Kinverse);

					double[][] cs = mi.multiply(c, pi);
					 distM =  cs[0][0];
					 distM = DoubleRounder.round(distM, 4);
					 distM =  Math.sqrt(distM);	
					 mem = Math.exp(-(distM/2));
					System.out.println("distM = "+ DoubleRounder.round(distM, 4, RoundingMode.UP) );
					System.out.println("mem(p"+i+",w1) = "+ DoubleRounder.round(mem, 4, RoundingMode.DOWN));
					
					list_mem.put(distM, mem);

					count--;
					}
					
					double max_distM = (Collections.max(list_mem.values()));  // This will return max value in the Hashmap
			        for (Entry<Double, Double> entry : list_mem.entrySet()) {  // Itrate through hashmap
			            if (entry.getValue()==max_distM) {
			                System.out.println(entry.getKey());     // Print the key with max value
			            }
			        }
					
			        
			        
					if(max_distM <= d_th)
					{
						// update W
						C=C+1;
						beta=1/C;
						for ( int m = 0; m < 5; m++ ){
						       W[m][i-1] = (1-beta) * W[m][i-2] + beta * p[m][0];
						       //System.out.println(W[m][i-1]);
						       
						   sub[m][0]  = p[m][0] - W[m][i-1]; 
						   subt[0][m] = sub[m][0];
						}
						Multiply mi = new Multiply();
						double[][] d = mi.multiply(sub, subt);
						
						for(int m = 0; m < 5; m++)
							for( int mn = 0; mn < 5; mn++)
								{Ki[m][mn] = (1-beta) * Ki[m][mn];
								Knew[m][mn] = Ki[m][mn] + (beta * (1-(beta*beta)) * (float)d[0][0]);							
								}
					}
					else
					{
						// create new cluster
						Knew = Ki;
						for ( int m = 0; m < 5; m++ ){
						       W[m][i-1] =  p[m][0];
						       //System.out.println(W[m][i-1]);
						       }
						System.out.println(Arrays.deepToString(W));
					}
					
				}//else end
				
			}	
	}
	
}	