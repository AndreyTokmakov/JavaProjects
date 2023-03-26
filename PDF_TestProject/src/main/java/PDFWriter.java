import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;  

/**
 * @author a.tokmakov
 *
 */
public class PDFWriter {

	public static void main(String[] args) throws Exception {
		PDFWriter pdfWriter = new PDFWriter();

		pdfWriter.AddEmptyDocument();
		//pdfWriter.AddPages();
		//pdfWriter.AddText();
		//pdfWriter.EditDocumntInfo();
		
		// pdfWriter.AddImage();
	}

        public void AddEmptyDocument() throws IOException {
                  /** Creating PDF document object **/ 
              PDDocument document = new PDDocument();     

              /** Add an empty page to it  **/ 
              document.addPage(new PDPage()); 

              /** Saving the document  **/ 
              document.save("C:\\Temp\\Pdf\\BlankPdf1.pdf"); 
              System.out.println("PDF created");  

              /** Closing the document   **/ 
              document.close(); 
        }

        public void AddPages() throws IOException {

                //Creating PDF document object 
                PDDocument document = new PDDocument(); 

                File file = new File("C:\\Temp\\Pdf\\BlankPdf.pdf"); 
                PDDocument.load(file);

                for (int i=0; i<10; i++){ 
                        //Creating a blank page 
                        PDPage blankPage = new PDPage(); 

                        //Adding the blank page to the document 
                        document.addPage(blankPage); 
                } 

                //Saving the document 
                document.save("C:\\Temp\\Pdf\\BlankPdf_OP.pdf"); 
                System.out.println("PDF created");  

                //Closing the document  
                document.close(); 
        }  
        
    public void EditDocumntInfo() throws IOException {     

    	/** Creating PDF document object **/ 
    	PDDocument document = new PDDocument();     

    	PDPage page = new PDPage();	
    	/** Add an empty page to it  **/ 
    	document.addPage(page);
    	
    	PDDocumentInformation documentInformation = document.getDocumentInformation();
    	documentInformation.setAuthor("Tutorialspoint");
    	documentInformation.setTitle("Sample document"); 
    	documentInformation.setCreator("PDF Examples"); 
    	documentInformation.setSubject("Example document"); 
    	
    	/** Setting the created date of the document  **/ 
    	Calendar date = new GregorianCalendar();
    	date.set(2015, 11, 5); 
    	documentInformation.setCreationDate(date);
    		
    	/** Setting the modified date of the document  **/ 
    	date.set(2016, 6, 5); 
    	documentInformation.setModificationDate(date); 
    	       
    	/** Setting keywords for the document  **/ 
    	documentInformation.setKeywords("sample, first example, my pdf"); 

    	/** Saving the document  **/ 
    	document.save(new File("C:\\Temp\\Pdf\\AddText_OP1.pdf")); 

    	/** Closing the document  **/ 
    	document.close();  
    } 


	public void AddText() throws IOException {     
    	PDDocument document = new PDDocument();     
    	PDPage page = new PDPage();	
    	
			       
		PDPageContentStream contentStream = new PDPageContentStream(document, page);
		//Begin the Content stream 
		contentStream.beginText();      
		//Setting the font to the Content stream  
		contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
		//Setting the position for the line 
		contentStream.newLineAtOffset(25, 500);
		String text = "[This is the sample document and we are adding content to it]";
		//Adding text in the form of string 
		contentStream.showText(text);      
		//Ending the content stream
		contentStream.endText();
		//Closing the content stream
		contentStream.close();
		
		document.addPage(page);

		
		
		//Creating a PDF Document
    	PDPage page2 = new PDPage();	
    	PDPageContentStream contentStream2 = new PDPageContentStream(document, page2); 
	       
    	//Begin the Content stream 
    	contentStream2.beginText(); 
    	//Setting the font to the Content stream
    	contentStream2.setFont( PDType1Font.TIMES_ROMAN, 16 );
    	//Setting the leading
    	contentStream2.setLeading(14.5f);
    	//Setting the position for the line
    	contentStream2.newLineAtOffset(25, 725);

    	String text1 = "This is an example of adding text to a page in the pdf document.we can add as many lines";
    	String text2 = "as we want like this using the ShowText()  method of the ContentStream class";

    	//Adding text in the form of string
    	contentStream2.showText(text1);
    	contentStream2.newLine();
    	contentStream2.showText(text2);
    	//Ending the content stream
    	contentStream2.endText();
    	//Closing the content stream
    	contentStream2.close();
    	
		document.addPage(page2);
		
		//Saving the document
		document.save(new File("C:\\Temp\\Pdf\\Document_WithText.pdf"));
		//Closing the document
		document.close();
	}
	

	public void AddImage() throws Exception {
    	PDDocument document = new PDDocument();     
    	PDPage page = new PDPage();	
	       
		//Creating PDImageXObject object
		PDImageXObject pdImage = PDImageXObject.createFromFile("C:\\Temp\\Pdf\\VisualElements_70.png", document);
	    //creating the PDPageContentStream object
	    PDPageContentStream contents = new PDPageContentStream(document, page);
		//Drawing the image in the PDF document
		contents.drawImage(pdImage, 126, 126);
		//Closing the PDPageContentStream object
	    contents.close();	
	    
	    document.addPage(page);
	    
	    
	    //Saving the document
	    document.save("C:\\Temp\\Pdf\\Document_WithImage1.pdf");     
	    //Closing the document
	    document.close();   
	 }
}
