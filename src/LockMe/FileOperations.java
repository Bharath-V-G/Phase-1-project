package LockMe;


import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileOperations {
	static File f = new File("c:\\LOCKER\\User.db.txt");
	static String uname = " ";
	static String utype = "";
	static List<UserDetails> ArrUserDtls = new ArrayList<UserDetails>();

	public void createFile(List<UserDetails> uname, String name) {
		try {
			// create file output stream.
			FileOutputStream file = new FileOutputStream("c:\\\\LOCKER\\\\User.db.txt");

			// create object stream
			ObjectOutputStream out = new ObjectOutputStream(file);
			for (UserDetails ud : uname) {
				try {
					// method to serialize object
					out.writeObject(ud);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			out.close();
			file.close();
			File usrFile = new File("c:\\LOCKER\\" + name + ".txt");
			usrFile.createNewFile();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeUserFile(List<UserDetails> uname) {
		try {
			// create file output stream.
			FileOutputStream file = new FileOutputStream("c:\\\\LOCKER\\\\User.db.txt");

			// create object stream
			ObjectOutputStream out = new ObjectOutputStream(file);
			for (UserDetails ud : uname) {
				try {
					// method to serialize object
					out.writeObject(ud);

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			out.close();
			file.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeAppSrl(List<AppDetails> appName,File appf) {
		// 1. read a file
		try {
			// create file output stream.
			FileOutputStream file = new FileOutputStream(appf.getAbsoluteFile());

			// create object stream
			ObjectOutputStream out = new ObjectOutputStream(file);
			for (AppDetails ud : appName) {
				try {
					// method to serialize object
					out.writeObject(ud);
					//System.out.println(ud.getAppName());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			out.close();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<AppDetails> readAppSrl(File filename) throws IOException {
		// 1. read a file
		ObjectInputStream in =null;
		AppDetails appDtls;
		List<AppDetails> appdtls = new ArrayList<AppDetails>();
		try {
			 in = new ObjectInputStream(new FileInputStream(filename));
			// 3. method to de-serialized object
			while (true) {
				appDtls = (AppDetails) in.readObject();
				appdtls.add( appDtls);	
			}
		} catch (EOFException exc) {
			System.out.println("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appdtls;
	}
	public List<UserDetails> readFileSrl(File filename) {
		
		UserDetails usrd;
		ArrUserDtls = new ArrayList<UserDetails>();
		
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			
			while (true) {
				usrd = (UserDetails) in.readObject();
				ArrUserDtls.add( usrd);
			}
		} catch (Exception exc) {
			System.out.println("");
		}
		return ArrUserDtls;
	}
	public  boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
 class Menu {
	
	public void mainMenu(){
		System.out.println("----------------------Welcome------------------");
		System.out.println("1. Login ");
		System.out.println("2. Register ");
	}

	public void logMenu(String username) {
		System.out.println("--------------Welcome "+username +"--------------");
		System.out.println("1. Fetch credentials ");
		System.out.println("2. Store credentials ");
		System.out.println("3. Modify credentials ");
		System.out.println("4. Delete credentials");
		System.out.println("0. To Exit");
		
	}

}