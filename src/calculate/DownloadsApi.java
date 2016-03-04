package calculate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class DownloadsApi {

   //public static void main(String args[]) throws IOException {
   public Object[] downloads() throws IOException{
		BufferedReader br = new BufferedReader(new FileReader("/home/vinay/workspace/M-Tech-DP-changes/data/access_log_Jul95"));
	//	BufferedWriter bw = new BufferedWriter(new FileWriter("/home/vinay/workspace/M-Tech-DP-changes/data/access_log_final.csv"));
		
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter number of records to be analysed");
		int i=sc.nextInt();
		String line;
		String[] values = new String[10];
		HashMap<String, Integer> hmapcount = new HashMap<String, Integer>();
		int count=1;
		HashMap<String, Integer> hmapmalcount = new HashMap<String, Integer>();
		int malcount=0;
		HashMap<String, Integer> hmap = new HashMap<String, Integer>();
		try{
//		while((line = br.readLine()) != null) {
			while(i-->0) {
				
			line = br.readLine();
			values = line.split(" ");
		//	System.out.println(values.length);
			
			// System.out.println("host "+ Arrays.toString(values));
			 try{
				 
			// System.out.println("host "+values[0]);
			// System.out.println(" date "+ values[3].replace("[", ""));
			// System.out.println(" code "+ values[4].replace("]", ""));
			// System.out.println(" method "+ values[5].replace("\"", ""));
			// System.out.println(" url "+ values[6]);
			// System.out.println(" httpversion "+ values[7].replace("\"", ""));
			// System.out.println(" status code "+ values[8]);
			// System.out.println(" bytes "+ values[9]);

		      /*Adding elements to HashMap*/
				 Integer th = 10;
				 if(hmap.containsKey(values[0]))
				 {
					 Integer bytes= hmap.get(values[0]);   //store current value of key in bytes					 
				     /* Remove values based on key*/
				     if(Integer.parseInt(values[9]) > (2*bytes) )
				     {
				    	 hmapmalcount.put(values[0], hmapmalcount.get(values[0])+1  );  // incrementing malicious count for that key
				     }
					 hmap.remove(values[0]);
     			     hmap.put(values[0], (Integer.parseInt(values[9])+bytes) );  // update key's value with new value[9] by adding with bytes
     			     hmapcount.put(values[0], hmapcount.get(values[0])+1  );  // incrementing count for that key
     			     
				 }
				 else
				 {
		      hmap.put(values[0], Integer.parseInt(values[9]) );
		      hmapcount.put(values[0], count );
		      hmapmalcount.put(values[0], malcount );
				 }	 

				 //		bw.write(values[0] +","+ values[3].replace("[", "") + "," + values[4].replace("]", "") + "," + values[5].replace("\"", "") + "," + values[6] + "," + values[7].replace("\"", "") + "," + values[8] + "," + values[9] + "\n");
			}
			 catch(Exception mm){continue;}
			// bw.write(sb.toString());
		}
		}
		catch(Exception en){System.out.println(en);}
		br.close();
	//	bw.close();

		/* Display content using Iterator*/
		
	/*      Set set = hmap.entrySet();
	      Iterator iterator = set.iterator();
	      while(iterator.hasNext()) {
	       Map.Entry mentry = (Map.Entry)iterator.next();
	       System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	       System.out.println(mentry.getValue()); 
	      }
	  */    
	      Object[] a = hmap.entrySet().toArray();
	      Arrays.sort(a, new Comparator() {
	          public int compare(Object o1, Object o2) {
	              return ((Map.Entry<String, Integer>) o2).getValue().compareTo(
	                      ((Map.Entry<String, Integer>) o1).getValue());
	          }
	      });
	      
	  	BufferedWriter bw = new BufferedWriter(new FileWriter("/home/vinay/workspace/M-Tech-DP-changes/data/access_log_final"));
	  	
	  	System.out.println("Number Of Unique Hosts/Users :"+ hmap.size() +"\n");   
	      for (final String key : hmap.keySet()) {
	    	    if (hmapcount.containsKey(key) && hmapmalcount.containsKey(key)) {
	    	        
	    	        int mal = (int)hmapmalcount.get(key);
	    	        int ttl = (int)hmapcount.get(key);
	    	        float result= ((float) mal/ (float) ttl)*100;
	    	        result = Math.round(result);
	    	       // float result = (float) (mal/ttl);
	    	        bw.write( key +" "+ result/10+"\n"); // writes the bytes
	    	        System.out.println(" host/user: " + key +" DownloadSize "+ hmap.get(key)+ " total count: "+ hmapcount.get(key) +" malcount: "+ hmapmalcount.get(key) +" risk sore: "+ result/10 );
	    	    }
	    	}
	      bw.close();
	      /*
	      Object[] b = hmapcount.entrySet().toArray();
	      Arrays.sort(b, new Comparator() {
	          public int compare(Object ob1, Object ob2) {
	              return ((Map.Entry<String, Integer>) ob2).getValue().compareTo(
	                      ((Map.Entry<String, Integer>) ob1).getValue());
	          }
	      });
	      
	      for (Object e : a) {
			  bw.write(((Map.Entry<String, Integer>) e).getKey() +"  "+  ((Map.Entry<String, Integer>) e).getValue() +"\n");
	    	  System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "
	                  + ((Map.Entry<String, Integer>) e).getValue());
	      }
	      
	      for (Object e1 : b) {			 
	    	  bw.write(((Map.Entry<String, Integer>) e1).getKey() +"  "+  ((Map.Entry<String, Integer>) e1).getValue() +"\n");
	    	  System.out.println(((Map.Entry<String, Integer>) e1).getKey() + " : "
	                  + ((Map.Entry<String, Integer>) e1).getValue());
	      }
	      */
	      
	    return a;
	} //main

} //class



