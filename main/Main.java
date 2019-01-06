package nachos.main;

import java.util.Vector;

import nachos.threads.KThread;

public class Main {
	
	Console console = Console.getInstance();
	FileManager fileManager = new FileManager();

	public Main() {
		// TODO Auto-generated constructor stub
		console.writeln("test");
		int input;
		Vector<Task> tasks = new Vector<>();
		String temp = "";
		
		//read file
		temp = fileManager.read("task.txt", false);
		if(temp != "")
		{
			String[] items = temp.split("\n");
			
			for(int i=0; i<items.length-1; i++)
			{
				String[] attribute = items[i].split("#");
				tasks.add(new Task(attribute[0], Integer.parseInt(attribute[1])));
			}
		}
		
		do{
			for(Task t: tasks)
			{
				console.writeln(t.getName());
				console.writeln(t.getDuration() + "");
				console.writeln("");
			}
			console.writeln("1. Add task");
			console.writeln("2. Process task");
			console.writeln("3. Save & exit");
			console.write(">> ");
			input = console.readInt();
			
			switch(input) {
				
			case 1:
				console.write("Input task name: ");
				String name = console.readLine();
				console.write("Input task duration: ");
				int duration = console.readInt();
				
				tasks.add(new Task(name, duration));
				break;
				
			case 2:
				while(!tasks.isEmpty())
					new KThread(tasks.remove(0)).fork();
				
				break;
				
			case 3:
				
				if(tasks.isEmpty())
					temp = "";
				else
				{
					for(Task t: tasks)
					{
						temp += t.getName() + "#" + t.getDuration() + "\n";
					}
				}
				
				fileManager.write("task.txt", temp, true);
				break;
			}
			
		}while(input != 3);
		
	}
}
