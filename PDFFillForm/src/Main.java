/*
 * Fills out the form of a PDF and saves it
 */
import java.io.*; 

import com.justformspdf.pdf.*;

public class Main {
	final boolean debug = true;
	
	public enum FormType {Verweis, Nachsitzen, Mahnung};
	
	
	public static void main(String[] args){		
		String INPUT = "T:\\Name.pdf";
		String OUTPUT = "T:\\Name_filled.pdf";
		new Main().CreateNewPDF(INPUT, OUTPUT);
	}
	
	
	String[] Params_Verweis = new String[]{
			"eltern_anschrift",
			"eltern_name",
			"schueler_name"
	};
	
	String[] Params_Nachsitzen = new String[]{
			"eltern_anschrift",
			"eltern_name",
			"schueler_name"
	};
	
	String[] Params_Mahnung = new String[]{
			"eltern_anschrift",
			"eltern_name",
			"schueler_name"
	};
	

	String[] getParams(FormType formtype){
		switch(formtype){
		case Verweis:
			return Params_Verweis;
		case Mahnung:
			return Params_Mahnung;
		case Nachsitzen:
			return Params_Nachsitzen;
		} return null;
	}
	
	
	/* Fills forms of a given PDF and saves it to a new file.
	 * Note: Input file must be a PDF 1.4 (set Acrobat DC 5.0 Compatibility)! */	
	public void CreateNewPDF(String input, String output){
		try{
			if(debug) System.out.println("Trying to fill PDF form...");
			
			// Retrieve original PDF
			FileInputStream fis = new FileInputStream(input);
			PDFReader reader = new PDFReader(fis);
			PDF pdf = new PDF(reader);
			pdf.lk("43B78HB6");
			Form form = pdf.getForm();
	
			// Fill PDF form
			FillForm(form, FormType.Verweis);
			
			// Render and save as new PDF
			pdf.render();		
			FileOutputStream fos = new FileOutputStream(output);
			pdf.writeTo(fos);
			fos.close();
			fis.close();
			
			if(debug) System.out.println("Output: '" + output + "'");	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	void FillForm(Form form, FormType formType) throws Exception{
		String[] params = getParams(formType);
		
		for(String param: params){
			FillFormField(form, param, param);
		}
	}
	
	void FillFormField(Form form, String identifier, String value) throws Exception{
		if(debug) System.out.println("-> " + identifier + " set to '" + value + "'");	
		((FormText) form.getElement(identifier)).setValue(value);
	}
	
}
