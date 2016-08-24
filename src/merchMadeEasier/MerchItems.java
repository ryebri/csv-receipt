package merchMadeEasier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MerchItems {

	
	private ArrayList<String> headers;
	public MerchItems(File in, String delim) throws FileNotFoundException
	{
		headers = new ArrayList<String>();

			Scanner scan = new Scanner(in);
			Scanner line = new Scanner(scan.nextLine());
			line.useDelimiter(delim);
			while(line.hasNext())
			{
				headers.add(line.next());
			}
			line.close();
			scan.close();


		
	}
	public ArrayList<String> getArrayList()
	{
		return headers;
	}
}
