/**
 * @author Group 13
 * Class contains the information of the user's account, 
 * to be defined at the start of the program.
 */
public class AccountDatabase {
	private double savingsBalance;
	private double chequingBalance;
	private double creditDebt;
	private int accountNumber;
	private int PIN;

	/**
	 * Constructor initializes database class information.
	 * @param newAccountNumber variable to set the accountNumber of the account.
	 * @param newPIN variable to set the PIN of the account.
	 */
	public AccountDatabase (int newAccountNumber, int newPIN){
		savingsBalance = chequingBalance = 0;
		this.accountNumber = newAccountNumber;
		this.PIN = newPIN;
	}

	/**
	 * Blank constructor.
	 */
	public AccountDatabase (){
		return;
	}

	/**
	 * Returns PIN variable.
	 * @return PIN variable.
	 */
	public int getPIN (){
		return PIN;
	}

	/**
	 * Sets PIN of the account.
	 * @param newPIN variable to set PIN.
	 */
	public void setPIN(int newPIN){
		PIN = newPIN;
	}

	/**
	 * Gets the accountNumber of the class.
	 * @return accountNumber variable.
	 */
	public int getAccountNumber (){
		return accountNumber;
	}
	
	/**
	 * Sets the accountNumber from the parameter value.
	 * @param accntNmbr to set accountNumber.
	 */
	public void setAccountNumber (int accntNmbr){
		accountNumber = accntNmbr;
	}

	/**
	 * Returns the savings balance of the account.
	 * @return savingsBalance of account.
	 */
	public double getSavingsBalance(){
		return savingsBalance;
	}

	/**
	 * Sets the savings balance of the account from input parameter.
	 * @param newBalance to set savings balance.
	 */
	public void setSavingsBalance(double newBalance){
		savingsBalance = newBalance;
	}

	/**
	 * Returns the chequing balance of the account.
	 * @return chequingBalance of account.
	 */
	public double getChequingBalance(){
		return chequingBalance;
	}

	/**
	 * Sets the chequing balance of the account from input parameter.
	 * @param newBalance to set chequing balance.
	 */
	public void setChequingBalance(double newBalance){
		chequingBalance = newBalance;
	}
	
	/**
	 * Returns the creditDebt of the account.
	 * @return creditDebt value.
	 */
	public double getCreditDebt(){
		return creditDebt;
	}

	/**
	 * Sets the credit debt value.
	 * @param debt to set the creditDebt value.
	 */
	public void setCreditDebt(double debt){
		creditDebt = debt;
	}
}
