package nachos.main;

public class Task implements Runnable{
	
	private String name;
	private int duration;

	public Task() {
		// TODO Auto-generated constructor stub
	}

	public Task(String name, int duration) {
		super();
		this.name = name;
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Task: " + name);
	}
}
