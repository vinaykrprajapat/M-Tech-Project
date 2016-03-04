package convert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WebLogConvert2 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("/home/vinay/workspace/M-Tech-DP/src/lbl-conn-7/lbl-conn-7.red"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("/home/vinay/workspace/M-Tech-DP/src/lbl-conn-7.csv"));
//		int i=20;
		String line;
		String[] values = new String[9];
		try{
		while((line = br.readLine()) != null) {
//			while(i-->0) {
			line = br.readLine();
			values = line.split(" ");
			System.out.println(values.length);
			
			// System.out.println("host "+ Arrays.toString(values));
			 try{
			 System.out.println("timestamp "+values[0]);
			 System.out.println(" duration "+ values[1]);
					 System.out.println(" protocol "+ values[2]);
							 System.out.println(" bytes sent by originator "+ values[3]);
							 System.out.println(" bytes sent by responder "+ values[4]);
							 System.out.println(" local host "+ values[5]);
							 System.out.println(" remote host "+ values[6]);
							 System.out.println(" state  "+ values[7]);
							 System.out.println(" flags  "+ values[8]);
							 
			bw.write(values[0] +","+ values[1]+","+ values[2]+","+ values[3] + "," + values[4] + "," + values[5] + "," + values[6] + "," + values[7] + "," + values[8]  + "\n");
			}
			 catch(Exception mm){continue;}
			// bw.write(sb.toString());
		}
		}
		catch(Exception en){System.out.println(en);}
		br.close();
		bw.close();

	}

}



