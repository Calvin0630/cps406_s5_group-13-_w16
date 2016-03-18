public class Main {

    public static void main(String[] args) {
        new ATM_GUI();
    }
}

class AccountDatabase {
	private double savingsBalance;
	private double chequingBalance;
	private int accountNumber;
	private int PIN;
	
	public AccountDatabase (int newAccountNumber, int newPIN){
		savingsBalance = chequingBalance = 0;
		this.accountNumber = newAccountNumber;
		this.PIN = newPIN;
	}
	
	public AccountDatabase (){
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
}
