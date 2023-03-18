package main;

import java.util.ArrayList;

public class FCFS {

	private ArrayList<Process> processes = new ArrayList<>();

	public FCFS(ArrayList<Process> processes) {
		super();
		this.processes = processes;
	}

	public void FCFSAlgorithm() {
		int time = 0;
		for (Process process : this.processes) {
			System.out.print("process " + process.getName() + " -> starts from " + time);
			time += process.getBurst();
			System.out.println(" to " + time);

		}
	}
}
