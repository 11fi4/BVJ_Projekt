import java.io.*; 
import com.justformspdf.pdf.*;
import java.util.Hashtable;
import java.util.Enumeration;


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
			"0",
			"1",
			"2",
			"3",
			"4",
			"5",
			"6",
			"7",
			"8",
			"9",
			"10",
			"11",
			"12",
			"13",
			"14",
			"15"
	};
	
	
	Hashtable Generate_Mahnung_Table(){
		Boolean gender = true;
		String brief_datum = "05.04.2014";
		String possesiv0 = gender? "ihre Tochter" : "ihr Sohn";
		String possesiv1 = gender? "ihrer Tochter" : "ihres Sohnses";
		String possesiv2 = gender? "unserer Tochter" : "unseres Sohnes";
		String anschrift = "Gudrun Müller"+"\\rMüllerstr. 7"+"\\r97080 Würzburg";
		String anrede = (gender? "geehrte Frau " : "geehrter Herr ") + "Müller";
		String name_schueler = "Tina";
		String fehltage_seit = "01.01.2014";
		String fehltage_gesamt = "19";
		String fehltage_unentschuldigt = "8";
		String attestpflicht_ab = "17.04.2014";
		
		Hashtable<Integer, String> ht = new Hashtable<Integer, String>(); 
	
		ht.put(0, anschrift); //0
		ht.put(1, brief_datum);
		ht.put(2, anrede + ",");
		ht.put(3, possesiv1 + " " + name_schueler);
		ht.put(4, fehltage_seit);
		ht.put(5, fehltage_gesamt);
		ht.put(6, fehltage_unentschuldigt);
		ht.put(7, possesiv1);
		ht.put(8, possesiv0);
		ht.put(9, attestpflicht_ab);
		ht.put(10, possesiv1);
		ht.put(11, attestpflicht_ab);
		ht.put(12, brief_datum);
		ht.put(13, possesiv2 + " " + name_schueler);
		ht.put(14, fehltage_seit);
		ht.put(15, attestpflicht_ab);
		
		return ht;
	}
	
	/** Testing */
	public static void main(String[] args){		
		String INPUT = "T:\\Repo_Vorlage_Mahnung.pdf";
		String OUTPUT = "T:\\Mahnung_filled.pdf";
		new PDFFillForm().CreateNewPDF(INPUT, OUTPUT);
	}

	/** Fills forms of a given PDF and saves it to a new file.
	 * Note: Input file must be a PDF 1.4 (set Acrobat DC 5.0 Compatibility)! */	
	public void CreateNewPDF(String input, String output){
		try{
			if(debug) System.out.println("Trying to fill PDF form");
			
			// Open pdf
			FileInputStream fis = new FileInputStream(input);
			PDFReader reader = new PDFReader(fis);
			PDF pdf = new PDF(reader);
			pdf.lk("43B78HB6");
			Form form = pdf.getForm();
	
			// Fill PDF form
			FillForm(form, FormType.Mahnung);
			
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
		
		
		if(debug) System.out.println("Type: " + formType);

		Hashtable ht = Generate_Mahnung_Table();
		Enumeration e = ht.keys();
		while(e.hasMoreElements()){
			int key = (int)e.nextElement();
			FillFormField(form, ""+key, ht.get(key).toString());
		}
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
