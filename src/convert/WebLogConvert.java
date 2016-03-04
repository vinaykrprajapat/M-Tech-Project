package convert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class WebLogConvert {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader("/home/vinay/workspace/M-Tech-DP-changes/src/access_log_Jul95"));

		BufferedWriter bw = new BufferedWriter(new FileWriter("/home/vinay/workspace/M-Tech-DP-changes/src/access_log.csv"));
		int i=117867;
		String line;
		String[] values = new String[10];
		try{
	//	while((line = br.readLine()) != null) {
			while(i-->0) {
			line = br.readLine();
			values = line.split(" ");
			System.out.println(values.length);
			
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
							 
			bw.write(values[0] +","+ values[3].replace("[", "") + "," + values[4].replace("]", "") + "," + values[5].replace("\"", "") + "," + values[6] + "," + values[7].replace("\"", "") + "," + values[8] + "," + values[9] + "\n");
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



