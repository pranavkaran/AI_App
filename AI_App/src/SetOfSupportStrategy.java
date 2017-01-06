import java.util.ArrayList;


public class SetOfSupportStrategy {
	AI_BaseClass objAI = new AI_BaseClass();
	ArrayList<String> database = new ArrayList<String>();
	int GamaPointer;
	ArrayList<Integer> GamaDerivative = new ArrayList<Integer>();
	public void SetOfSupport_Process(){
		setDatabase();
		System.out.println(database);
		GamaPointer = database.size() - 1;
		//System.out.println("GamaPointer: " + GamaPointer);
		GamaDerivative.add(GamaPointer);
		startProcess();
		System.out.println("SetOfSupportStrategy");
		displayFinalDatabase();
	}
	
	public void startProcess() {
		int firstPointer = GamaPointer;
		int secondPointer = 0;
		boolean stopLoop = false;
		//System.out.println("Loop pointers-> " +firstPointer+ " >><< " + secondPointer);
		while(endLoop(firstPointer,secondPointer, database.size())){
			//System.out.println("outer Loop -> " +firstPointer+ " >><< " + secondPointer);
			while(firstPointer == secondPointer ? false : true){
				String strResolvent = "";
				if (IsGamaDerivative(firstPointer, secondPointer)) {
					//System.out.println("Loop pointers-> " +firstPointer+ " >><< " + secondPointer);
					//System.out.println("Loop inputs-> " +database.get(secondPointer)+ " >><< " + database.get(firstPointer));
					strResolvent = objAI.Resolvent(database.get(secondPointer), database.get(firstPointer), "");
					//System.out.println(strResolvent);
					if(!strResolvent.equals("")){
						database.add(strResolvent + " ="+secondPointer+"&"+firstPointer);
						GamaDerivative.add(database.size() - 1);
					}
				}
				if((strResolvent.contains("{A(") && strResolvent.length() == 6)){
					stopLoop = true;
					break;
				}
				else{
					secondPointer++;
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
		return (databaseLen == firstPointer && databaseLen >= secondPointer || databaseLen == firstPointer) ? false : true;
	}
	
	public void setDatabase(){
		//Unit db
		database = new ArrayList<String>();
		//database.add("{P(x):Q(x)}");
		//database.add("{-P(x):R(x)}");
		//database.add("{-Q(x):R(x)}");
		//database.add("{-R(x)}");
		//System.out.println(database.size());
		
		database.add("{-I(r):-K(r)}");
		database.add("{-I(r):-T(r)}");
		database.add("{-I(c):K(s)}");
		database.add("{-I(s):-K(s)}");
		database.add("{-I(t):K(c)}");
		
		database.add("{L(r,t)}");
		database.add("{T(r)}");
		database.add("{T(t)}");
		
		database.add("{-C(c,s), -F(c,s)}");
		
		database.add("{I(r):I(c)}");
		database.add("{I(r):I(s)}");
		database.add("{I(r):I(t)}");
		database.add("{I(c):I(s)}");
		database.add("{I(c):I(t)}");
		database.add("{I(s):I(t)}");
		
		//database.add("{-T(x):-K(x):-I(x)}");
		database.add("{-T(t):-K(t):-I(t)}");
		database.add("{-T(s):-K(s):-I(s)}");
		database.add("{-T(r):-K(r):-I(r)}");
		database.add("{-T(c):-K(c):-I(c)}");
		//database.add("{I(x):A(x)}");
		database.add("{I(t)}");
		database.add("{I(s)}");
		database.add("{I(r)}");
		database.add("{I(c)");
	}
	
	public void displayFinalDatabase(){
		System.out.println("Following sentences are in database: ");
		for (int i = 0; i < database.size(); i++){
			System.out.println(""+i+"."+ database.get(i));
		}
	}
	
	public boolean IsGamaDerivative(int firstPointer, int secondPointer) {
		return ((GamaDerivative.contains(firstPointer) || GamaDerivative.contains(secondPointer)) ? 
				 true : false);
	}
}
