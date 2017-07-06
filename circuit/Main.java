
package circuit;


public class Main {
    public static void main(String [] args) throws Exception
	{
           
		 FullAdder f1= new FullAdder();
               
           
                f1.getCircuit();
                
                
                boolean FA_ab_result[]= f1.evaluate(true, false, true); 
               
                
                f1.getCircuit();
                
                
                boolean FA_abc_result[]= f1.evaluate(FA_ab_result[1], false, true); 
  
                System.out.println("");
                System.out.print("result of 3-integer Full Adder: ");
                System.out.println((FA_abc_result[1]) ? 1 : 0);
                
                System.out.println("");
                
                
                IntEquality f= new IntEquality();
                
                
                f.getCircuit();
                
                
                boolean ab_result= f.evaluate(true, true, true); 
                
                
                f.getCircuit();
                
               
                boolean abc_result= f.evaluate(ab_result, false, true); 
                
                
                System.out.println("");
                System.out.print("result of 3-integer equality: ");
                System.out.println((abc_result) ? 1 : 0);
                
                
                
               
	}
}
