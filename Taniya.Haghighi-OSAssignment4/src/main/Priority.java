package main;

import java.util.ArrayList;

public class Priority {
	private ArrayList<Process> processes = new ArrayList<>();

	public Priority(ArrayList<Process> processes) {
		super();
		this.processes = processes;
	}

	public void priorityAlgorithm() {
		int time = 0;
		int counter = 0;
		Process lastProcess = null;
		ArrayList<Integer> similarPriorities = new ArrayList<>();
		while (!processes.isEmpty()) {
			int index = findHighestPriority(time);

			similarPriorities = findSimilarPriorites(index, time);
			index = similarPriorities.get(0);
			Process process = processes.get(index);

			if (similarPriorities.size() > 1) {
				counter++;

			}
			process.setBurst(process.getBurst() - 1);
			if (process.getBurst() == 0) {
				processes.remove(index);
				counter = 0;
			}
			if (counter == 2) {
				Process p = processes.get(index);
				processes.remove(index);
				processes.add(p);
				counter = 0;
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

	public ArrayList<Integer> findSimilarPriorites(int index, int time) {
		ArrayList<Integer> indices = new ArrayList<>();
		int in = 0;
		int min = processes.get(index).getPriority();
		for (Process process : processes) {
			if (process.getPriority() == min && process.getArrival() <= time) {
				indices.add(in);
			}
			in++;
		}
		return indices;
	}

	public int findHighestPriority(int time) {
		int index = 0;
		int in = 0;
		int min = Integer.MAX_VALUE;
		for (Process process : processes) {
			if (process.getPriority() < min && process.getArrival() <= time) {
				min = process.getPriority();
				index = in;
			}
			in++;
		}
		return index;
	}
}
