import java.util.*;

public class AI_BaseClass {
	//Global variables
	Scanner in = new Scanner(System.in);
	ArrayList<String> Database = new ArrayList<String>();
	int DatabaseLen = Database.size();
		
	public void setDatabaseLenth(){
		System.out.println("Enter the length of the database: ");
		DatabaseLen = in.nextInt();
	}
		
	public void getDatabaseLenth(){
		System.out.println("Database lenth: " + DatabaseLen);
	}
	
	public void setDatabaseArray(){
		System.out.println("Enter sentesnces into database: ");
		for (int i = 0; i < DatabaseLen; i++){
			System.out.println("Enter " + (i + 1) + " sentence: ");
			Database.add(in.next());
		}
	}
	
	public void getDatabaseArray(){
		System.out.println("Following sentesnces are in database: ");
		for (int i = 0; i < Database.size(); i++){
			System.out.println(""+i+"."+ Database.get(i));
		} 
	}
	
	public String getValueDatabase(int index){
		return Database.get(index);
	}
	
//	public ArrayList<String> preLoadDatabase(){
////		Database.add("{-I(r):O(r)}");
////		Database.add("{-I(r):-K(r)}");
////		Database.add("{-I(c):K(s)}");
////		Database.add("{-I(s):-K(s)}");
////		Database.add("{-I(t):-O(c)}");
////		Database.add("{-F(r,t):U(r,t)}");
////		Database.add("{-L(c,s):-U(c,s)}");
//		
////		//deletion db
////		Database.add("{P(x)}");
////		Database.add("{-P(x):-Q(x)}");
////		Database.add("{-Q(x):R(x)}");
////		Database.add("{-R(x)}");
//	
////		// new del db
////		Database.add("{P(x):Q(x)}");
////		Database.add("{-P(x):Q(x)}");
////		Database.add("{P(x):-Q(x)}");
////		Database.add("{-P(x):-Q(x)}");
//		
////		//Unit db
////		Database.add("{P(x):Q(x)}");
////		Database.add("{-P(x):R(x)}");
////		Database.add("{-Q(x):R(x)}");
////		Database.add("{-R(x)}");
//		
//		return Database;
//	}
	
	public String[] getTerm(String input){
		String[] retString;
		
		if(input.charAt(0) != input.charAt(input.lastIndexOf("}"))){
			input = input.substring(1, input.lastIndexOf("}"));
			retString = getString(input, ":"); 
		}
		else{
			System.out.print("Sentence not valid !!");
			retString = null;
		}
		return retString;
	}
	public String[] getString(String input, String exp){
		return input.split(exp);
	}
	
	public String Resolvent(String input1, String input2, String Strategy){
		ArrayList<String> retOutput = CheckBinding(input1, input2);
		if(!retOutput.isEmpty()){
			input1 = retOutput.get(0);
			input2 = retOutput.get(1);
		}
		String[] term1 = getTerm(input1);
		String[] term2 = getTerm(input2);
		String retInput = "";
		String strTerm1 = "";
		String strTerm2 = "";
		ArrayList<ArrayList<String>> retArrayList = new ArrayList<ArrayList<String>>();
		ArrayList<Boolean> retMatchFirstLastTerm = new ArrayList<Boolean>();
		retArrayList = MatchTerms(term1, term2, retArrayList);
		ArrayList<String> retComplimentaryTerm = retArrayList.get(0);
		ArrayList<String> retMatchingTerm = retArrayList.get(1);
		//System.out.println("hi:" + retComplimentaryTerm);
		if (!retArrayList.isEmpty() && !retComplimentaryTerm.isEmpty()) {
			System.out.println("here is the string -> ");
			//System.out.println(retComplimentaryTerm);
			for(int i = 0; i < term1.length; i++){
				if(!retComplimentaryTerm.contains(term1[i])) {
					strTerm1 = strTerm1 + term1[i] + ":";
				}
			}
			if(term1.length != 1 && strTerm1.contains(":")){
				strTerm1 = strTerm1.substring(0, strTerm1.lastIndexOf(":"));
			}
			for(int j = 0; j < term2.length; j++) {
				if (!retMatchingTerm.contains(term2[j]) && !retComplimentaryTerm.contains(term2[j])) {
					
					strTerm2 = strTerm2 + term2[j] + ":" ;
				}
			}
			if(term2.length != 1 && strTerm2.contains(":")){
				strTerm2 = strTerm2.substring(0, strTerm2.lastIndexOf(":"));
			}
			
			// Special cases
//			if (Strategy.equals("O")) {
//				
//			}
//			switch(Strategy) {
//				case "O": 
//					retMatchFirstLastTerm = MatchFirstLastTerm(term1, term2, retComplimentaryTerm);
//					if(!retMatchFirstLastTerm.isEmpty() && retMatchFirstLastTerm.get(0).equals(true)){
//						return retInput = ""; 
//					}
//					break;
//				default: break;
//			}
		}
		else {
			return retInput = "";
		}
		
		//System.out.println("here is the string -> " + input1 + "<>" + input2);
		//System.out.println("here is the string -> " + strTerm1 + "<>" + strTerm2);
		retInput = strTerm1.isEmpty() ? strTerm2.isEmpty() ? "{}" : ("{" + strTerm2 + "}") 
				: strTerm2.isEmpty() ? ("{" + strTerm1 + "}") : ("{" + strTerm1 + ":" + strTerm2 + "}");
		//retInput = strTerm2.isEmpty() ? "" : strTerm1;
		
		return retInput;
	}
	
	public ArrayList<ArrayList<String>> MatchTerms(String[] term1, String[] term2, ArrayList<ArrayList<String>> retArrayList){
		ArrayList<String> retComplimentaryTerm = new ArrayList<String>();
		ArrayList<String> retMatchingTerm = new ArrayList<String>();
		for(int i = 0; i < term1.length; i++){
			//boolean VarMat1 = false;
			for(int j = 0; j < term2.length; j++) {
				boolean VarMatched = false;
				//boolean VarMat2 = false;
				String str11 = term1[i].substring(0,1);
				String str21 = term2[j].substring(0,1);
				//System.out.println(str11 + " <><> " + str21);
				char strcase = str11.equals("-") ? (str21.equals("-") ? 'D' : 'B') 
						: (str21.equals("-") ? 'C' : 'A');
				
				String var1, var2;
				var1 = term1[i].substring(term1[i].indexOf('(') + 1, term1[i].indexOf(')'));
				var2 = term2[j].substring(term2[j].indexOf('(') + 1, term2[j].indexOf(')'));
				
				switch (strcase){
					case 'A': //System.out.println("A");
						if (str11.equals(str21)){
							//check variable
							VarMatched = checkVariables(var1, var2);
							//System.out.println("Function matches* !!"+ VarMatched);	
						}
						break;
					case 'B': //System.out.println("B");
						if (term1[i].substring(1, 2).equals(str21)){
							//check variable
							VarMatched = checkVariables(var1, var2);
							//System.out.println("Function matches* !!"+ VarMatched);
						}
						break;
					case 'C': //System.out.println("C");
						if (str11.equals(term2[j].substring(1, 2))){
							//check variable
							VarMatched = checkVariables(var1, var2);
							//System.out.println("Function matches* !!"+ VarMatched);
						}
						break;
					case 'D': //System.out.println("D");
						if (term1[i].substring(1, 2).equals(term2[j].substring(1, 2))){
							//check variable
							VarMatched = checkVariables(var1, var2);
							//System.out.println("Function matches* !!"+ VarMatched);
						}
						break;
					default: break;
				}
				if(VarMatched){
					if(term1[i].length() != term2[j].length()) {
						retComplimentaryTerm.add(term1[i]);
						retComplimentaryTerm.add(term2[j]);
						//System.out.println(term1[i] + " COM matches " + term2[j]);
					}
					else {
						retMatchingTerm.add(term1[i]);
						retMatchingTerm.add(term2[j]);
						//System.out.println(term1[i] + " matches " + term2[j]);
					}
					break;
				}
				else {
					//System.out.println(term1[i] + " -- NO match -- " + term2[j]);
				}
			}
		}
		// return value
		retArrayList.add(retComplimentaryTerm);
		retArrayList.add(retMatchingTerm);
		return retArrayList;
	}
	
	public void showTerm(String[] term){
		for(int i = 0; i < term.length; i++){
			System.out.println(i + "th term: " + term[i]);
		}
	}
	
	public boolean checkVariables(String var1, String var2){
		boolean bRet = false;
		String[] str1 = getString(var1, ",");
		String[] str2 = getString(var2, ",");
		
		for(int i = 0; i < str1.length; i++){
			if (str1.length ==  str2.length){
				//System.out.println(str1[i] + " : " + str2[i]);
				if(str1[i].equals(str2[i])){
					bRet = true;
					//System.out.println("Variable Matched !!");
				}
				else {
					//different variable or constant
//					if (str1[i].equals("x") || str2[i].equals("x")) {
//						bRet = true;
//						//System.out.println("str[i]:"+str1[i]+"str2[i]:"+str2[i]);
//						break;
//					}
					//System.out.println("str[i]:"+str1[i]+"str2[i]:"+str2[i]);
					//System.out.println("different variable or constant !!");
					bRet = false;
					break;
				}
			}
			else{
				//Different # of variables
				//System.out.println("Different # of variables !!");
			}
		}
		return bRet;
	}
	
	public ArrayList<Boolean> MatchFirstLastTerm(String[] term1, String[] term2, ArrayList<String> retComplimentaryTerm){
		ArrayList<Boolean> retBoolean = new ArrayList<Boolean>(); 
		if(term1[0].equals(retComplimentaryTerm.get(0)) && term2[0].equals(retComplimentaryTerm.get(1))) {
			// set ordered true
			retBoolean.add(true);
		}
//		if (){
//			
//		}
		return retBoolean;
	}
	
	public ArrayList<String> CheckBinding(String Input1, String Input2){
		ArrayList<String> retInput = new ArrayList<String>();
		char strcase = Input1.contains("x") ? (Input2.contains("x") ? 'D' : 'B') 
				: (Input2.contains("x") ? 'C' : 'A');
		
		switch (strcase){
			case 'A': //System.out.println("A");
				// do nothing
				break;
			case 'B': //System.out.println("B");
				String[] str1 = getTerm(Input1);
				boolean b1 = false;
				for(int i = 0; i < str1.length; i++){
					b1 = str1[i].contains("x") ? true : false;
				}
				if (!b1){
					String s1 = Input1.substring(Input1.indexOf('(') + 1,Input1.indexOf(')'));
					Input1 = Input1.replace("x", s1);
					System.out.println("!b1 = true:"+Input1);
				}
				else{
					String s1 = Input2.substring(Input2.indexOf('(') + 1,Input2.indexOf(')'));
					Input1 = Input1.replace("x", s1);
					System.out.println("!b1 = false:"+Input1);
				}
				break;
			case 'C': //System.out.println("C");
				String[] str2 = getTerm(Input2);
				boolean b2 = false;
				for(int i = 0; i < str2.length; i++){
					b2 = str2[i].contains("x") ? true : false;
				}
				if (!b2){
					String s2 = Input2.substring(Input2.indexOf('(') + 1,Input2.indexOf(')'));
					Input2 = Input2.replace("x", s2);
					System.out.println("s2:"+Input2);
				}
				else{
					String s2 = Input1.substring(Input1.indexOf('(') + 1,Input1.indexOf(')'));
					Input2 = Input2.replace("x", s2);
					System.out.println("s2:"+Input2);
				}
				break;
			case 'D': //System.out.println("D");
				// do more
				break;
			default: break;
		}

		retInput.add(Input1);
		retInput.add(Input2);
		return retInput;
	}
}
