package entities;

import java.util.Date;  

public class Account {

	private String accNumber;
	private String agency;
	private String client;
	private String cpf;
	private int limit;
	private String accType;
	private double money;
	private String history;
	private String password;
	
	public Account(String client, String cpf, String accType, double money, String password, int accNumber, int limit, String history) {
		this.accNumber = "" + accNumber;
		this.agency = "123";
		this.client = client;
		this.cpf = cpf;
		this.limit = limit;
		this.accType = accType;
		this.money = money;
		this.password = password;
		this.history = (history == "") ? "Extrato bancário. Conta: " + this.accNumber + ", Agência: " + this.agency + "\nSaldo Inicial: " + money : history;
	}
	
	public boolean checkPassword(String password) {
		if(this.password.equals(password) == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean checkAgency(String agency) {
		if(this.agency.equals(agency) == true) {
			return true;
		}else {
			return false;
		}
	}
		
	public String deposit(double money) {
		
		Date currentDate = new Date();
		if(money>0) {
			this.setMoney(this.getMoney() + money);
			this.history += "\n [" + currentDate.getDate() + "/" + currentDate.getMonth() + 1 + "/" + currentDate.getYear() + " " + currentDate.getHours() + ":" + currentDate.getMinutes() + ":" + currentDate.getSeconds() + "]" +
					" Depositou R$" + money + " e o saldo atual é: "+ this.getMoney() + "."; 
			return "success";
		}else if(money>0) {
			return "zeroError";
		}else {
			return "negativeError";
		}
		
	}
	
	public String receiveMoney(double money, String userName) {
		
		Date currentDate = new Date();
		if(money>0) {
			this.setMoney(this.getMoney() + money);
			this.history += "\n [" + currentDate.getDate() + "/" + currentDate.getMonth() + 1 + "/" + currentDate.getYear() + " " + currentDate.getHours() + ":" + currentDate.getMinutes() + ":" + currentDate.getSeconds() + "]" +
					" Recebeu R$" + money + " de transferência do usuário " + userName + " e o saldo atual é: "+ this.getMoney() + "."; 
			return "success";
		}else if(money>0) {
			return "zeroError";
		}else {
			return "negativeError";
		}
		
	}
	
	public String changeLimit(int newLimit) {
		Date currentDate = new Date();
		
		if(newLimit > 0) {
			if(currentDate.getHours() > 6 || currentDate.getHours() < 22) {
				this.limit = newLimit;
				this.history += "\n [" + currentDate.getDate() + "/" + currentDate.getMonth() + 1 + "/" + currentDate.getYear() + " " + currentDate.getHours() + ":" + currentDate.getMinutes() + ":" + currentDate.getSeconds() + "]" +
						" Alterou o limite para " + newLimit + "."; 
				return "success";
			}else {
				if(this.limit >= newLimit) {
					this.limit = newLimit;
					this.history += "\n [" + currentDate.getDate() + "/" + currentDate.getMonth() + 1 + "/" + currentDate.getYear() + " " + currentDate.getHours() + ":" + currentDate.getMinutes() + ":" + currentDate.getSeconds() + "]" +
							" Alterou o limite para " + newLimit + "."; 
					return "success";
				}else {
					return "hourError";	
				}
			}
			
		}else if(newLimit == 0) {
			return "zeroError";
		}
		else {
			return "negativeError";
		}
	}
	
	public String withdrawMoney(double money) {
		Date currentDate = new Date();
		
		if(this.limit >= money) {
			if(this.money >= money) {
				this.setMoney(this.getMoney() - money);	
				this.history += "\n [" + currentDate.getDate() + "/" + currentDate.getMonth() + 1 + "/" + currentDate.getYear() + " " + currentDate.getHours() + ":" + currentDate.getMinutes() + ":" + currentDate.getSeconds() + "]" +
						" Retirou R$" + money + " e o saldo atual é: "+ this.getMoney() + "."; 
				return "success";
			}else {
				return "moneyError";
			}
		}else {
			return "limitError";
		}
		
	}
	
	public String sendMoney(double money, String userName) {
		Date currentDate = new Date();
		
		if(this.limit >= money) {
			if(this.money >= money) {
				this.setMoney(this.getMoney() - money);	
				this.history += "\n [" + currentDate.getDate() + "/" + currentDate.getMonth() + 1 + "/" + currentDate.getYear() + " " + currentDate.getHours() + ":" + currentDate.getMinutes() + ":" + currentDate.getSeconds() + "]" +
						" Transferiu R$" + money + " para o usuário " + userName + " e o saldo atual é: "+ this.getMoney() + "."; 
				return "success";
			}else {
				return "moneyError";
			}
		}else {
			return "limitError";
		}
		
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
