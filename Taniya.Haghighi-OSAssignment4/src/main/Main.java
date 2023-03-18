package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static ArrayList<Process> processes = new ArrayList<>();

	public static void main(String[] args) {
		readFromFile("process_info");
		printProcesses();
		printMenu();
	}

	public static void printMenu() {
		System.out.println("choose proper algorithm:\n1-FCFS\n2-SJF\n3-Priority preementive");
		Scanner myInput = new Scanner(System.in);
		int a = myInput.nextInt();
		switch (a) {
		case 1:
			FCFS fsfc = new FCFS(processes);
			fsfc.FCFSAlgorithm();
			break;
		case 2:
			System.out.println("non preemptive:");
			SJF sjf = new SJF(processes);
			sjf.SJFAlgorithmNonPreemptive();
			System.out.println("preemptive:");
			sjf.SJFAlgorithmPreemptive();
			break;
		case 3:
			Priority priority = new Priority(processes);
			priority.priorityAlgorithm();
			break;
		default:
			System.out.println("you can only choose one of the algorithms above...");
		}
	}

	public static void printProcesses() {
		for (Process process : processes) {
			System.out.println(process);
		}
	}

	public static void readFromFile(String fileName) {
		// File path is passed as parameter
		File file = new File(fileName + ".txt");

		// Note: Double backquote is to avoid compiler
		// interpret words
		// like \test as \t (ie. as a escape sequence)

		// Creating an object of BufferedReader class
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(file));
			// Declaring a string variable
			String st;

			// Condition holds true till
			// there is character in a string
			while ((st = br.readLine()) != null) {
				String[] s = st.split(",");
				Process newProcess = new Process(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]),
						Integer.parseInt(s[3]));
				processes.add(newProcess);
				// Print the string
				// System.out.println(st);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
