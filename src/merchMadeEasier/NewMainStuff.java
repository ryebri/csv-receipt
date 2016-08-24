package merchMadeEasier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class NewMainStuff implements Runnable{

	private Thread threadOne;
	private File fin;
	private static String fout;
	private int perP;
	private static int permC;
	private String delim;
	private static JProgressBar bar;
	private static String stringNames[];
	private static int location;
	private static MerchItems merch;
	private static int pageNum;
	private static int specialPage;
	private static int count;
	private volatile boolean stop;
	
	
	public NewMainStuff(File in, String out, int perPage, int permCols, String delim, JProgressBar bar)
	{
		fin = in;
		fout = out;
		perP = perPage;
		permC = permCols;
		this.delim = delim;
		this.bar = bar;
		stringNames = new String[3];
		location = 0;
		pageNum = 0;
		specialPage = 0;
		stop = false;
		count = 0;
		threadOne = new Thread(this, "new thread1");
		threadOne.start();	
	}

	@Override
	public void run() {
		try {
			File f = new File(fout+"/"+"printReady");
			f.mkdirs();
			merch = new MerchItems(fin, delim);
			bar.setValue(bar.getValue()+10);
		
			int error = readInCust();
			if(error == 1)
			{
				bar.setValue(10);
				bar.setIndeterminate(false);
				return;
			}
			//allows for the user to see the progress bar move
			threadOne.sleep(50);
			
			bar.setValue(60);
			PDFCreate p = new PDFCreate(fout);
			p.createDoc(pageNum, specialPage);
			//process has completed, so the progress bar can be set to the maximum
			bar.setValue(bar.getMaximum());
			
		} catch (FileNotFoundException | InterruptedException e) {


			JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel,  "File was not found/was not formatted properly", "Improper File Type", 
					JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	/*
	 * Is the main logic behind reading in the files and formatting them properly
	 */
	private int readInCust()
	{
		Scanner scanLine;
		Scanner scanWords;
		ArrayList<String>info;
		Items stuff;
		//int count=0;	//used for debugging purposes, also could be implemented to show how many files were read/created
		
		bar.setIndeterminate(true);
		
		try {
			scanLine = new Scanner(fin);
			scanLine.nextLine();
			
			

				scanWords = new Scanner(scanLine.nextLine());
				scanWords.useDelimiter(delim);
				info = new ArrayList<String>();
				String temp = scanWords.nextLine();
				if(delim.equals(","))
				{
					if(temp.contains(","))
					{
						scanWords.close();
						scanLine.close();
						scanLine = new Scanner(fin);
						scanLine.nextLine();
						
					 } else {
						JOptionPane.showMessageDialog(new JPanel(),  "You did not select the proper delimiter for the file", "Input Error", 
								JOptionPane.ERROR_MESSAGE);
						scanWords.close();
						scanLine.close();
						return 1;
					}
					
				} else {
					if(temp.contains("\t"))
					{
						scanWords.close();
						scanLine.close();
						scanLine = new Scanner(fin);
						scanLine.nextLine();
						delim = "\t";
						
					} else {
						JOptionPane.showMessageDialog(new JPanel(),  "You did not select the proper delimiter for the file", "Input Error", 
								JOptionPane.ERROR_MESSAGE);
						scanWords.close();
						scanLine.close();
						return 1;
					}
				}
				
				//Creates an infinite loop with proper break points for reading in from the file
				while(this.stop == false)
				{
					scanWords = new Scanner(scanLine.nextLine());
					scanWords.useDelimiter(delim);
					info = new ArrayList<String>();
//					temp = scanWords.nextLine();
//					temp = scanWords.next();

				//Adds everything to the Array List for future sorting/formatting
					while(scanWords.hasNext())
					{
						temp = scanWords.next();
						info.add(temp);
						
						
					}
				stuff = new Items(info, fout);
				stuff.createTextFile();
				stringNames[location] = stuff.getFileName().toString();

				//Calls the method to add the names of columns which contain values to the document
				addColumnNames(stuff.getFileName());

				//Depending on how many forms are to be on one page, the switch statement interprets them
				//Note that the location variable must be at the proper location for it to merge the files
				//It will continue on scanning/formatting files until the proper number combination is reached
				//then it will call the proper statement and merge files as appropriate
				switch (perP)
				{
				case 1:
					if(location ==0)
					{
						merge1File();
						location--;
					}
					break;
					
				case 2:
					if(location ==1)
					{
						merge2Files();
						location--;
					}
					break;
					
				case 3:
					if(location ==2)
					{
						merge3Files();
						
						location--;
					}
				}
				scanWords.close();
				count++;
				if(!scanLine.hasNext())
				{
					break;
				}
				
				location++;
				
			}
			if(this.stop == true)
			{
				scanLine.close();
				
				return 1;
			}
			bar.setIndeterminate(false);
			bar.setValue(bar.getValue()+40);
			threadOne.sleep(50); //so that the user can see the progress bar move
			
			scanLine.close();
			
			int numLeft=0;
			for(int i = 0; i<2; i++)
			{
				if(!stringNames[i].equals(""))
				{
					numLeft++;
				}
			}
			
			switch (perP)
			{
			case 1:

				break;
			case 2:
				merge2Files();
				break;
			case 3:
				merge3Files();
				break;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	private static void addColumnNames(File fileName)
	{
		
		Scanner scan;
		Scanner check;
		int count = 1;
		PrintWriter write;
		StringBuilder sb = new StringBuilder();
		StringBuilder output = new StringBuilder();
		
		try {
			scan = new Scanner(fileName);
			scan.useDelimiter("\\n");
			while(scan.hasNext())
			{
				sb.append(scan.nextLine()+"\r\n");

				count++;
			}
			check = new Scanner(fileName);

			String temp = check.nextLine();
			//if the Item has a value or is one of the permanent columns, it is added to the individual form
			for(int i = 0; i<merch.getArrayList().size(); i++)
			{
				if(!temp.equals("")||i<permC)
				{
					output.append(merch.getArrayList().get(i) + ":   " + temp + "\n");
				}
				
				if(check.hasNextLine()==false)
				{
					break;
				}
				temp = check.nextLine();
			}
			scan.close();
			
			
			
			
			scan = new Scanner(output.toString());
			String temp3 = scan.nextLine();
			write = new PrintWriter(fileName);
			while(true)
			{
				write.println(temp3);
				if(!scan.hasNextLine())
				{
					break;
				}
				temp3 = scan.nextLine();
			}
			
			
			
			write.close();
			check.close();
			

			
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		
		
		
		
	}
	
	private static void merge3Files()
	{
		Scanner scan;
		PrintWriter print;
		StringBuilder sb;
		File output;
		File reading;
		int counter=0;
		location = 0;
		
		output = new File(fout+"/"+"printReady"+"/" +"page" + pageNum + ".txt");
		

		try {
			print = new PrintWriter(output);
			sb = new StringBuilder();
			while(location <3)
			{
				reading = new File(stringNames[location]);
				if(!reading.toString().equals(""))
				{
					
				
//				sb = new StringBuilder();
				scan = new Scanner(reading);
				String temp = scan.nextLine();
				while(true)
				{

					sb.append(temp +"\r\n");
					
					if(!scan.hasNextLine())
					{
						break;
					}
					temp = scan.nextLine();
					counter++;

					//Handles the exceptions of if a form has too many items
					//Creates a new page, with only one form on a page, names it specialPage#.txt
					if(counter >14)
					{

						sb = new StringBuilder();
						scan.close();
						print.close();
						print = new PrintWriter(fout + "/" +"printReady/specialpage"+specialPage+".txt");
						specialPage++;
						scan = new Scanner(reading);
						int stop = location;
						switch(stop)
						{
						case 0:
							while(scan.hasNextLine())
							{
								sb.append(scan.nextLine()+"\r\n");
							}
							print.print(sb.toString());
							print.close();
							print = new PrintWriter(output);
							location =1;
							break;
							
						case 1:
							while(scan.hasNextLine())
							{
								sb.append(scan.nextLine()+"\r\n");
							}
							print.print(sb.toString());
							print.close();
							print = new PrintWriter(output);
							stringNames[1] = stringNames[2];
							stringNames[2]="";
							location = 0;
							break;
							
						case 2:
							while(scan.hasNextLine())
							{
								sb.append(scan.nextLine()+"\r\n");
							}
							print.print(sb.toString());
							print.close();
							print = new PrintWriter(output);
							stringNames[2]="";
							break;
						}
						sb= new StringBuilder();
						counter = 0;
						break;
						
					}
				}
				scan.close();
				} else {
					break;
				}
				if(counter !=0)
				{
					for(int i = counter; i < 16; i++)
					{
						//makes sure every form takes up the same space
						sb.append("\r\n");
					}
					if(location <2)
					{
						//adds lines to separate the forms
						for(int i = 0; i<80; i++)
						{

							sb.append("-");
						}

						sb.append("\r\n");
						sb.append("\r\n");
					}
					

					counter =0;
					location++;
				}
			}
			print.print(sb.toString());

			
			print.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		stringNames[0] ="";
		stringNames[1]="";
		stringNames[2]="";
		location = 0;
		pageNum++;
	}
	
	private static void merge2Files()
	{
		Scanner scan;
		PrintWriter print;
		StringBuilder sb;
		File output;
		File reading;
		int counter=0;
		location = 0;
		
		output = new File(fout+"/"+"printReady"+"/" +"page" + pageNum + ".txt");
		

		try {
			print = new PrintWriter(output);
			sb = new StringBuilder();
			while(location <2)
			{
				reading = new File(stringNames[location]);
				if(!reading.toString().equals(""))
				{
					
				

				scan = new Scanner(reading);
				String temp = scan.nextLine();
				while(true)
				{

					sb.append(temp +"\r\n");
					
					if(!scan.hasNextLine())
					{
						break;
					}
					temp = scan.nextLine();
					counter++;

					if(counter >24)
					{

						sb = new StringBuilder();
						scan.close();
						print.close();
						print = new PrintWriter(fout + "/" +"printReady/specialpage"+specialPage+".txt");
						specialPage++;
						scan = new Scanner(reading);
						int stop = location;
						switch(stop)
						{
						case 0:
							while(scan.hasNextLine())
							{
								sb.append(scan.nextLine()+"\r\n");
							}
							print.print(sb.toString());
							print.close();
							print = new PrintWriter(output);
							location =1;
							break;
							
						case 1:
							while(scan.hasNextLine())
							{
								sb.append(scan.nextLine()+"\r\n");
							}
							print.print(sb.toString());
							print.close();
							print = new PrintWriter(output);
							stringNames[0] = stringNames[1];
							stringNames[1]="";
							location = 0;
							break;
							
					
						}
						sb= new StringBuilder();
						counter = 0;
						break;
						
					}
				}
				scan.close();
				} else {
					break;
				}
				if(counter !=0)
				{
					for(int i = counter; i < 26; i++)
					{
						sb.append("\r\n");
					}
					if(location <1)
					{
						for(int i = 0; i<80; i++)
						{

							sb.append("-");
						}

						sb.append("\r\n");
						sb.append("\r\n");
					}
					

					counter =0;
					location++;
				}
			}
			print.print(sb.toString());

			
			print.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		stringNames[0] ="";
		stringNames[1]="";
		stringNames[2]="";
		location = 0;
		pageNum++;
	
	}
	
	private static void merge1File()
	{
		Scanner scan;
		PrintWriter print;
		StringBuilder sb;
		File output;
		File reading;
		int counter=0;
		location = 0;
		
		output = new File(fout+"/"+"printReady/page" + pageNum + ".txt");
		

		try {
			print = new PrintWriter(output);
			sb = new StringBuilder();
			
				reading = new File(stringNames[location]);
					
				
				scan = new Scanner(reading);
				String temp = scan.nextLine();
				while(true)
				{
					sb.append(temp +"\r\n");
					
					if(!scan.hasNextLine())
					{
						break;
					}
					temp = scan.nextLine();
					counter++;
					
					if(counter >48)
					{
						sb = new StringBuilder();
						scan.close();
						print.close();
						print = new PrintWriter(fout + "/" +"printReady/page" + pageNum + ".txt");
						pageNum++;
						scan = new Scanner(reading);
					}

						
				}
			print.print(sb.toString());

			
			print.close();
				
			
		} catch (FileNotFoundException e) {
			bar.setValue(90);
			JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel,  "File Error", "Improper File Type", 
					JOptionPane.ERROR_MESSAGE);
		}
		stringNames[0] ="";
		stringNames[1]="";
		stringNames[2]="";
		location = 0;
		pageNum++;
	
	
	}
	public void stop()
	{
		this.stop = true;

	}
	
	public int getTotalCount() {
		return count;
	}
	
	public int getSpecialOrderCount() {
		return specialPage;
	}
	
	public int getNormalPageTotal() {
		return pageNum;
	}

}

