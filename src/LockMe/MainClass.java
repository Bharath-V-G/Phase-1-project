package LockMe;

public class MainClass {

	public static void main(String[] args) {
		LoginUser adUsr = new LoginUser();
		String user;
		if (adUsr.login()) {
			user = adUsr.getUser();
			UserCredOperations appUsr = new UserCredOperations();
			appUsr.executeUser(user);
			}
		System.out.println("Thank you!");
	}
}