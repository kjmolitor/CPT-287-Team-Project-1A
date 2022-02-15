package project_1a;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Program {
	static Movie_List showing = new Movie_List();
	static Movie_List coming = new Movie_List();
	static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	static Scanner movieInput = new Scanner(System.in);;
	
	public static void main(String[] args) throws IOException {
		//
		
		// this will get the input in dd/MM/yyyy form
		FileInputStream inputFile = new FileInputStream("input.txt");
		Scanner scanner = new Scanner(inputFile);
		
		 
		while(scanner.hasNext()) {//Read all data in file
			String[] input = scanner.nextLine().split(", ");
			
			String name = input[0].trim();
			Date releaseDate = stringToDate(input[1].replace("(ReleaseDate)", ""));
			String description = input[2].trim();
			Date receiveDate = stringToDate(input[3].replace("(ReceiveDate)", ""));
			String status = input[4].trim();
			
			
			Movie movie = new Movie(name, releaseDate, description, receiveDate, status );
			
			if(status.equals("released")) {
				if(!showing.containsName(name)) {
					showing.add(movie);
				}
			}
			else if(status.equals("received")) {
				if(!coming.containsName(name)) {
				coming.add(movie);
				}
			}
		}
		System.out.println(coming.toFile());
		
		//display();
		movieInput.close();
		scanner.close();
		inputFile.close();
		
	} 

	// Methods
	
	/**
	 * separate the actions of options of the menu
	 */
	public static void printDash() {
		System.out.println("-----------------------------------------------------------------");
	}
	
	
	/**
	 * The menu of the program
	 */
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
	
	/**
	 * This function will keep looping the menu until user choose to exit out
	 * @param scanner
	 * @param option
	 * @return
	 */
	public static int backToMenu(Scanner scanner, int option) {
		System.out.println("1. Continue\t2. Exit");
		if(scanner.nextInt() == 1) {
			return option;
		}else {
			System.out.println("Exiting...");
			FileOutputStream outputFile = new FileOutputStream("input.txt");
			PrintWriter writer = new PrintWriter(outputFile);
			writer.print(coming.toFile());
			writer.print(showing.toFile());
			movieInput.close();
			writer.close();
			return 8;
		}
	}
	
	
	/**
	 * Capitalize the string of description that has one or more than one movie description
	 * @param str: the description of the movie
	 * @return: the first-letter capitalized description of the movie
	 */
	public static String capitalize(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		String result = "";
		String[] arr = str.split("/");
		for(int i = 0; i < arr.length; i++) {
			result += arr[i].substring(0,1).toUpperCase() + arr[i].substring(1);
			if((i+1) < arr.length) {
				result += "/";
			}
		}
		return result;
	}
	
	
	/**
	 * Update the release date of the movie in the coming list if user chooses option 3
	 * Check if name is in the coming list
	 * Check if date is valid
	 */
	public static void editRelease() {
		
		//The user will be asked to set the name of the new movie
		System.out.print("Please tell the name of the movie that needs to change release day: ");
		String movieName = movieInput.nextLine();
		if( coming.containsName(movieName)) {
			Date newDate = getDate("release date");
			if(coming.updateRelease(movieName, newDate)) {
				System.out.println("Update success");
			}else {
				System.out.println("Update fail, please check the date");
			}
		}else { System.out.println("The movie name is not in our movie list, please check the name again "+ movieName);}
	}
	
	
	/**
	 * Update the description of the movie in the coming list if user chooses option 4
	 * Check if the name is valid
	 * Capitalize the first letter of the description 
	 */
	public static void editDescription() {
		//The user will be asked to set the name of the movie
		System.out.print("Please set the name of the movie that needs to change description: ");
		String movieName = movieInput.nextLine();
		System.out.print("Please set the name of the movie that needs to change description: ");
		String movieDescription = movieInput.next();
		
		if(coming.updateDescription(movieName, capitalize(movieDescription))) {
			System.out.println("Update success");
		}else {
			System.out.println("Update fail");
		}
	}
	
	/**
	 * Show specified release date if it has in the coming list if user chooses option 5
	 * Check if the release date is already in the showing list
	 * Check if the release date does not exist in both lists
	 */
	public static void showRelease() {
		Date displayDate = getDate("release date");
		if(showing.hasReleaseDate(displayDate)) {
			System.out.println("The release date is already in the showing list");
		}else if(coming.hasReleaseDate(displayDate)) {
			showing = new Movie_List(coming.displayReleaseDate(displayDate, showing));
		}else {
			System.out.println("The release date is not in the upcoming movie list and in theater movie list");
		}
	}

	
	/**
	 * Display how many movies there are before the specified date, and display every movie before that date.
	 */
	public static void showBeforeDate() {
		Date displayDate = getDate("date");
		Movie_List movieBeforeDate = new Movie_List(coming.displayBeforeDate(displayDate));
		System.out.println("There is/are "+ movieBeforeDate.size() + ": ");
		System.out.print(movieBeforeDate.toString());
		
	}
	
	
	/**
	 * Check if the user date is valid. If it is not, it will keep looping until get the valid date
	 * @param month: month from user menu
	 * @param day: day from user menu
	 * @param year: year from user menu
	 * @return: the corrected date from user menu.
	 */
	public static Date checkDate(int day, int month, int year) {
		if (month > 12) {
			System.out.print("Month Invalid, please set valid month: ");
			month = movieInput.nextInt();
			System.out.println();
			return checkDate(day,month,year);
		}
		if (day > 31 || (month == 2 && day > 28)) {
				System.out.println("Day Invalid, please set valid day: ");
				day = movieInput.nextInt();
				System.out.println();
				return checkDate(day,month,year);
		}
		if (year > 9999 || year < 1700) {
			System.out.println("Year Invalid, please set valid year: ");
			year = movieInput.nextInt();
			System.out.println();
			return checkDate(day,month,year);
		}
		return stringToDate( day + "/"+ month + "/" +year);
	}
	
	
	/**
	 * Get the date of the movie from the user/menu 
	 * @param type: the type of the date. It's either "release date" or "receive date", or it's just "date"
	 * @return: the date from user menu
	 */
	public static Date getDate(String type) {
		
		int day,month,year;
		System.out.printf("Please set the day of the %s: ",type);
		day = movieInput.nextInt();
		System.out.printf("Please set the month of the %s: ",type);
		month = movieInput.nextInt();
		System.out.printf("Please set the year of the %s: ",type);
		year = movieInput.nextInt();

		Date date= checkDate(day,month,year);;
		
		return date;
	}
	
	
	/**
	 * The function will display the menu to the console and get user option until user chooses to exit the program
	 * @throws FileNotFoundException: if the program can't get the input file or overwrite the input file
	 */
	public static void display() throws FileNotFoundException{
		int option = 0;
		Scanner scanner = new Scanner(System.in);
		while(option != 8) {
			menu();
			switch(option = scanner.nextInt()){
			case 1://Display movies currently in list
				System.out.println("--------Showing--------");
				System.out.println(showing.toString());
				System.out.println("--------Coming--------");
				System.out.println(coming.toString());
				printDash();
				option = backToMenu(scanner, option);
				break;
			case 2://User add movies to list
				addMovie();
				printDash();
				option = backToMenu(scanner, option);
				break;
			case 3://User edits movies release dates
				editRelease();
				printDash();
				option = backToMenu(scanner, option);
				break;
			case 4://User edits movie description
				editDescription();
				printDash();
				option = backToMenu(scanner, option);
				break;
			case 5://Start showing movies with specified release date
				showRelease();
				printDash();
				option = backToMenu(scanner, option);
				break;
			case 6://Show number of movies before a date
				showBeforeDate();
				printDash();
				option = backToMenu(scanner, option);
				break;
			case 7:
				System.out.println("\nSaved!!\n");
				FileOutputStream outputFile = new FileOutputStream("input.txt");
				PrintWriter writer = new PrintWriter(outputFile);
				writer.print(coming.toFile());
				writer.print(showing.toFile());
				option = backToMenu(scanner, option);
				break;
			case 8:
				outputFile = new FileOutputStream("input.txt");
				writer = new PrintWriter(outputFile);
				writer.print(coming.toFile());
				writer.print(showing.toFile());
				movieInput.close();
				writer.close();
				break;
			}
		}
		
	}

	/**
	 * Convert user's string to a date value
	 * @param s: user date's string
	 * @return: the user date after converted 
	 */
	public static Date stringToDate(String s ){

	    Date result = null;
	    try{
	        result  = dateFormat.parse(s);
	    }
	    catch(ParseException e){
	        e.printStackTrace();
	    }
	    return result ;
	}
	
	
	/**
	 * Add a movie to the coming list if user chooses option 2
	 */
	public static void addMovie() { 

		//The user will be asked to set the name of the new movie
		System.out.print("Please set name of Movie: ");
		String movieName = movieInput.nextLine();
		
		//the user will then be prompted to set the description
		System.out.print("Please set description of movie: ");
		String movieDesc = movieInput.nextLine();
		
		//a new date is created with the user's inputs
		Date movieRelease = getDate("release date");
		
		
		//a new date is created for the receive year
		Date movieReceive = getDate("receive date");
		
		//set status to received
		String movieStatus = "received";
		
		//the movie class is created
		Movie newMovie = new Movie(movieName, movieRelease, capitalize(movieDesc), movieReceive, movieStatus);//NOTE: the formatting on the dates is incorrect
		
		coming.add(newMovie);
		
	}//end addMovie
	
	

}
