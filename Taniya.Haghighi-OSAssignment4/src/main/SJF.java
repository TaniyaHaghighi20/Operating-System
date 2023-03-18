package main;

import java.util.ArrayList;

public class SJF {

	private ArrayList<Process> processes = new ArrayList<>();

	public SJF(ArrayList<Process> processes) {
		super();
		this.processes = processes;
	}

	public void SJFAlgorithmNonPreemptive() {
		int time = 0;
		ArrayList<Process> pcopy = copyArrayList(processes);
		while (!pcopy.isEmpty()) {
			int index = findShortestJob(time, pcopy);
			Process process = pcopy.get(index);
			System.out.print("process " + process.getName() + " -> starts from " + time);
			time += process.getBurst();
			System.out.println(" to " + time);
			process.setBurst(0);
			if (process.getBurst() == 0) {
				pcopy.remove(index);
			}
		}

	}

	private ArrayList<Process> copyArrayList(ArrayList<Process> processes2) {
		ArrayList<Process> pcopy = new ArrayList<>();
		for (Process process : processes) {
			Process p = new Process(process.getName(), process.getBurst(), process.getArrival(), process.getPriority());
			pcopy.add(p);
		}
		return pcopy;
	}

	public void SJFAlgorithmPreemptive() {
		int time = 0;
		Process lastProcess = null;
		while (!processes.isEmpty()) {
			int index = findShortestJob(time, processes);
			Process process = processes.get(index);
			process.setBurst(process.getBurst() - 1);
			if (process.getBurst() == 0) {
				processes.remove(index);
			}
			if (lastProcess != process) {
				if (lastProcess != null) {
					System.out.println(" to " + time);
				}
				lastProcess = process;
				System.out.print("process " + process.getName() + " -> starts from " + time);
			}
			time++;

		}
		System.out.println(" to " + time);

	}

	public int findShortestJob(int time, ArrayList<Process> pArray) {
		int index = 0;
		int in = 0;
		int min = Integer.MAX_VALUE;
		for (Process process : pArray) {
			if (process.getBurst() < min && process.getArrival() <= time) {
				min = process.getBurst();
				index = in;
			}
			in++;
		}
		return index;
	}

}
