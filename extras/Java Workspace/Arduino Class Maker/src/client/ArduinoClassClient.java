package client;
import java.util.Scanner;

import classGenerators.ArduinoClassCpp;
import classGenerators.ArduinoClassH;
import enums.ArduinoClassExample;
import enums.ArduinoClassPrompts;

/**Name: Jacob Smith 
 * Date 5/14/2049 Personal Study, uses ArduinoClasses to autaomtically generate header, class, and keyword files
 * Bugs: array position hardcoded*/
public class ArduinoClassClient {
	public static void main (String[]args) {
				//This example generates a class represented as a string
				//The user can decide how these string will be inputted
				//These fields are the minimum required to generate an arduino class
				
				//use any of examples
				//simplestExample();
				scannerExample();
				
	}
	
	/**
	 * Creates an arduino cpp, header, and keyword file strings with no input or output
	 * */
	public static void simplestExample(){
	
		//call helper method to display the generated files
		ArduinoClassClient.createFiles(ArduinoClassExample.CLASSNAME.toString(),
				ArduinoClassExample.AUTHOR.toString(),
				ArduinoClassExample.ORGANIZATION.toString(),
				ArduinoClassExample.HEADERCOMMENTS.toString(),
				ArduinoClassExample.SUPPORTEDBOARDS.toString(),
				ArduinoClassExample.VARIABLES.toString(),
				ArduinoClassExample.PRIVATEMETHODS.toString(),
				ArduinoClassExample.PUBLICMETHODS.toString());
			
	}
	
	/**
	 * Creates an arduino cpp, header, and keyword file strings using Scanner as input
	 * */
	public static void scannerExample() {
		//initalize a new scanner to read keyboard input
		Scanner reader=new Scanner(System.in);
		//generate an array of field names
		ArduinoClassPrompts []fields=ArduinoClassPrompts.values();
		ArduinoClassExample[]examples=ArduinoClassExample.values();
		//create an array of the same length to hold the user's answers
		String []userAnswers=new String[fields.length];
		//temporary variable for code readability
		ArduinoClassPrompts field;
		ArduinoClassExample example;
		//iterate through all the fields, displaying prompt, example formatting, and reading user input
		for(int i=0;i<fields.length;i++){
			field=fields[i];
			example=examples[i];
			//display prompt
			System.out.println(field.prompt+"\n"+example.toString());
			//read user input
			userAnswers[i]=reader.nextLine();
		}
		
		try{
			//call helper method to display the generated files, array positions hardcoded
			ArduinoClassClient.createFiles(userAnswers[0], userAnswers[1], userAnswers[2], userAnswers[3], userAnswers[4], userAnswers[5], userAnswers[6], userAnswers[7]);
		}catch (Exception e){
			System.out.println("Sorry, there was a formatting error in your input, couldn't make class");
			System.out.println("Would you like to view the error? Y/N");
			String answer=reader.next().toLowerCase();
			//if user doens't enter y or n, display error prompt
			while(!(answer.equals("y") | answer.equals("n"))){
				System.out.print("Please enter Y/N:");
				answer=reader.next().toLowerCase();
			}
			if(answer.equals("y")){
				e.printStackTrace();
				sleepNoError(500);
			}
			
		}
		System.out.println("Thank you, closing down now");
		reader.close();
	
	}
	
	/**
	 * Helper method to tell computer to sleep without needing throws declaration
	 */
	private static void sleepNoError(int sleepTime){
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e1) {
			System.out.println("Error with sleep statement");
		}
		
	}
	
	/**
	 * Helper method to create the header, body, and keyword files
	 * */
	private static void createFiles(String className, String author, String organization,String headerComments, String supportedBoards, String variables, String privateMethods, String publicMethods){
		ArduinoClassCpp template=new ArduinoClassCpp(className,author,organization,headerComments,supportedBoards,variables,privateMethods,publicMethods);
		System.out.println(template.toString());
		ArduinoClassH template2=new ArduinoClassH(className,author,organization,headerComments,supportedBoards,variables,privateMethods,publicMethods);
		System.out.println(template2.toString());
		System.out.println(template2.getKeywords());
	}
}