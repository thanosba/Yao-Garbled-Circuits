
package circuit;

import yao.Transfer;
import yao.Utils;
import yao.gate.AndGate;
import yao.gate.Gate;
import yao.gate.OrGate;
import yao.gate.Wire;
import yao.gate.XorGate;


public class FullAdder {
    
     byte[][] lut_g1, lut_g2, lut_g3, lut_g4, lut_g5;
     Wire a, b, c, r1, r2, r3, r4, r5;
     
     public void getCircuit() throws Exception {
        System.out.println("Generate Full Adder Circuit");

        a=new Wire();
	b=new Wire();
        c=new Wire();
        

        r1=new Wire();
        r2=new Wire();
        r3=new Wire();
        r4=new Wire();
        r5=new Wire();
        

        Gate g1=new XorGate(a,b,r1);
        Gate g2=new XorGate(r1,c,r2);
        Gate g3=new AndGate(a,b,r3);
        Gate g4=new AndGate(c,r1,r4);
        Gate g5=new OrGate(r3,r4,r5);

        

        lut_g1=g1.getLut();
        lut_g2=g2.getLut();
        lut_g3=g3.getLut();
        lut_g4=g4.getLut();
        lut_g5=g5.getLut();
        
        System.out.println("Full Adder Circuit has been generated");
     }
     
    public boolean[] evaluate(boolean a_input, boolean b_input, boolean c_input ) throws Exception {
        System.out.println("Evaluate Full Adder Circuit");
        
        byte[] in_a=(a_input?a.getValue1():a.getValue0());
        byte[] in_b=(b_input?b.getValue1():b.getValue0());
        byte[] in_c=(c_input?c.getValue1():c.getValue0());
        
    
        Gate gate1=new Gate(lut_g1);
        Gate gate2=new Gate(lut_g2);
        Gate gate3=new Gate(lut_g3);         
        Gate gate4=new Gate(lut_g4); 
        Gate gate5=new Gate(lut_g5);
       

        byte[] ret1=gate1.operate(in_b, in_a);
        byte[] ret2=gate2.operate(in_c, ret1);
        byte[] ret3=gate3.operate(in_b, in_a);
        byte[] ret4=gate4.operate(ret1, in_c);
        byte[] ret5=gate5.operate(ret4, ret3);
       
       
        boolean [] results= new boolean[2];
        
      
       if(Utils.arraysAreEqual(ret2,r2.getValue0()))
	    results[0]= false;
       else if(Utils.arraysAreEqual(ret2,r2.getValue1()))
	    results[0]= true;
       
 
        if(Utils.arraysAreEqual(ret5,r5.getValue0()))
            results[1]= false;
       else if(Utils.arraysAreEqual(ret5,r5.getValue1())) 
           results[0]= false;
        
        System.out.println("Full Adder Circuit has been evaluated");
         
        return results;
 
    }
    
    
    
    
}
