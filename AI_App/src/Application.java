import java.util.*;
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AI_BaseClass objAI = new AI_BaseClass();
		//objAI.setDatabaseLenth();
		//objAI.getDatabaseLenth();
		//objAI.setDatabaseArray();
		//ArrayList<String> database = objAI.preLoadDatabase();
		//objAI.getDatabaseArray();
		
		//
		//objAI.Matching(objAI.Database.get(2), objAI.Database.get(4));
		
//		objAI.Matching("{I(x,y,z):F(x,y):P(x,y)}",
//				"{I(x,y,z):-K(B):-F(x,y)}");
//		String s = objAI.Resolvent("{I(x,y,z):F(x,y):-P(x,y):-D(A):T(x)}",
//				"{I(x,y,z):-K(B):-F(C,y):-P(x,y):V(x):D(B)}");
//		System.out.println("here is the string -> " + s);
		
		//objAI.getDatabaseArray();
		//objAI.getDatabaseArray();
		
		
		DeletionStrategy objDelStrategy = new DeletionStrategy();
		objDelStrategy.Deletion_Process();
		
		UnitStrategy objUnitStrategy = new UnitStrategy();
		objUnitStrategy.Unit_Process();
		
		InputStrategy objInpStrategy = new InputStrategy();
		objInpStrategy.Input_Process();
		
		//SetOfSupportStrategy objSOSStrategy = new SetOfSupportStrategy();
		//objSOSStrategy.SetOfSupport_Process();
		
		OrderedStrategy objOrdStrategy = new OrderedStrategy();
		objOrdStrategy.OrderedStrategy_Process();
		
//		DirectedStrategy objDirStrategy = new DirectedStrategy();
//		objDirStrategy.DirectedStrategy_Process();
	}

}
