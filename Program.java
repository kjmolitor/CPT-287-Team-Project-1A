package project_1a;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Program {
	
	static Movie_List showing = new Movie_List();
	static Movie_List coming = new Movie_List();
	
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
			case 1://Display movies currently in list
				System.out.println("\nPICKED: 1\n");
				System.out.println("--------Showing--------");
				System.out.println(showing.toString());
				System.out.println("--------Coming--------");
				System.out.println(coming.toString());
				option = backToMenu(scanner, option);
				break;
			case 2://User add movies to list
				System.out.println("\nPICKED: 2\n");
				option = backToMenu(scanner, option);
				break;
			case 3://User edits movies release dates
				System.out.println("\nPICKED: 3\n");
				option = backToMenu(scanner, option);
				break;
			case 4://User edits movie description
				System.out.println("\nPICKED: 4\n");
				option = backToMenu(scanner, option);
				break;
			case 5://Start showing movies
				System.out.println("\nPICKED: 5\n");
				option = backToMenu(scanner, option);
				break;
			case 6://Show number of movies before a date
				System.out.println("\nPICKED: 6\n");
				option = backToMenu(scanner, option);
				break;
			case 7://Save changes
				System.out.println("\nSaved!!\n");
				option = backToMenu(scanner, option);
				break;
			case 8://Exit
				System.out.println("\nSelection: 8");
				break;
			}
		}
	}
	
	//this function will convert the string to date variable. from stackoverflow
	public static Date stringToDate(String s,SimpleDateFormat dateFormat ){

	    Date result = null;
	    try{
	        result  = dateFormat.parse(s);
	    }

	    catch(ParseException e){
	        e.printStackTrace();
	    }
	    return result ;
	}
	
	public static void main(String[] args) throws IOException{
		//display();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // this will get the input in dd/MM/yyyy form
		FileInputStream inputFile = new FileInputStream("input.txt");
		Scanner scanner = new Scanner(inputFile);
		
		
		while(scanner.hasNext()) {//Read all data in file
			String[] input = scanner.nextLine().split(", ");
			Date releaseDate = stringToDate(input[1].replace("(ReleaseDate)", ""), dateFormat);
			Date receiveDate = stringToDate(input[3].replace("(ReceiveDate)", ""), dateFormat);
			String name = input[0];
			String description = input[2];
			String status = input[4];
			
			Movie movie = new Movie(name, releaseDate, description, receiveDate,status );
			if(input[4].equals("released")) {
				showing.add(movie);
			}
			if(input[4].equals("received")) {
				coming.add(movie);
			}
		}
		scanner.close();
		inputFile.close();
	} 
}
