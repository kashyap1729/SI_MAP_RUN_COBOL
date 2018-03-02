package SI_MAP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ReplaceCobol {
	public  void ReplaceFile(String path) throws IOException {
		File file= new File(path);
		String search = "TAGNAME,FIELDNAME,DESCRIPTION,FIELDSTART,FIELDLENGTH,MANDATORY,TYPE,FORMAT";  
		String replacement = "";
		String FirstLine="RECORD NAME,RECORD DESCRIPTION,RECORD TAG,TAG POSITION,RECORD MIN LOOP,RECORD MAX LOOP,,\r\n";
		String SecondLine=",FIELD NAME,FIELD DESCRIPTION,FIELD START,FIELD LENGTH,MANDATORY,TYPE,FORMAT\r\n";
		String s;
		String totalStr=FirstLine+SecondLine;
		
		//file reading
		FileReader fr = new FileReader(file);
		
		
		   BufferedReader br = new BufferedReader(fr);

		    while ((s = br.readLine()) != null) 
		    {      
		    	s=s.replaceAll(search, replacement);
		    	if (!s.equals(""))
		    	{totalStr += s + "\r\n";
		    	
		    	}
		    	
		        
		       
		    }
		    totalStr = totalStr.replaceAll(search, replacement);
	        FileWriter fw = new FileWriter(file);
	       
	        fw.write(totalStr);
	        fw.close();
		  
		   br.close();
		   fr.close();
		   System.out.println("String <<"+search+">> Removed");
		   System.out.println("File Content added <<"+FirstLine +SecondLine+">> at the begining");
		   
		}

}
