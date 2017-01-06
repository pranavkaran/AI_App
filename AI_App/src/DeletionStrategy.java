import java.util.ArrayList;


public class DeletionStrategy {

	AI_BaseClass objAI = new AI_BaseClass();
	ArrayList<String> database = new ArrayList<String>();
	public void FindPureLiteral(){
		
	}
	
	public boolean GetPureLiteral(String input1, String input2){
		boolean bRet = false;
		
		return bRet;
	}
	
	public void Deletion_Process(){
		FindPureLiteral();
		setDatabase();
		startProcess();
		System.out.println("DeletionStrategy");
		displayFinalDatabase();
	}
	
	public void startProcess() {
		int firstPointer = 0;
		int secondPointer = 0;
		boolean stopLoop = false;
		//System.out.println("Loop pointers-> " +firstPointer+ " >><< " + secondPointer);
		while(endLoop(firstPointer,secondPointer, database.size())) {
			//System.out.println("outer Loop -> " +firstPointer+ " >><< " + secondPointer);
			while(firstPointer == secondPointer ? false : true){
				//System.out.println("Loop pointers-> " +firstPointer+ " >><< " + secondPointer);
				//System.out.println("Loop inputs-> " +database.get(firstPointer)+ " >><< " + database.get(secondPointer));
				String strResolvent = objAI.Resolvent(database.get(firstPointer), database.get(secondPointer), "");
				//System.out.println(strResolvent);
				if(!strResolvent.equals("")){
					database.add(strResolvent + " ="+firstPointer+"&"+secondPointer);
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
			}
		}
		
	}
	
	public boolean endLoop(int firstPointer, int secondPointer, int databaseLen) {
		return (databaseLen == firstPointer && databaseLen == secondPointer || databaseLen == firstPointer) ? false : true;
	}
	
	public void setDatabase(){
		// del db
//		database.add("{P(x):Q(x)}");
//		database.add("{-P(x):Q(x)}");
//		database.add("{P(x):-Q(x)}");
//		database.add("{-P(x):-Q(x)}");
		
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
}
