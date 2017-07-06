
package circuit;


public class Main {
    public static void main(String [] args) throws Exception
	{
               //Full Adder Circuit
		 FullAdder f1= new FullAdder();
               
                //A builds Circuit for 2 integers: a, b
                f1.getCircuit();
                
                //B receives encrypted circuit and provides intermediate results: carrier bit and sum bit
                //3 inputs: a, b, c, c: carrier bit
                boolean FA_ab_result[]= f1.evaluate(true, false, true); 
               
                //A builds Circuit for 2 integers: Sj, d 
                f1.getCircuit();
                
                //B receives encrypted circuit and provides intermediate results: carrier bit and sum bit
                //3 inputs: Sj, d, c, c: carrier bit
                boolean FA_abc_result[]= f1.evaluate(FA_ab_result[1], false, true); //Sj + d
  
                System.out.println("");
                System.out.print("result of 3-integer Full Adder: ");
                System.out.println((FA_abc_result[1]) ? 1 : 0);
                
                System.out.println("");
                
                //Integer Equality - Socialist Millionaires Problem
                IntEquality f= new IntEquality();
                
                //A builds Circuit for 2 integers: a, b
                f.getCircuit();
                
                //B receives encrypted circuit and provides intermediate results: carrier bit
                //3 inputs: a, b, c, c: carrier bit
                boolean ab_result= f.evaluate(true, true, true); // a comp b
                
                //A builds Circuit for 2 integers: Cj, d 
                f.getCircuit();
                
                //B receives encrypted circuit and provides intermediate results: carrier bit
                //3 inputs: Cj, d, c, c: carrier bit
                boolean abc_result= f.evaluate(ab_result, false, true); //a comp b comp d
                
                
                System.out.println("");
                System.out.print("result of 3-integer equality: ");
                System.out.println((abc_result) ? 1 : 0);
                
                
                
               
	}
}
