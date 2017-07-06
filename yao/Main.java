package yao;

import yao.gate.AndGate;
import yao.gate.Gate;
import yao.gate.Wire;
import yao.gate.XorGate;

public class Main 
{
	public static void main(String [] args) throws Exception
	{
		new Main()._main();
	}
	
	void _main() throws Exception
	{
	
		
		Wire a1=new Wire();
		Wire a2=new Wire();
		Wire b1=new Wire();
		Wire b2=new Wire();
		Wire ra=new Wire();
		Wire rb=new Wire();
		Wire r=new Wire(3);
		
		Gate g1=new XorGate(a1,a2,ra);
		Gate g2=new AndGate(b1,b2,rb);
		Gate g3=new XorGate(ra,rb,r);
		
		
		byte[][] lut_g1=g1.getLut();
		byte[][] lut_g2=g2.getLut();
		byte[][] lut_g3=g3.getLut();
		byte[] in_a1=a1.getValue1();
		byte[] in_a2=a2.getValue0();
		byte[] in_b1=b1.getValue1();
		byte[] in_b2=b2.getValue1();
		
		
		
		Utils.printLut(g1, "g1");
		Utils.printLut(g2, "g2");
		Utils.printLut(g3, "g3");
		System.out.println("in_a1:"+Utils.getHex(in_a1));
		System.out.println("in_a2:"+Utils.getHex(in_a2));
		System.out.println("in_b1:"+Utils.getHex(in_b1));
		System.out.println("in_b2:"+Utils.getHex(in_b2));
		
		
		/* PHASE B: EVALUATE (on untrusted resource) 
		 * =========================================*/
		
		//notice that the evaluator instantiates untyped gates
		Gate gate1=new Gate(lut_g1);
		Gate gate2=new Gate(lut_g2);
		Gate gate3=new Gate(lut_g3);
				
		byte[] r1=gate1.operate(in_a2, in_a1);
		byte[] r2=gate2.operate(in_b2, in_b1);
		byte[] r3=gate3.operate(r2, r1);
		System.out.println("encrypted result="+Utils.getHex(r3));
		
		
		
		if(Utils.arraysAreEqual(r3,r.getValue0()))
			System.out.println("result is 0");
//		else if(Utils.arraysAreEqual(r3,r.getValue0()))
//			System.out.println("result is 0");
		else if(Utils.arraysAreEqual(r3,r.getValue1()))
			System.out.println("result is 1");
//		else if(Utils.arraysAreEqual(r3,r.getValue1()))
//			System.out.println("result is 1");
		else
			System.out.println("result is undefined");
		
//		KeyPair kp=Utils.genRSAkeypair();
//		
//		byte[] secret=new byte[]{0x12,0x34,0x56,0x78};
//		
//		byte[] cipher=Utils.RSAencrypt(secret, kp.getPublic());
//		
//		System.out.println("RSA encrypted="+Utils.getHex(cipher));
//		
//		secret=Utils.RSAdecrypt(cipher, kp.getPrivate());
//		
//		System.out.println("RSA decrypted="+Utils.getHex(secret));
//		
//		new Transfer().exchange();
		
		
	}
}
