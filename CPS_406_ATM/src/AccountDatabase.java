import java.util.ArrayList;

public class AccountDatabase {
	//a list of all accounts
	public ArrayList<Account> accounts;
	//the account that the ATM is "using"
	public static Account activeAccount;
	
	public AccountDatabase() {
		accounts = new ArrayList<Account>();
	}
	
	public void addAcount(Account a) {
		accounts.add(a);
	}
	
	public static void setActiveAccount(Account a) {
		activeAccount = a;
	}
}
