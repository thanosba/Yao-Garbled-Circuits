
package circuit;

import yao.Utils;
import yao.gate.AndGate;
import yao.gate.Gate;
import yao.gate.OrGate;
import yao.gate.Wire;
import yao.gate.XorGate;


public class IntEquality {
    
     byte[][] lut_g1, lut_g2, lut_g3, lut_g4, lut_g5;
     Wire a, b, c, r1, r2, r3, r4, r5;
     
     public void getCircuit() throws Exception {
        System.out.println("Generate Integer Equality Circuit");
        
        //3-Wire inputs a, b, c
        a=new Wire();
	b=new Wire();
        c=new Wire();
        
        //5-Wire outputs 
        r1=new Wire();
        r2=new Wire();
        r3=new Wire();
        r4=new Wire();
        r5=new Wire();
        
        //Gates of the Yao Garbled Circuit
        Gate g1=new AndGate(a,b,r1); // A AND B
        Gate g2=new AndGate(b,c,r2); //B AND C
        Gate g3=new AndGate(a,c,r3); //A AND C
        Gate g4=new AndGate(r1,r2, r4); // G1 AND G2
        Gate g5=new AndGate(r3,r4, r5); // G3 AND G4
       
     
        //encrypted output from the Gates
        lut_g1=g1.getLut();
        lut_g2=g2.getLut();
        lut_g3=g3.getLut();
        lut_g4=g4.getLut();
        lut_g5=g5.getLut();
        
        System.out.println("Integer Equality Circuit has been generated");
     }
     
     
    public boolean evaluate(boolean a_input, boolean b_input, boolean c_input ) throws Exception {
        System.out.println("Evaluate Integer Equality Circuit");
        byte[] in_a=(a_input?a.getValue1():a.getValue0());
        byte[] in_b=(b_input?b.getValue1():b.getValue0());
        byte[] in_c=(c_input?c.getValue1():c.getValue0());
        
        //Gates in B's side using A's encrypted output
        Gate gate1=new Gate(lut_g1);
        Gate gate2=new Gate(lut_g2);
        Gate gate3=new Gate(lut_g3);         
        Gate gate4=new Gate(lut_g4); 
        Gate gate5=new Gate(lut_g5);
       
        //applies circuit with test input values
        byte[] ret1=gate1.operate(in_b, in_a);
        byte[] ret2=gate2.operate(in_c, in_b);
        byte[] ret3=gate3.operate(in_c, in_a);
        byte[] ret4=gate4.operate(ret2, ret1);
        byte[] ret5=gate5.operate(ret4, ret3);
       
         System.out.println("Integer Equality Circuit has been evaluated");
         
         //returns Carrier bit (Cj): true for 1, false for 0
       if(Utils.arraysAreEqual(ret5,r5.getValue0())) 
            return false;
       else if(Utils.arraysAreEqual(ret5,r5.getValue1()))
            return true;
       
      
       return false;     
    }
}
