package assthree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UserLoginApplication {
	public static User[] users = new User[4];
	private static UserService userService = new UserService();
	
	public static void main (String[] args) throws IOException {
		populateUsersArray();
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(System.in);
			
			boolean validLogin = false;
			int loginAttempts = 0;
			while (!validLogin && loginAttempts < 5) {
				System.out.println("Enter your email:");
				String username = scanner.nextLine();
				System.out.println("Enter your password: ");
				String password = scanner.nextLine();
				
				User validUser = userService.isValidUser(username, password);
				if (validUser != null) {
					System.out.println("Welcome: " + validUser.getName());
					validLogin = true;
				} else {
					System.out.println("Invalid login, please try again");
					loginAttempts++;
					if (loginAttempts >= 5) {
						System.out.println("Too many failed login attempts, you are now locked out.");
					}
				}
			}
		} finally {
			if (scanner != null) 
				scanner.close();
		}
		
	}

	private static void populateUsersArray() throws FileNotFoundException, IOException {
		String fileName = "data.txt";
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(fileName ));
			String line = null;
			int i = 0;
			while ((line = br.readLine()) != null) {
				users[i] = new User(line.split(","));
				System.out.println(users[i]);
				i++;
			}
		} finally {
			if (br != null)
				br.close();
		}

	}
}