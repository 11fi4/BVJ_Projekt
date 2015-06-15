import java.io.*; 
import com.justformspdf.pdf.*;

/** Fills out the form of a given PDF and saves it to a new file */
public class PDFFillForm {
	public enum FormType {Verweis, Nachsitzen, Mahnung};
	private final boolean debug = true;
	
	/** Params of the Verweis form */
	private String[] Params_Verweis = new String[]{
			"eltern_anschrift",
			"eltern_name",
			"schueler_name"
	};
	
	/** Params of the Nachsitzen form */
	private String[] Params_Nachsitzen = new String[]{
			"eltern_anschrift",
			"eltern_name",
			"schueler_name"
	};
	
	/** Params of the Mahnung form */
	private String[] Params_Mahnung = new String[]{
			"eltern_anschrift",
			"eltern_name",
			"schueler_name",
			"schueler_fehltage_sinceDate",
			"schueler_fehltage_entschuldigt",
			"schueler_fehltage_unentschuldigt",
			"schueler_attestpflicht_abDatum",
	};
	
	/** Testing */
	public static void main(String[] args){		
		String INPUT = "T:\\Name.pdf";
		String OUTPUT = "T:\\Name_filled.pdf";
		new PDFFillForm().CreateNewPDF(INPUT, OUTPUT);
	}

	/** Fills forms of a given PDF and saves it to a new file.
	 * Note: Input file must be a PDF 1.4 (set Acrobat DC 5.0 Compatibility)! */	
	public void CreateNewPDF(String input, String output){
		try{
			if(debug) System.out.println("Trying to fill PDF form...");
			
			// Open pdf
			FileInputStream fis = new FileInputStream(input);
			PDFReader reader = new PDFReader(fis);
			PDF pdf = new PDF(reader);
			pdf.lk("43B78HB6");
			Form form = pdf.getForm();
	
			// Fill PDF form
			FillForm(form, FormType.Verweis);
			
			// Render and Save
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

	/** Gets the parameters for a FormType */
	private String[] getParams(FormType formtype){
		switch(formtype){
		case Verweis:
			return Params_Verweis;
		case Mahnung:
			return Params_Mahnung;
		case Nachsitzen:
			return Params_Nachsitzen;
		} return null;
	}
	
	/** Fills a complete from depending on its FormType */
	private void FillForm(Form form, FormType formType) throws Exception{
		String[] params = getParams(formType);
		
		for(String param: params)
			FillFormField(form, param, "test");
	}
	
	/** Fills a single field of a form with a value. Skips it field is not found */
	private void FillFormField(Form form, String field, String value){
		try{
			((FormText) form.getElement(field)).setValue(value);
			if(debug) System.out.println("-> SUCCESS: set " + field + " to '" + value + "'");
		}catch(Exception e){
			if(debug) System.out.println("-> FAIL: set " + field + " to '" + value + "'");
			//e.printStackTrace();
		}	
	}
	
}
