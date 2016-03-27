
public class Account {
	private double savingsBalance;
	private double chequingBalance;
	private double creditDebt;
	private int accountNumber;
	private int PIN;
	
	public Account (int newAccountNumber, int newPIN){
		savingsBalance = chequingBalance = 0;
		this.accountNumber = newAccountNumber;
		this.PIN = newPIN;
	}
	
	public Account (){
		return;
	}
	
	public int getPIN (){
		return PIN;
	}

	public void setPIN(int newPIN){
		PIN = newPIN;
	}

	public int getAccountNumber (){
		return accountNumber;
	}
	
	public void setAccountNumber (int accntNmbr){
		accountNumber = accntNmbr;
	}

	public double getSavingsBalance(){
		return savingsBalance;
	}

	public void setSavingsBalance(double newBalance){
		savingsBalance = newBalance;
	}

	public double getChequingBalance(){
		return chequingBalance;
	}

	public void setChequingBalance(double newBalance){
		chequingBalance = newBalance;
	}
	
	public double getCreditDebt(){
		return creditDebt;
	}

	public void setCreditDebt(double debt){
		creditDebt = debt;
	}
}
