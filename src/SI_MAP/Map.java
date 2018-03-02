package SI_MAP;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

 
public class Map {
	
	
 public static void main(String[] args) throws IOException, InterruptedException {
	 
	 String IP="9.109.116.185:8113", dir="C:\\Program Files (x86)\\Sterling Commerce\\Map Editor",user="map_test",password="password";
	 JTextField path = new JTextField(13);
  	JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("IP Address:PORT"));
	    myPanel.add(path);
	    path.setText(IP);
	    
	    JTextField id = new JTextField(8);
	  	    myPanel.add(new JLabel("ID"));
		    myPanel.add(id);
		    id.setText(user);
		    
		    JPasswordField psw = new JPasswordField(8);
	  	    myPanel.add(new JLabel("Password"));
		    myPanel.add(psw); 
		    psw.setText(password);
	    
	    String dest="";
	    Path path2=null,path3=null;
	    int RowIndex=0;
	    
	    Date date = new Date();
    	String modifiedDate= new SimpleDateFormat("yyyyMMdd").format(date);
	    
	    String[] cmd = new String[5000];
	    int flag=0,flag1=0;
	    int k=0;
	   // String selectedlog="NO";
	    
	    
	    
	    JTextField path1 = new JTextField(35);
	  	myPanel.add(new JLabel("SI Installation Directory"));
		myPanel.add(path1);
	    path1.setText(dir);
	    
	    String[] options = new String[] {"DevTeam", "SupportTeam"};
	    JComboBox<String> optionList = new JComboBox<>(options);
	    myPanel.add(optionList);
	  
	    
	    String option=null ;
	   
	    
	    
	  	
	    
	    int result = JOptionPane.showConfirmDialog(null, myPanel, 
	               "SI MAP TEST", JOptionPane.OK_CANCEL_OPTION);
	      if (result == JOptionPane.OK_OPTION) {
	    	    if (path.getText().trim().equals("") || path1.getText().trim().equals("") || id.getText().trim().equals("") || psw.getText().equals("") )
	    	    	{
	    	    	JOptionPane.showMessageDialog(null, "Please provide data");
	    	    	System.exit(0);
	    	    	
	    	    	}
	    	    else
	    	    {
	    	    	IP = path.getText().trim();
	    	    	dir= path1.getText().trim();
	    	    	user =id.getText().trim();
	    	    	password=psw.getText().trim();
	    	    	option = (String) optionList.getSelectedItem();
	    	    	
	    	    	
	    	    }
	    	    
	    	    
	      }
	      else{
  	    	System.out.println("Cancel button pressed");
  	    	System.exit(0);
  	    }
	      
	        Workbook workbook=new XSSFWorkbook();
		     
	       
	        
	        Sheet Sheet = workbook.createSheet("STATUS");
	       
	        
	        //Cell style for header row
	        CellStyle cs = workbook.createCellStyle();
	        cs.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
	        cs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
	        Font f2 = workbook.createFont();
	        f2.setBoldweight(Font.BOLDWEIGHT_BOLD);
	        f2.setFontHeightInPoints((short) 12);
	        cs.setFont(f2);
	        
	        Cell c=null;
	        Row row = Sheet.createRow(RowIndex++);
	        
	         c= row.createCell(0);
	         c.setCellValue("MapName");
	         c.setCellStyle(cs);
	         
	         c=row.createCell(1);
	         c.setCellValue("InputFile");
	         c.setCellStyle(cs);
	         
	         c=row.createCell(2);
	         c.setCellValue("Status");
	         c.setCellStyle(cs);
	         
	       ReplaceCobol obj= new ReplaceCobol();  
	      
	      File root = new File( ".");
	      File[] list = root.listFiles();
	      
	      for (int i=0;i<list.length;i++)
          System.out.println(list[i]);
	      
	      
	      for ( File f : list ) {
	            if ( f.isDirectory() )
	            {
	            	File level1 = new File(f.getAbsolutePath());
	      	        File[] list1 = level1.listFiles();
	      	      
	      	      for (int i=0;i<list1.length;i++)
	      	      {   flag=0;
	      	    	  if (list1[i].getName().substring(list1[i].getName().length()-4).equals(".txo") )
	      	    	  {
	      	    		
	      	  
	      	          
   		      	      
   		      	      
	      	    		  
	      	    		for ( File f1 : list1 ) {
	    	  	            if ( f1.isDirectory() && f1.getName().toString().toLowerCase().equals("input") )
	    	  	            {
	    	  	            	File level2 = new File(f1.getAbsolutePath());
	    		      	        File[] list2 = level2.listFiles();
	    		      	      
	    		      	        if (flag==0)
	    		      	        {
	    		      	        	path2 = Paths.get(level1.getAbsolutePath()+"\\Output");
	    		   		      	    path3 = Paths.get(level1.getAbsolutePath()+"\\TranslationReport");
	    		      	        	
	    		      	        	//Delete Output and Translation folder
	    		   		      	FileUtils.deleteDirectory(new File(path2.toString())); 
	    		   		        FileUtils.deleteDirectory(new File(path3.toString()));
	    		      	        	
	    		      	        	//Creates Output and Translation folder
	    		      	        	Files.createDirectories(path2);
	    		   		      	    Files.createDirectories(path3);
	    		   		      	     
	    		      	      
	    		      	    FileWriter fw = new FileWriter(path2.toString()+"\\CompareFile.xml");
	    			         PrintWriter pw = new PrintWriter(fw);
	    			         
	    			         //Write to file for the first row
	    			           pw.print("<TranslationReport><![CDATA[[Translation report contains no data.]]></TranslationReport>");
	    		      	      
	    			            //Flush the output to the file
	    				         pw.flush();
	    				         
	    				         //Close the Print Writer
	    				         pw.close();
	    				         
	    				         //Close the File Writer
	    				         fw.close();
	    		      	      dest = path2.toString() +"\\"+list1[i].getName();
	    		      	      System.out.println(dest);
	    		      	      System.out.println(list1[i].toPath());
	    		      	      Files.copy(list1[i].toPath(), Paths.get(dest),StandardCopyOption.REPLACE_EXISTING);
	    		      	      flag=1;
	    		      	      
	    		      	      }
	    		      	        
	    		      	    for(k=0;k<list2.length;k++) 
	    		      	    {cmd[k]=dir+"\\Mapper.exe -T "+IP+" " +user +" " +password+" "+dest +" "+list2[k]+" "+" "+path2.toString();
	    		              System.out.println(cmd[k]);
	    		             Process p= Runtime.getRuntime().exec(cmd[k]);
	    		              
	    		              p.waitFor();
	    		      	      File oldfile = new File(path2.toString()+"\\MapTestTxResult.txt");
	    		      	      File newfile = new File(path2.toString()+"\\"+list2[k].getName().substring(0,list2[k].getName().length()-4)+"_DDF.csv");
	    		      	      
	    		      	      System.out.println(oldfile.toString() +"   "+ newfile.toString());
	    		      	      
	    		      	      File oldfilelog = new File(path2.toString()+"\\MapTestTxReport.xml");
	    		      	      File newfilelog = new File(path2.toString()+"\\"+list2[k].getName().substring(0,list2[k].getName().length()-4)+"_MapTestTxReport_"+modifiedDate+".xml");
	    		      	      
	    		      	       System.out.println(oldfilelog.toString() +"   "+ newfilelog.toString());
	    		      	       
	    		      	     
	    		      	     row = Sheet.createRow(RowIndex++); 
	    		      	      //Rename XML log file
	    		      	    Files.move(oldfilelog.toPath(),newfilelog.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    		      	    
	    		      	    FileCompare comparefile = new FileCompare();
	    		      	    String com = path2.toString()+"\\CompareFile.xml";
	    		      	    System.out.println(com);
	    		      	    Path compath = Paths.get(com);
	    		      	    
	    		      	    String transpath = path3.toString()+"\\"+newfilelog.getName();
	    		      	   
	    		      	    flag1=0;
	    		      	   // Compare the file
	    		      	    if(comparefile.Compare(newfilelog.toPath(),compath) )
	    		      	    {   
	    		      	    	flag1 = 1;
	    		      	    	//Deletes the mapping report file
	    		      	    	if(option.equals("DevTeam"))
	    		      	    	{	  
	    		      	    		Files.delete(newfilelog.toPath());
	    		      	    		 
	    		      	    	}
	    		      	    	else
	    		      	    	{	
	    		      	    	Path translation1 = Paths.get(transpath);
		    		      	    Files.move(newfilelog.toPath(),translation1, StandardCopyOption.REPLACE_EXISTING);
		    		      	     
	    		      	    	}
	    		      	    	row.createCell(0).setCellValue(list1[i].getName().substring(0, list1[i].getName().length()-4));
	    		      	    	row.createCell(1).setCellValue(list2[k].getName());
	    		      	    	row.createCell(2).setCellValue("Success");
	    		      	    	
	    		      	    	
	    		      	    	
	    		      	    }
	    		      	    
	    		      	    
	    		      	      
	    		      	      // Rename map result file 
	    		      	      Files.move(oldfile.toPath(),newfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    		      	       
	    		      	     // Call Cobol Method to Edit the file
	    		      	      obj.ReplaceFile(newfile.toPath().toString());
	    		      	      
	    		      	       
	    		      	       //delete GIS Error log file
	    		      	       Files.delete(Paths.get(path2.toString()+"\\GIS_Map_Test_ErrorReport.txt"));
	    		      	       
	    		      	       //Move the Translation Report File
	    		      	       
	    		      	  if (flag1==0)
	    		      	  {   
	    		      	      
	    		      	       
	    		      	     Path translation2 = Paths.get(transpath);
	    		      	     Files.move(newfilelog.toPath(),translation2, StandardCopyOption.REPLACE_EXISTING);
	    		      	   row.createCell(0).setCellValue(list1[i].getName().substring(0, list1[i].getName().length()-4));
   		      	    	  row.createCell(1).setCellValue(list2[k].getName());
   		      	    	  row.createCell(2).setCellValue("Failed");
	    		      	  }
	    		      	      
	    		      	    }
	    		      	    
	    		      	    //Deletes the txo file
	    		      	   if(flag==1)     
	    		      	    Files.delete(Paths.get(dest));
	    		      	   
	    		      	//Deletes the compare file 
	    		      	 Files.delete(Paths.get(path2.toString()+"\\CompareFile.xml"));
	    		      	 
	    		      	 // Deletes the Transalation Folder if empty
	    		      	 if (path3.toFile().list().length==0  && option.equals("DevTeam"))
	    		      		 {
	    		      		 Files.delete(path3);
	    		      		 
	    		      		 }
	      	    		  
	      	    	  }
	      	      }
	    	      
	    	      
	  	            }
	    	      
	    	      }
	    	      
	            }
	           
	        }
	      try {
	 			FileOutputStream fileName = new FileOutputStream("StatusReport.xlsx");
	 			 workbook.write(fileName);
	 		        fileName.close();
	 		    System.out.println("File written successfully");
	 		} catch (FileNotFoundException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
 
 
 
 }
}

 
