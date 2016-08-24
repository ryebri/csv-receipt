package merchMadeEasier;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFCreate {
	//C:\cs227\workspace_Local\KKPsi-MerchStuff\2015MBSummerMerchNoTotals.txt
	private PDDocument document;
	private PDFont font;
	private String fout;
	private static final int LINE = 14;
	
	public PDFCreate(String fileOut){
		document = new PDDocument();
		font = PDType1Font.TIMES_ROMAN;
		fout = fileOut;
		
	}
	
	public void createDoc(int pageCountN, int pageCountS){
		PDPage page = new PDPage(PDPage.PAGE_SIZE_LETTER);
		File f = new File(fout +"/printReady/allForms.pdf");
		
		try {
			PDPageContentStream content;
			for(int i = 0; i < pageCountN; i++){
				File fin = new File(fout+"/"+"printReady"+"/" +"page" + i + ".txt");
			//	int count = 1;
				Scanner s = new Scanner(fin);
				s.useDelimiter("\r\n");
				if(0 == i){
					content = new PDPageContentStream(document, page);
				} else {
					document.addPage(page);
					page = new PDPage(PDPage.PAGE_SIZE_LETTER);
					content = new PDPageContentStream(document, page, true, true, true);
				}
				
				content.setFont(font, 12);
				content.beginText();
				content.moveTextPositionByAmount(100, 740);
				content.drawString(s.next());
				while(s.hasNext()){
					content.moveTextPositionByAmount(0, (-1)*LINE);
					content.drawString(s.next());
//					count++;
				}
				content.endText();
				content.close();
				s.close();
			}
			for(int i = 0; i < pageCountS; i++){
				File fin = new File(fout+"/"+"printReady"+"/" +"specialpage" + i + ".txt");
				//int count = 1;
				Scanner s = new Scanner(fin);
				s.useDelimiter("\r\n");
				if(0 == i && 0==pageCountN){
					content = new PDPageContentStream(document, page);
				} else {
					document.addPage(page);
					page = new PDPage(PDPage.PAGE_SIZE_LETTER);
					content = new PDPageContentStream(document, page, true, true, true);
				}
				
				content.setFont(font, 12);
				content.beginText();
				content.moveTextPositionByAmount(100, 725);
				content.drawString(s.next());
				while(s.hasNext()){
					content.moveTextPositionByAmount(0, (-1)*LINE);
					content.drawString(s.next());
//					count++;
				}
				content.endText();
				content.close();
				s.close();
			}

			document.addPage(page);
			try {
				document.save(f);
			} catch (COSVisitorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			document.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
