package main;

public class Process implements Comparable<Process> {
	private String name;
	private int burst;
	private int arrival;
	private int priority;

	public Process(String name, int burst, int arrival, int priority) {
		super();
		this.name = name;
		this.burst = burst;
		this.arrival = arrival;
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Process [name=" + name + ", burst=" + burst + ", arrival=" + arrival + ", priority=" + priority + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBurst() {
		return burst;
	}

	public void setBurst(int burst) {
		this.burst = burst;
	}

	public int getArrival() {
		return arrival;
	}

	public void setArrival(int arrival) {
		this.arrival = arrival;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public int compareTo(Process st) {
		if (this.priority == st.priority)
			return 0;
		else if (this.priority > st.priority)
			return 1;
		else
			return -1;
	}

}
