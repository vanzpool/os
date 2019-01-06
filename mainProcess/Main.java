package nachos.mainProcess;

import nachos.machine.Machine;
import nachos.machine.NetworkLink;

public class Main {

	private Console console;
	private MyNetworkLink networkLink = new MyNetworkLink();
	
	public Main(){
		console = Console.getInstance();
		
		int dest;
		String message;
		
		console.writeLine("Your Address : " 
		+ networkLink.getAddress());
		
		do{
		console.writeLine("Timer : " + Machine.timer().getTime()/10000000f);
			
		console.writeLine("Input Destination Address : ");
		dest = console.readInt();
		
		console.writeLine("Dest : " + dest);
		
		console.writeLine("Input Message : ");
		message = console.readLine();
		
		networkLink.send(dest, message);
		
		}while(true);
		
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new Main();
//	}

}
