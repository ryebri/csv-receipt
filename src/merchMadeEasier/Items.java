package merchMadeEasier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Items {

	private static ArrayList<String> info;
	private static File f;
	private static String folder;

	public Items(ArrayList<String> list, String outLoca)
	{
		info = list;
		folder = outLoca;
	}
	
	public void createTextFile()
	{
		f = new File(folder+"/" +info.get(0)+info.get(1)+".txt");
		
		PrintWriter writeInitial;
		try {
			writeInitial = new PrintWriter(f);
			for(int i = 0; i<info.size(); i++)
			{
				writeInitial.println(info.get(i));
			}
			
			writeInitial.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
	}
	
	public File getFileName()
	{
		return f;
	}
}
