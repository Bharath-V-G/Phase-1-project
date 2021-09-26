package LockMe;
import java.io.*;
import java.util.*;
public class LoginUser {
	static File f = new File("c:\\LOCKER\\User.db.txt");
	static String uname = " ";
	static List<UserDetails> ArrUserDtls = new ArrayList<UserDetails>();
    public String getUser() {
		String user =uname;
	      return user;
		}
		public boolean login()
	{
		LoginUser user = new LoginUser();
		FileOperations fd = new FileOperations();
		Scanner s = new Scanner(System.in);
		boolean first = user.lockMe();
		if (!first) {
			Menu main = new Menu();
			main.mainMenu();
			String str = s.nextLine();
			
			while ((!fd.isNumeric(str)) || (!(str.equals("1")) && !(str.equals("2")))) {
				System.out.println("Invalid input");
				System.out.println(" ");
				main.mainMenu();
				str = s.nextLine();
			}
			int option = Integer.parseInt(str);
			switch (option) {
			case 1:
				try {
					System.out.println("------------------ ");
					System.out.println("Enter a user name");
					String userName = s.nextLine();
					uname = userName;
					ArrUserDtls = fd.readFileSrl(f);
					if (user.verifyUser(userName)) {
						System.out.println("Enter a Password");
						String password = s.nextLine();
						System.out.println("");
						if (user.verifyPassword(password)) {
							return true;
						} else {
							System.out.println("Invalid password");
							return false;
						}
					} else {
						System.out.println("Invalid user");
						return false;
					}

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Enter UserName");
				String usrName = s.nextLine();
				System.out.println("Enter the new user's password");
				String pass = s.nextLine();
				ArrUserDtls=fd.readFileSrl(f);
				ArrUserDtls.add(new UserDetails(usrName, pass));
				fd.createFile(ArrUserDtls, usrName);
				uname=usrName;				
				break;
			}

		} else {
			user.getUser();
			return true;
		}
		return true;
	}
	public boolean verifyUser(String userName) {
		for (UserDetails ud : ArrUserDtls) {
			if (userName.equals(ud.getUserId())) {
				return true;
			}
		}
		return false;
	}

	public boolean verifyPassword(String password) {

		for (UserDetails ud : ArrUserDtls) {

			if (uname.equals(ud.getUserId())&&password.equals(ud.getPassword())) {
				
				return true;
			}
		}
		return false;
	}
	public boolean lockMe() {
		if (!f.exists()) {
			System.out.println("=========================================");
			System.out.println("*				            *");
			System.out.println("*      Welcome To LockMe.com		    *");
			System.out.println("*   Your Personal Digital Locker	    *");
			System.out.println("*				       	    *");
			System.out.println("=========================================");
			System.out.println("-----------------------------------------------------------------");

			System.out.println("You have not yet registered. Please do registration before login.");
			System.out.println("-----------------------------------------------------------------");
			System.out.println("  ");
			Scanner s = new Scanner(System.in);
			System.out.println("Enter an user name");
			String userName = s.nextLine();
			uname = userName;
			System.out.println("Enter a  Password");
			String password = s.nextLine();
			File f = new File("c:\\LOCKER\\");
			try {
				f.mkdir();
				f = new File("c:\\\\LOCKER\\\\User.db.txt");
				f.createNewFile();
				try {
					// create file output stream.
					FileOutputStream file = new FileOutputStream(f.getAbsoluteFile());

					// create object stream
					ObjectOutputStream out = new ObjectOutputStream(file);
					UserDetails first = new UserDetails(userName, password);
					 out.writeObject(first);
					
					uname=userName;
					File usrFile = new File("c:\\LOCKER\\" + userName + ".txt");
					usrFile.createNewFile();
					out.close();
					file.close();

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				return true;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return false;
	}

}