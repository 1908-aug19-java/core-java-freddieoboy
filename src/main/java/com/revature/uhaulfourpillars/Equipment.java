package com.revature.uhaul.fourpillars;

import java.util.Scanner;

public class Equipment {
	
	private boolean ud = false;
	private boolean fd = false;
	private boolean po = false;

	public Equipment() {
		super();
	}

	public void Rent() {
		Scanner s = new Scanner(System.in);

		System.out.println("(Would you like to rent a Utility Dolly?"); 
		System.out.print("Type y/n: ");

		String input;

		do {
			input = s.nextLine();
		} while (input != "y" || input != "n");
		
		if (input == "y")
			ud = true;
		
		////////////////////
		System.out.println("(Would you like to rent a Furniture Dolly?"); 
		System.out.print("Type y/n: ");

		do {
			input = s.nextLine();
		} while (input != "y" || input != "n");
		
		if (input == "y")
			fd = true;
		//////////////////////////
		System.out.println("(Would you like to rent Padded Blankets?"); 
		System.out.print("Type y/n: ");

		do {
			input = s.nextLine();
		} while (input != "y" || input != "n");
		
		if (input == "y")
			po = true;

		s.close();
	}

	public boolean rentUD() {
		return ud;
	}

	public boolean rentFD() {
		return fd;
	}

	public boolean rentPO() {
		return po;
	}
}
