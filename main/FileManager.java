package nachos.main;


import nachos.machine.FileSystem;
import nachos.machine.Machine;
import nachos.machine.OpenFile;

public class FileManager {
	
	FileSystem file;

	public FileManager() {
		// TODO Auto-generated constructor stub
		file = Machine.stubFileSystem();
	}
	
	public void write(String filename, String message, boolean is_create)
	{
		OpenFile of = file.open(filename, is_create);
		of.write(message.getBytes(), 0, message.length());
		of.close();
	}
	
	public String read(String filename, boolean is_create)
	{
		try{
			OpenFile of = file.open(filename, is_create);
			
			byte[] bytes = new byte[1024];
			
			of.read(bytes, 0, bytes.length);
			of.close();
			
			return new String(bytes);
		}catch(Exception e){
			return "";
		}
	}
	
	
	
	

}
