import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entities.Account;

public class Main {

	public static List<Account> loadDatabase() {
		
		List<Account> loadedAccounts = new ArrayList<>();
		File file = new File("src/database/accounts.txt");
		Scanner sc = null;
		
		
		
		try {
			sc = new Scanner(file);
			String accountsFile = "";
			
			
			while(sc.hasNextLine()) {
				accountsFile += sc.nextLine();
			}
			
			String[] accountsSplited = accountsFile.split("-");
			
			for(String accountSplited : accountsSplited) {
				String[] elements = accountSplited.split(";");
				loadedAccounts.add(new Account(elements[2], elements[3], elements[4], Double.parseDouble(elements[5]), elements[6], Integer.parseInt(elements[0]), Integer.parseInt(elements[7]), elements[8]));
			}
			
			return loadedAccounts;
			
		}catch(IOException e) {
			System.out.println("Error: " + e);
			return loadedAccounts;
		}finally {
			if(sc != null) {
				sc.close();
				
			}
		}		
		
	}

	public static boolean checkCPF(String cpf) {
		if (cpf.equals("00000000000") ||
	            cpf.equals("11111111111") ||
	            cpf.equals("22222222222") || cpf.equals("33333333333") ||
	            cpf.equals("44444444444") || cpf.equals("55555555555") ||
	            cpf.equals("66666666666") || cpf.equals("77777777777") ||
	            cpf.equals("88888888888") || cpf.equals("99999999999") ||
	            (cpf.length() != 11))
	            return(false);

	        char dig10, dig11;
	        int sm, i, r, num, peso;

	        try {
	            sm = 0;
	            peso = 10;
	            for (i=0; i<9; i++) {
	            num = (int)(cpf.charAt(i) - 48);
	            sm = sm + (num * peso);
	            peso = peso - 1;
	            }

	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                dig10 = '0';
	            else dig10 = (char)(r + 48); 
	            
	            sm = 0;
	            peso = 11;
	            for(i=0; i<10; i++) {
	            num = (int)(cpf.charAt(i) - 48);
	            sm = sm + (num * peso);
	            peso = peso - 1;
	            }

	            r = 11 - (sm % 11);
	            if ((r == 10) || (r == 11))
	                 dig11 = '0';
	            else dig11 = (char)(r + 48);

	            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
	                 return(true);
	            else return(false);
	                } catch (InputMismatchException erro) {
	                return(false);
	            }
	}

	public static boolean comparePassword(String password1, String password2) {
		if(password1.equals(password2)) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean checkValidPassword(String password) {
		
		String regex = "^(?=.*[0-9])" 
                + "(?=.*[a-z])(?=.*[A-Z])" 
                + "(?=.*[@#$%^&+=])" 
                + "(?=\\S+$).{6,20}$"; 
		
		Pattern p = Pattern.compile(regex);
		
		if (password == null) {
            return false;
        }
		
		Matcher m = p.matcher(password);
		
		return m.matches();
	}

	public static void saveAccounts(List<Account> allUsers) {
		
	
		String path = "src/database/accounts.txt";
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
			for(Account user: allUsers) {
				String line = user.getAccNumber() + ";" + user.getAgency() + ";" + user.getClient() + ";" + user.getCpf() + ";"
							+ user.getAccType() + ";" + user.getMoney() + ";" + user.getPassword() + ";" + user.getLimit() + ";" 
							+ user.getHistory() + "-";
;				bw.write(line);
				bw.newLine();
				
				//1,123,João Jonas,96385274114,Débito,2300,teste1,1000;
			}
				
			System.out.println("Extrato gerado com sucesso." );
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean shutOff = false;
		List<Account> allUsers = new ArrayList<>();
		Account currentUser = null;
		
		allUsers = loadDatabase();
		
		do {
			System.out.println("Seja bem vindo ao Banquinho. Como posso te ajudar?");
			System.out.println("1 - Acessar minha conta");
			System.out.println("2 - Criar conta");
			System.out.println("3 - Desligar Máquina");
			
			char firstStep = '0';
			
			while(firstStep != '1' && firstStep != '2' && firstStep != '3') {
				firstStep = sc.next().charAt(0);
				sc.nextLine();
				
				if(firstStep == '1') {
					String accNumber = "";
					String agency = "";
					String password = "";
					
					System.out.println("1 - Acessar");
					System.out.println("Por favor, digite o número da sua conta");
					accNumber = sc.nextLine();
					System.out.println("Digite o número da sua agência");
					agency = sc.nextLine();
					System.out.println("Digite sua senha.");
					password = sc.nextLine();
					
					int position = Integer.parseInt(accNumber) - 1;;
				;
					if(allUsers.size() > position) {
						if(allUsers.get(position).checkAgency(agency) == true) {
							if(allUsers.get(position).checkPassword(password) == true) {
								currentUser = allUsers.get(position);
								System.out.println("Estamos te logando...");
							}else {
								System.out.println("Senha incorreta. Por favor, tente novamente.");
							}
						}else {
							System.out.println("Número de agência não corresponde ao do usuário.");
						}
						
					}else {
						System.out.println("Não encontramos usuário com esse número de conta.");
					}
					
				}else if(firstStep == '2') {
					
					String name = "";
					String cpf = "";
					int accType = 0;
					String password1 = "";
					String password2 = "";
					int aux = 0;
					
					System.out.println("É sua primeira vez aqui? Preciso de algumas informações para criar sua conta.");
					System.out.println("Por favor, digite o seu nome.");
					name = sc.nextLine();
					do {
						if(aux > 0) {
							System.out.println("Por favor, digite um CPF válido. Tentativa " + aux + " de 3");
						}
						System.out.println("Agora preciso do seu CPF.");
						cpf = sc.nextLine();
						aux++;
					}while(checkCPF(cpf) == false && aux != 4);
					if(aux == 4) {
						System.out.println("Sessão finalizada.");
					}else {
						aux = 0;
						
						do {
							
							if(aux > 0) {
								System.out.println("Por favor, digite uma opção válida. Tentativa " + aux + " de 3");
							}
							
							System.out.println("Gostaria de criar uma conta de débito ou crédito? Digite apenas o número.");
							System.out.println("1 - Débito");
							System.out.println("2 - Crédito");
							accType = sc.nextInt();
							sc.nextLine();
							aux++;
						}while((accType != 1 && accType != 2) && aux != 4);
						if(aux == 4) {
							System.out.println("Sessão finalizada.");
						}else {
							aux = 0;
							
							do {
								
								if(aux > 0) {
									System.out.println("Por favor, digite uma senha válida (Com 6 caracteres, com uma letra maiuscula, uma minuscula e um número). Garanta que as duas senhas digitadas sejam iguais. Tentativa " + aux + " de 3");
								}
								
								System.out.println("Digite sua senha.");
								password1 = sc.nextLine();
								System.out.println("Digite sua senha novamente.");
								password2 = sc.nextLine();
								
								aux++;
							
							}while((comparePassword(password1, password2) == false || checkValidPassword(password1) == false) && aux != 4);
							
							if(aux == 4) {
								System.out.println("Sessão finalizada.");
							}else {
								aux = 0;
								

								currentUser = new Account(name, cpf, (accType == 1) ? "Débito": "Crédito",0, password1, allUsers.size()+1, 1000, "");
								allUsers.add(new Account(name, cpf, (accType == 1) ? "Débito": "Crédito",0, password1, allUsers.size()+1, 1000, ""));
								
								System.out.println("Aguarde um momento, estamos analisando suas informações....");
								System.out.println("Sua conta foi aceita! Por favor, anote o número da conta: '" + allUsers.get(allUsers.size()-1).getAccNumber() + "' e número da sua agência: '" + allUsers.get(allUsers.size()-1).getAgency() + "'. Limite aprovado de 1000 reais.");
								
								
							}
							
							
							
						}
						
						
						
						
					}
					
					
				}else if(firstStep == '3') {
					System.out.println("Máquina desligando...");
					shutOff = true;
				}else {
					System.out.println("Por favor, digite uma opção válida.");
					System.out.println("1 - Acessar minha conta");
					System.out.println("2 - Criar conta");
					System.out.println("3 - Desligar Máquina");
				}	
			}
			
			
			if(currentUser != null) {
				char loggedInMenu = '0';
				int position = Integer.parseInt(currentUser.getAccNumber()) - 1;;
				
				System.out.println("----------------- MENU PRINCIPAL ---------------");
				System.out.println("\nOlá " + allUsers.get(position).getClient());
				
				
				while(loggedInMenu != '7') {
					
					System.out.println("Selecione uma opção");
					System.out.println("1 - Transferir");
					System.out.println("2 - Depositar");
					System.out.println("3 - Retirar");
					System.out.println("4 - Alterar limite");
					System.out.println("5 - Ver extrato");
					System.out.println("6 - Gerar extrato");
					System.out.println("7 - Sair");
					
					loggedInMenu = sc.next().charAt(0);
					sc.nextLine();
					
					if(loggedInMenu == '1') {
						System.out.println("1 - Transferir");
						System.out.println("Por favor, digite o número da sua conta que deseja transferir.");
						String accNumber = sc.nextLine();
						System.out.println("Por favor, digite a agencia que deseja transferir.");
						String agency = sc.nextLine();
						int positionTemp = Integer.parseInt(accNumber) - 1;;
												
						if(allUsers.size() > positionTemp ) {
							if(allUsers.get(positionTemp).checkAgency(agency) == true) {
								System.out.println("Você gostaria de transferir para o usuário " + allUsers.get(positionTemp).getClient() + " portador do cpf " + allUsers.get(positionTemp).getCpf().substring(0,3) + ".***.***-** (sim/não)?");
								String simNao = sc.nextLine();
								if(simNao.toLowerCase().equals("sim")) {
									System.out.println("Qual valor você gostaria de transferir?" );
									double moneyTransfered = sc.nextDouble();
									
									String response = allUsers.get(position).sendMoney(moneyTransfered, allUsers.get(positionTemp).getClient());
									
									if(response == "success") {
								
										String responseTarget = allUsers.get(positionTemp).receiveMoney(moneyTransfered, allUsers.get(position).getClient());
										
										if(responseTarget == "success") {
											System.out.println("Dinheiro transferido. Seu saldo atual é: " + allUsers.get(position).getMoney() + ".");
										}else {
											System.out.println("Houve um problema na transferência. O usuário não pode receber seu dinheiro e ele foi devolvido para sua conta.");
											allUsers.get(position).deposit(moneyTransfered);
											System.out.println("Seu saldo atual é: " + allUsers.get(position).getMoney() + ".");
										}
									}
									else if(response == "moneyError"){
										System.out.println("Saldo para transferência insuficiente. Seu saldo atual é: " + allUsers.get(position).getMoney() + ".");
									}	
									else {
										System.out.println("Tentativa de transferir valor maior que o limite.");
									}
								}else {
									System.out.println("Transação encerrada.");
								}
							}else {
								System.out.println("Número de agência não corresponde ao do usuário.");
							}
						}else {
							System.out.println("Não encontramos usuário com esse número de conta.");
						}
						
					}else if(loggedInMenu == '2') {
						
						System.out.println("2 - Depositar");
						System.out.println("Qual valor você gostaria de depositar?" );
						double newValueDeposited = sc.nextDouble();
						String response = allUsers.get(position).deposit(newValueDeposited);
						if(response == "success") {
							System.out.println("Dinheiro depositado. Seu saldo atual é: " + allUsers.get(position).getMoney() + ".");
						}else {
							System.out.println("Por favor, digite um número maior que zero. Seu saldo atual é: " + allUsers.get(position).getMoney() + ".");
						}
						
						
						
					}else if(loggedInMenu == '3') {
						
						System.out.println("3 - Retirar");
						System.out.println("Qual valor você gostaria de retirar?" );
						double newValueRemoved = sc.nextDouble();
						String response = allUsers.get(position).withdrawMoney(newValueRemoved);
						if(response == "success") {
							
							System.out.println("Dinheiro retirado. Seu saldo atual é: " + allUsers.get(position).getMoney() + ".");
						}
						else if(response == "moneyError"){
							System.out.println("Saldo para retirada insuficiente. Seu saldo atual é: " + allUsers.get(position).getMoney() + ".");
						}	
						else {
							System.out.println("Tentativa de saque maior que o limite.");
						}
			
					}else if(loggedInMenu == '4') {
						
						System.out.println("4 - Alterar limite");
						System.out.println("Qual seu novo valor de limite?" );
						int newLimit = sc.nextInt();
						String response = allUsers.get(position).changeLimit(newLimit);
						if(response == "success") {
							System.out.println("Limite atualizado com sucesso" );
						}else if(response == "hourError"){
							System.out.println("Não é possível aumentar o limite neste horário. Por favor, tente novamente de dia." );
						}else {
							System.out.println("Não é possível mudar o limite para menor ou igual a zero." );
						}
						
					}else if(loggedInMenu == '5') {
						
						System.out.println(allUsers.get(position).getHistory());
						
						
					}else if(loggedInMenu == '6') {
						
						String[] lines = allUsers.get(position).getHistory().split("\n");
						System.out.println("Aguarde um momento. Estamos gerando seu extrato...." );
						
						String path = "src/database/extrato.csv";
						
						try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
							for(String line: lines) {
								bw.write(line);
								bw.newLine();
							}
								
							System.out.println("Extrato gerado com sucesso." );
						}catch(IOException e) {
							e.printStackTrace();
						}
					
					}else if(loggedInMenu == '7') {
						
						System.out.println("Sair");
						currentUser = null;
						
					}else {
						System.out.println("Por favor, digite uma opção válida.");
					}	
				}
				
				
			}else {
				
			}
		
		}while(shutOff == false);
		
		saveAccounts(allUsers);
		
		sc.close();		
		
	}

}
