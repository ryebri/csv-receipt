package merchMadeEasier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainStuff {
	
	private static ArrayList<String> columns;
	private static String[] stringNames;
	private static int location;
	private static int pageNum;
	private static File file;
	public static void main(String[] args)
	{
		columns = new ArrayList<String>();
		stringNames = new String[3];
		pageNum = 0;
		location = 0;
		file = new File("2015MBSummerMerchNoTotals.txt");
		readInTest();
//		readInTest2();
		readIn3();
		
	}
	/**
	 * Used to write in the columns
	 */
	public static void readInTest()
	{
//		File file = new File("2015MBSummerMerchNoTotals.txt");
		PrintWriter writer;
//		Scanner scan;
		Scanner words;
		try {
//			scan = new Scanner(file);
//			System.out.println(scan.nextLine());
//			words = new Scanner(System.in);	
//			words = new Scanner(scan.toString());
			words = new Scanner(file);
			words.useDelimiter("\\t");
			writer = new PrintWriter("Columns.txt");
//			System.out.println(words.next().toString());
			words.next();
//			words.next(); //These 4 need to be not commented out for readInTest2() to work
//			words.next();
//			words.next();
//			writer.println("Name");
			String temp = words.next();
			while(words.hasNext())
			{
				if(temp.contains("STOP"))
				{
					break;
				}
				writer.println(temp);
				temp = words.next();
				
			}
			writer.close();
			words.close();
			words = new Scanner(new File("columns.txt"));
			String temp2 = words.nextLine();
			while(words.hasNextLine())
			{
				columns.add(temp2);
				temp2 = words.nextLine();
				
			}
//			System.out.println(columns.toString());
//			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void readInTest2()
	{
//		File file = new File("2015MBSummerMerchNoTotals.txt");
		File indvOrd;
		PrintWriter writer;
		Scanner scan;
		Scanner words;
		
		try{
			scan = new Scanner(file);
			scan.nextLine();
			
//			for(int i =0; i <16; i++)
//			{
//				scan.nextLine();
//			}
			
			for(int i = 0; i< 3; i++)
			{
				words = new Scanner(scan.nextLine());
				words.useDelimiter("\\t");
				words.next();
				String fname = words.next();
				String lname = words.next();
				indvOrd = new File(fname + lname + ".txt");
				writer = new PrintWriter(indvOrd);
				writer.println(fname + " " + lname);
				words.next();
				
				while(words.hasNext())
				{
					String temp = words.next();
					if(temp.equals(""))
					{
						temp = "0";
					}
					writer.println(temp);
				}
				writer.close();
				addColumnNames(indvOrd);
			}
			
//			file = new File("ThomasMayer.txt");
//			words = new Scanner(file);
//			System.out.println(words.nextLine());
//			if(words.nextLine().equals(" "))
//			{
//				System.out.println("No Section");
//			}
			
			scan.close();
		}catch (FileNotFoundException er) {
			er.printStackTrace();
		}
	}
	
	public static void addColumnNames(File fileName)
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
//				sb.append(scan.nextLine());
				scan.nextLine();
				count++;
			}
			check = new Scanner(fileName);
//			check.next();
//			check.next();
//			check.next();
			String temp = check.nextLine();
			for(int i = 0; i<columns.size(); i++)
			{
				if(!temp.equals(""))
				{
					output.append(columns.get(i) + ":   " + temp + "\n");
				}
				
				if(check.hasNextLine()==false)
				{
					break;
				}
				temp = check.nextLine();
			}
			scan.close();
			
			
			
//			System.out.println(output.toString());
			
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
			
			//For use with the merge3 function
//			stringNames[location] = fileName.toString();
//				location++;
//			if(location >2)
//			{
//				location = 0;
//				merge3Files();
//			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static void merge3Files()
	{
		Scanner scan;
		PrintWriter print;
		File output;
		File reading;
		int counter=0;
		location = 0;
		
		output = new File("page" + pageNum + ".txt");
		

		try {
			print = new PrintWriter(output);
			
			while(location <3)
			{
				reading = new File(stringNames[location]);
				scan = new Scanner(reading);
				String temp = scan.nextLine();
				while(true)
				{
					print.println(temp);
					
					if(!scan.hasNextLine())
					{
						break;
					}
					temp = scan.nextLine();
					counter++;
					if(counter >14)
					{
						System.out.println(stringNames[location]);
					}
				}
				for(int i = counter; i < 16; i++)
				{
					print.println();
				}
				if(location <2)
				{
					for(int i = 0; i<80; i++)
					{
						print.print("-");
					}
					print.println();
					print.println();
				}
				
				scan.close();
				counter =0;
				location++;
			}
		

			
			print.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		location = 0;
		pageNum++;
	}
	
	public static void readIn3()
	{
//		File file = new File("2015MBSummerMerchNoTotals.txt");
		Scanner scanLine;
		Scanner scanWords;
		ArrayList<String>info;
		Items stuff;
		int count=0;
		try {
			scanLine = new Scanner(file);
			scanLine.nextLine();
			while(true)
			{
				scanWords = new Scanner(scanLine.nextLine());
				scanWords.useDelimiter("\\t");
				scanWords.next();
				info = new ArrayList<String>();
				String temp;
				//for testing purposes
//				for(int i = count; i < 165; i++)
//				{
//					scanLine.nextLine();
//					count++;
//				}
//				
				
					while(scanWords.hasNext())
					{
						temp = scanWords.next();
						info.add(temp);
						
						
					}
//					TODO
				stuff = new Items(info, "");
				stuff.createTextFile();
//				System.out.println(stringNames);
				stringNames[location] = stuff.getFileName().toString();
				addColumnNames(stuff.getFileName());
				location++;
				if(location >2)
				{
					location = 0;
					merge3Files();
				}
				scanWords.close();
				
				if(!scanLine.hasNext())
				{
					break;
				}
				count++;
			}
			
			
			scanLine.close();
//			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
