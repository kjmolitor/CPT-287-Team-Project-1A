package project_1a;

import java.util.Scanner;

public class Program {
	public static void menu() {
		System.out.println("1. Display movies");
		System.out.println("2. Add movies");
		System.out.println("3. Edit release dates");
		System.out.println("4. Edit movie description");
		System.out.println("5. Start showing movies in the theater");
		System.out.println("6. Number of movie before a date");
		System.out.println("7. Save");
		System.out.println("8. Exit");
		System.out.print("Selection: ");
	}
	
	public static int backToMenu(Scanner scanner, int option) {
		System.out.println("1. Continue\t2. Exit");
		System.out.println("");
		if(scanner.nextInt() == 1) {
			return option;
		}else {
			System.out.println("Exited!!");
			return 8;
			}
		
	}
	
	public static void display(){
		int option = 0;
		Scanner scanner = new Scanner(System.in);
		while(option != 8) {
			menu();
			switch(option = scanner.nextInt()){
			case 1:
				System.out.println("\nPICKED: 1\n");
				option = backToMenu(scanner, option);
				break;
			case 2:
				System.out.println("\nPICKED: 2\n");
				option = backToMenu(scanner, option);
				break;
			case 3:
				System.out.println("\nPICKED: 3\n");
				option = backToMenu(scanner, option);
				break;
			case 4:
				System.out.println("\nPICKED: 4\n");
				option = backToMenu(scanner, option);
				break;
			case 5:
				System.out.println("\nPICKED: 5\n");
				option = backToMenu(scanner, option);
				break;
			case 6:
				System.out.println("\nPICKED: 6\n");
				option = backToMenu(scanner, option);
				break;
			case 7:
				System.out.println("\nSaved!!\n");
				option = backToMenu(scanner, option);
				break;
			case 8:
				System.out.println("\nSelection: 8");
				break;
			}
		}
	}
	public static void main(String[] args) {
		int option = 0;
		display();
		
		
	} 
}
