import java.util.ArrayList;


public class OrderedStrategy {
	AI_BaseClass objAI = new AI_BaseClass();
	ArrayList<String> database = new ArrayList<String>();
	//int GamaPointer;
	//ArrayList<Integer> GamaDerivative = new ArrayList<Integer>();
	public void OrderedStrategy_Process(){
		setDatabase();
		System.out.println(database);
		startProcess();
		System.out.println("OrderedStrategy");
		displayFinalDatabase();
	}
	
	public void startProcess() {
		int firstPointer = 0;
		int secondPointer = 0;
		boolean stopLoop = false;
		//System.out.println("Loop pointers-> " +firstPointer+ " >><< " + secondPointer);
		while(endLoop(firstPointer,secondPointer, database.size())){
			//System.out.println("outer Loop -> " +firstPointer+ " >><< " + secondPointer);
			while(firstPointer == secondPointer ? false : true){
				String strResolvent = "";
				//System.out.println(database.size());
				//System.out.println("Loop pointers-> " +firstPointer+ " >><< " + secondPointer);
				//System.out.println("Loop inputs-> " +database.get(secondPointer)+ " >><< " + database.get(firstPointer));
				
				if (IsOrdered(firstPointer, secondPointer)) {
					//System.out.println("Loop pointers-> " +firstPointer+ " >><< " + secondPointer);
					//System.out.println("Loop inputs-> " +database.get(secondPointer)+ " >><< " + database.get(firstPointer));
					strResolvent = objAI.Resolvent(database.get(secondPointer), database.get(firstPointer), "O");
					//System.out.println(strResolvent);
					if(!strResolvent.equals("")){
						database.add(strResolvent + " ="+secondPointer+"&"+firstPointer);
						//GamaDerivative.add(database.size() - 1);
					}
					if((strResolvent.contains("{A(") && strResolvent.length() == 6)){
						stopLoop = true;
						break;
					}
					else{
						secondPointer++;
					}
				}
			}
			if(stopLoop){
				break;
			}
			else{
				// pointer increment
				firstPointer++;
				secondPointer = 0;
			}
		}
		
	}
	
	public boolean endLoop(int firstPointer, int secondPointer, int databaseLen) {
		return (((database.size() - 1 == firstPointer) && database.size() - 1 >= secondPointer)) ? false : true;
	}
	
	public void setDatabase(){
		//Unit db
		database = new ArrayList<String>();
//		database.add("{P(x):Q(x)}");
//		database.add("{-P(x):R(x)}");
//		database.add("{-Q(x):R(x)}");
//		database.add("{-R(x)}");
//		//System.out.println(database.size());
		
		database.add("{-I(r):-K(r)}");
		database.add("{-I(r):-T(r)}");
		database.add("{-I(c):K(s)}");
		database.add("{-I(s):-K(s)}");
		database.add("{-I(t):K(c)}");
		
		database.add("{L(r,t)}");
		//database.add("{-L(x,y):T(x)}");
		database.add("{T(r)}");
		database.add("{T(t)}");
		
		database.add("{-C(c,s), -F(c,s)}");
		
		//database.add("{I(r):I(c)}");
		//database.add("{I(r):I(s)}");
		//database.add("{I(r):I(t)}");
		//database.add("{I(c):I(s)}");
		//database.add("{I(c):I(t)}");
		//database.add("{I(s):I(t)}");
		
		database.add("{-T(x):-K(x):-I(x)}");

		database.add("{I(x):A(x)}");
	}
	
	public void displayFinalDatabase(){
		System.out.println("Following sentences are in database: ");
		for (int i = 0; i < database.size(); i++){
			System.out.println(""+i+"."+ database.get(i));
		}
	}
	
	public boolean IsOrdered(int firstPointer, int secondPointer) {
		boolean b = true;
		String[] str1 = objAI.getTerm(database.get(firstPointer));
		String[] str2 = objAI.getTerm(database.get(secondPointer));
		String var_str1 = str1[0].substring(str1[0].indexOf('(') - 1,str1[0].indexOf('('));
		String var_str2 = str2[0].substring(str2[0].indexOf('(') - 1,str2[0].indexOf('('));
		//System.out.println("var_str1: "+ var_str1+" var_str2: "+var_str2);
		//b = var_str1.equals(var_str2) ? true : false;
		return b;
	}
}
