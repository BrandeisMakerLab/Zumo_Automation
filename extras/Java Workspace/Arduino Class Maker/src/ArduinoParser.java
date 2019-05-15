/** Name: Jacob Smith
 *  Email:jsmith2021@brandeis.edu
 *  Assignment:  Personal Study, provides methods relevent to parsing an Arduino Class
 *  	This is a seperate class to keep classes short and to allow client to access there server methods for input validation
 *  Date: May 15, 2019
 *  Sources: 
 *  Bugs:
 */

public class ArduinoParser {
	
	/**Inserts tabs into a string separated by newline characters*/
	public static String insertTabs(String base, int numTabs){
		MiniScanner reader=new MiniScanner();
		reader.prime(base,"\n");
		String withTabs="";
		String line;
		while(reader.hasNext()){
			line=reader.next();
			withTabs+=genTab(numTabs)+line+"\n";
		}
		return withTabs;
		
	}
	/** Generates a tab of the specified depth
	 * */
	private static String genTab(int numTabs){
		String tab="";
		for(int i=1;i<=numTabs;i++){
			tab+="    ";
		}
		return tab;
	}
}
