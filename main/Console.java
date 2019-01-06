package nachos.main;

import nachos.machine.Machine;
import nachos.machine.SerialConsole;
import nachos.threads.Semaphore;

public class Console {

	private static Console console = new Console();
	private Semaphore semaphore;
	private SerialConsole serialConsole;
	private String temp_message;
	
	private Console() { 
		semaphore = new Semaphore(0);	
		serialConsole = Machine.console();
		
		Runnable recv = new Runnable() {
			public void run() {			
				char Character = (char)serialConsole.readByte();
				if(Character == '\n')
					semaphore.V();
				else
					temp_message += Character;
			}
		};
		Runnable send = new Runnable() {			
			@Override
			public void run() {				
				semaphore.V(); 
			}
		};
		
		serialConsole.setInterruptHandlers(recv, send);
	}
	
	public void write(String message)
	{
		for(char c : message.toCharArray())
		{
			serialConsole.writeByte(c);	
			semaphore.P(); 
		}
	}
	
	public void writeln(String message)
	{
		write(message+"\n");
	}
	
	public String readLine()
	{
		temp_message = "";
		semaphore.P();
		
		return temp_message;
	}
	
	public int readInt()
	{
		return Integer.parseInt(readLine());
	}
	
	public static Console getInstance()
	{
		return console;
	}

}
