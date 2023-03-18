package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static int[] frames;
	public static ArrayList<Integer> refrence_pages = new ArrayList<>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readFromFile("page_references");

		printMenu();

	}

	private static void printMenu() {
		System.out.println("choose desired algorithm \n1-FIFO\n2-LRU");
		Scanner myInput = new Scanner(System.in);
		int a = myInput.nextInt();
		System.out.println("enter number of frames");
		int n = myInput.nextInt();
		frames = new int[n];
		switch (a) {
		case 1:
			fifoAlgorithm();
			break;
		case 2:
			lruAlgorithm();
		}
	}

	private static void lruAlgorithm() {
		HashMap<Integer, Integer> pages_with_lru = new HashMap<>();
		int empty = 0;
		int val = 0;
		int replaced_index = 0;
		boolean page_fault = false;
		int page_replaced = 0;
		int[] lowest_lru = new int[2];
		for (Integer page : refrence_pages) {
			page_fault = false;
			page_replaced = -1;
			replaced_index = -1;
			if (!contains(frames, page)) {
				if (empty < frames.length) {
					frames[empty] = page;
					replaced_index = empty;
					empty++;
					page_fault = true;
				} else {
					lowest_lru = find_lowest_lru(pages_with_lru);
					replaced_index = replace_page(lowest_lru[0], page);
					pages_with_lru.remove(lowest_lru[0]);
					page_replaced = lowest_lru[0];
					page_fault = true;
				}
			}
			pages_with_lru.put(page, val);
			val++;
			System.out
					.print("New page: " + page + "– Replaced page: " + ((page_replaced != -1) ? page_replaced : "none")
							+ " –" + " Replaced frame: " + ((replaced_index != -1) ? replaced_index : "none")
							+ " – Page fault: " + page_fault + " – New frame pages:");
			print_frame(frames);
			System.out.println();
		}
	}

	private static int replace_page(int prev_page, Integer page) {
		for (int j = 0; j < frames.length; j++) {
			if (frames[j] == prev_page) {
				frames[j] = page;
				return j;
			}
		}
		return -1;
	}

	private static int[] find_lowest_lru(HashMap<Integer, Integer> pages_with_lru) {
		int min = Integer.MAX_VALUE;
		int[] pair = new int[2];
		for (HashMap.Entry page : pages_with_lru.entrySet()) {
			int key = (int) page.getKey();
			if (contains(frames, key)) {
				int val = (int) page.getValue();
				if (val < min) {
					min = val;
					pair[0] = key;

					pair[1] = val;
				}
			}
		}
		return pair;
	}

	private static void fifoAlgorithm() {
		int index = 0;
		int frame_size = frames.length;
		boolean page_fault = false;
		int page_replaced = -1;
		int last_index = 0;
		for (Integer page : refrence_pages) {
			page_fault = false;

			if (!contains(frames, page)) {
				if (frames[index] != 0) {
					page_replaced = frames[index];
				}
				frames[index] = page;
				last_index = index;
				index++;
				index = index % frame_size;
				page_fault = true;

			} else {
				page_replaced = -1;
				last_index = -1;
			}
			System.out
					.print("New page: " + page + "– Replaced page: " + ((page_replaced != -1) ? page_replaced : "none")
							+ " –" + " Replaced frame: " + ((last_index != -1) ? last_index : "none")
							+ " – Page fault: " + page_fault + " – New frame pages:");
			print_frame(frames);
			System.out.println();
		}

	}

	private static void print_frame(int[] frames2) {
		System.out.print("[");
		for (int i = 0; i < frames2.length; i++) {
			System.out.print(frames2[i]);
			if (i != frames2.length - 1) {
				System.out.print(",");
			}
		}
		System.out.print("]");
	}

	private static boolean contains(int[] frames2, Integer page) {
		for (int i : frames2) {
			if (i == page) {
				return true;
			}
		}
		return false;
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
				for (int i = 0; i < s.length; i++) {
					refrence_pages.add(Integer.parseInt(s[i]));
				}

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
