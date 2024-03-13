package io ;


import java.io.IOException;
import java.util.Scanner;

/**
 * tool class for inputs (String or int)
 *
 * @author Minaud Mathide Nollet Antoine
 * @version 05/10/2020
 */
public class Input {

	private static Scanner scanner = new Scanner(System.in);

	/**
	 * reads a string in standard input
	 * @return the read string
	 */
	public static String readString() {
	    return Input.scanner.nextLine();
	}

}// Input
