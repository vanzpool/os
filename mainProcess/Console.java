package nachos.mainProcess;

import nachos.machine.Machine;
import nachos.machine.SerialConsole;
import nachos.threads.Semaphore;

public class Console {

	private Semaphore semaphore;
	private SerialConsole serialConsole;
	private static Console console = new Console();
	private String temp_msg;
	
	public Console(){
		semaphore = new Semaphore(0);
		serialConsole = Machine.console();
		
		Runnable recv = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				char c = (char) serialConsole.readByte();
				if(c != '\n'){
					temp_msg += c;
				}
				else{
					semaphore.V();
				}
			}
		};
		
		Runnable send = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				semaphore.V();
			}
		};
		
		serialConsole.setInterruptHandlers(recv, send);
		
	}
	
	public void write(String message){
		for(char c : message.toCharArray()){
			serialConsole.writeByte(c);
			semaphore.P();
		}
	}
	
	public void writeLine(String message){
		write(message + '\n');
	}
	
	public static Console getInstance(){
		return console;
	}
	
	public String readLine(){
		temp_msg = "";
		semaphore.P();
		return temp_msg;
	}
	
	public int readInt(){
		return Integer.parseInt(readLine());
	}
	
}
