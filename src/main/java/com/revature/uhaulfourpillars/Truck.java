package com.revature.uhaul.fourpillars;

import java.util.Scanner;

public class Truck extends Item{

	private String truck;

	public Truck() {
		super();
	}

	public String getTruck() {
		return truck;
	}

	public void Rent() {
		Scanner s = new Scanner(System.in);
		
		do {
			
			System.out.println("What size truck would you like to rent?");
			System.out.println("(Type TM for 10', DC for 15', EL for 17', TT for 20', JH for 26')");
			String input = s.nextLine();
			
			switch (input) {
			
			case "TM":
				truck = "TM3873";
				break;
			case "DC":
				truck = "DC4897";
				break;
			case "EL":
				truck = "EL3879";
				break;
			case "TT":
				truck = "TT2765";
				break;
			case "JH":
				truck = "JH9032";
				break;
			default:
				truck = "None";
				System.out.println("Invalid entry.\n");
				break;
			}
			
		} while (truck == "None");
		
		s.close();
	}

}
