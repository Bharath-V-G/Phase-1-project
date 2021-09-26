package LockMe;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserCredOperations {

	static String uname = " ";
	static List<AppDetails> ArrAppDtls = new ArrayList<AppDetails>();

	public void executeUser(String user) {
		uname = user;
	
		File uf = new File("c:\\LOCKER\\" + uname + ".txt");
		FileOperations fd = new FileOperations();
		Menu menu = new Menu();
		int cnt = 9;
		while (cnt == 9) {
			menu.logMenu(uname);
			System.out.println("");
			Scanner s1 = new Scanner(System.in);
			String str = s1.nextLine();

			while ((!fd.isNumeric(str)) || (!(str.equals("1")) && !(str.equals("2")) && !(str.equals("3"))
					&& !(str.equals("4"))&& !(str.equals("0")))) {
				System.out.println("Invalid input");
				System.out.println(" ");
				menu.logMenu(uname);
				str = s1.nextLine();

			}

			int option = Integer.parseInt(str);
			List<AppDetails> ArrAppDtlsnew = new ArrayList<AppDetails>();
			switch (option) {
			case 1:
				/* List all application details */
				try {
					ArrAppDtls = fd.readAppSrl(uf);
					int c=1;
					      System.out.println("---------------------------App details----------------------------");
					      System.out.print("sl.no");
							System.out.print('\t');
							System.out.print('\t');
							System.out.print("App name");
							System.out.print('\t');
							System.out.print('\t');
							System.out.print("User ID");
							System.out.print('\t');
							System.out.print('\t');
							System.out.println("Password");
					for (AppDetails aud : ArrAppDtls) {
						
						System.out.println("------------------------------------------------------------------");
						System.out.print(c);
						System.out.print('\t');
						System.out.print('\t');
						System.out.print(aud.getAppName());
						System.out.print('\t');
						System.out.print('\t');
						System.out.print(aud.getUserId());
						System.out.print('\t');
						System.out.print('\t');
						System.out.println(aud.getPassword());
						System.out.println("------------------------------------------------------------------");						c=c+1;
					}
				} catch (Exception e) {

				}
				break;
			case 2:
				/* Add application details */
				System.out.println("********************************");
				System.out.println("Enter App Name");
				String appName = s1.nextLine();
				System.out.println("Enter App User Name");
				String usrName = s1.nextLine();
				System.out.println("Enter App  password");
				String pass = s1.nextLine();
				System.out.println("********************************");
				try {
					ArrAppDtls = fd.readAppSrl(uf);
					ArrAppDtls.add(new AppDetails(appName, usrName, pass));
					fd.writeAppSrl(ArrAppDtls, uf);
				}

				catch (Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
				break;
			case 3:
				/* Change application details */
				try {
					ArrAppDtls = fd.readAppSrl(uf);
					System.out.println("Enter the app name you want to change : ");
					String appname = s1.nextLine();
					System.out.println("Enter the new username for the app : ");
					String usrname = s1.nextLine();
					System.out.println("Enter the new password for the app : ");
					String apppass = s1.nextLine();
					for (AppDetails aud : ArrAppDtls) {
						if (appname.equals(aud.getAppName())) {
							ArrAppDtlsnew.add(new AppDetails(appname, usrname, apppass));
						} else {
							ArrAppDtlsnew.add(new AppDetails(aud.getAppName(), aud.getUserId(), aud.getPassword()));
						}
					}
					fd.writeAppSrl(ArrAppDtlsnew, uf);
					System.out.println("-----------Updated successfully-------------- \n");
				} catch (Exception e) {

				}
				break;
			case 4:
				/* Delete application details */

				try {
					ArrAppDtls = fd.readAppSrl(uf);
					System.out.println("Enter the app name you want to delete : ");
					String appname = s1.nextLine();
					for (AppDetails aud : ArrAppDtls) {
						if (appname.equals(aud.getAppName())) {
						} else {
							ArrAppDtlsnew.add(new AppDetails(aud.getAppName(), aud.getUserId(), aud.getPassword()));
						}
						fd.writeAppSrl(ArrAppDtlsnew, uf);
						}
					System.out.println("-----------Deleted successfully-------------- \n");
				} catch (Exception e) {

				}
				break;
			case 0:
				System.out.println("------------------------------------");
				System.out.println("!!!!!!!!Are you sure to exit!!!!!!!!");
				System.out.println("------------------------------------");
				break;
			}
			System.out.println("");
			System.out.println("Press 9 to Continue OR 0 to Exit");
			str = s1.nextLine();

			while (!(fd.isNumeric(str)) || (!str.equals("9") && !str.equals("0"))) {
				System.out.println("");
				System.out.println("Press 9 to Continue OR 0 to Exit");

				System.out.println(str);
				str = s1.nextLine();

			}
			cnt = Integer.parseInt(str);
		}
	}
}