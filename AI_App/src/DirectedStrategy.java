import java.util.ArrayList;


public class DirectedStrategy {
	AI_BaseClass objAI = new AI_BaseClass();
	ArrayList<String> database = new ArrayList<String>();
	//int GamaPointer;
	//ArrayList<Integer> GamaDerivative = new ArrayList<Integer>();
	public void DirectedStrategy_Process(){
		setDatabase();
		System.out.println(database);
		//GamaPointer = database.size() - 1;
		//System.out.println("GamaPointer: " + GamaPointer);
		//GamaDerivative.add(GamaPointer);
		startProcess();
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
//				if (IsGamaDerivative(firstPointer, secondPointer)) {
//					
//				}
				//System.out.println("Loop pointers-> " +firstPointer+ " >><< " + secondPointer);
				System.out.println("Loop inputs-> " +database.get(secondPointer)+ " >><< " + database.get(firstPointer));
				strResolvent = objAI.Resolvent(database.get(secondPointer), database.get(firstPointer), "O");
				System.out.println(strResolvent);
				if(!strResolvent.equals("")){
					database.add(strResolvent + " ="+secondPointer+"&"+firstPointer);
					//GamaDerivative.add(database.size() - 1);
				}
				if(strResolvent.equals("{}")){
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
		return databaseLen == firstPointer && databaseLen == secondPointer ? false : true;
	}
	
	public void setDatabase(){
		//Unit db
		database = new ArrayList<String>();
		database.add("{P(x):Q(x)}");
		database.add("{-P(x):R(x)}");
		database.add("{-Q(x):R(x)}");
		database.add("{-R(x)}");
		//System.out.println(database.size());
	}
	
	public void displayFinalDatabase(){
		System.out.println("Following sentences are in database: ");
		for (int i = 0; i < database.size(); i++){
			System.out.println(""+i+"."+ database.get(i));
		}
	}
	
//	public boolean IsGamaDerivative(int firstPointer, int secondPointer) {
//		return ((GamaDerivative.contains(firstPointer) || GamaDerivative.contains(secondPointer)) ? 
//				 true : false);
//	}
}
