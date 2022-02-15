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
	
	public static void display() throws FileNotFoundException{
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
				FileOutputStream outputFile = new FileOutputStream("input.txt");
				PrintWriter writer = new PrintWriter(outputFile);
				writer.print(coming.toString());
				writer.print(showing.toString());
				writer.close();
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
	public static void addMovie() { 

		//first we'll create a scanner
		Scanner movieInput = new Scanner(System.in);
		
		//The user will be asked to set the name of the new movie
		System.out.println("Please set name of Movie: ");
		String movieName = movieInput.nextLine();
		System.out.println();
		
		//the user will then be prompted to set the description
		System.out.println("Please set description of movie: ");
		String movieDesc = movieInput.nextLine();
		System.out.println();
		
		//the user will then set the date
		System.out.println("Please set release year: ");
		int releaseYear = movieInput.nextInt();
		System.out.println();
		
		//if the release year exceeds 9999 then the movie is invalid
		if (releaseYear > 9999) {
			
			System.out.println("Year Invalid, please set valid year: ");
			releaseYear = movieInput.nextInt();
			System.out.println();
			
		}//end if
		
		System.out.println("Please set release month: ");
		int releaseMonth = movieInput.nextInt();
		System.out.println();
		
		//if the release month exceeds 12 than it will be invalid
		if (releaseMonth > 12) {
			
			System.out.println("Month Invalid, please set valid month: ");
			releaseMonth = movieInput.nextInt();
			System.out.println();
			
		}//end if
		
		System.out.println("Please set release day: ");
		int releaseDay = movieInput.nextInt();
		System.out.println();
		
		//if the releaseDay exceeds 31 days the date will be invalid
		if (releaseDay > 31) {
			
			System.out.println("Day Invalid, please set valid day: ");
			releaseDay = movieInput.nextInt();
			System.out.println();
			
		}//end if
		
		//a new date is created with the user's inputs
		Date movieRelease = new Date(releaseYear, releaseMonth, releaseDay);
		
		//we will repeat the steps to get the recieve year
		System.out.println("Please set receive year: ");
		int receiveYear = movieInput.nextInt();
		System.out.println();
		
		if (receiveYear > 9999) {
			
			System.out.println("Year Invalid, please set valid year: ");
			receiveYear = movieInput.nextInt();
			System.out.println();
			
		}//end if
		
		System.out.println("Please set receive month: ");
		int receiveMonth = movieInput.nextInt();
		System.out.println();
		
		if (receiveMonth > 12) {
			
			System.out.println("Month Invalid, please set valid month: ");
			receiveMonth = movieInput.nextInt();
			System.out.println();
			
		}//end if
		
		System.out.println("Please set receive day: ");
		int receiveDay = movieInput.nextInt();
		System.out.println();
		
		if (receiveDay > 31) {
			
			System.out.println("Day Invalid, please set valid day: ");
			receiveDay = movieInput.nextInt();
			System.out.println();
			
		}//end if
		
		//a new date is created for the receive year
		Date movieReceive = new Date(receiveYear, receiveMonth, receiveDay);
		
		//finally the user will be asked to set the status of the movie
		System.out.println("Please set status of Movie: ");
		String movieStatus = movieInput.nextLine();
		System.out.println();//Note: for some reason the movie status lines seem to get skipped over
		
		//the movie class is created
		Movie newMovie = new Movie(movieRelease, movieName, movieDesc, movieReceive, movieStatus);//NOTE: the formatting on the dates is incorrect

		//moviesComing.add(newMovie); i'm not sure why this wasn't working
		
		
	}//end addMovie
}
