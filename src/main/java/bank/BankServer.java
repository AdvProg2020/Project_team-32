package bank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.json.simple.JSONObject;

public class BankServer {
	
	private static int port;
	
	
	private static class InvalidToken extends Exception {
		
	}
	
	private static class ExpiredToken extends Exception {
		
	}
	
	private static class NotEnoughMoneyInSourceAccount extends Exception {
		
	}
	
	private static class Account {
		
		private static List<Account> allAccounts = new ArrayList<>();
		
		private String firstname;
		private String lastname;
		private String username;
		private String password;
		private int accountId;
		private List<Token> allTokens;
		private Token currentToken;
		private List<Receipt> receiptsWithThisAccountAsSource = new LinkedList<>();
		private List<Receipt> receiptssWithThisAccountAsDestination = new LinkedList<>();
		private int money = 1000;
		
		public Account(String firstname, String lastname, String username, String password ) {
			this.firstname = firstname;
			this.lastname = lastname;
			this.username = username;
			this.password = password;
			allAccounts.add(this);
			this.accountId = allAccounts.size();
			this.allTokens = new LinkedList<>();
		}
		
		void addToken(Token token) {
			this.allTokens.add(token);
			this.currentToken = token;
		}
		
		static ValidatingTokenStatus checkToken(int accountId, String token) {
			for(Token t : Account.allAccounts.get(accountId - 1).allTokens) {
				if(t.token.equals(token)) {
					if(Account.allAccounts.get(accountId - 1).currentToken == t)
						return ValidatingTokenStatus.valid;
					else {
						return ValidatingTokenStatus.expired;
					}
				}
			}
			
			return ValidatingTokenStatus.invalid;
		}
		
		static int getAccountIdByToken(String token) throws ExpiredToken, InvalidToken {
			for(Account account: allAccounts) {
				if(account.currentToken.token.equals(token)) {
					return account.accountId;
				}
				for(Token t : account.allTokens) {
					if(t.token.equals("token")) {
						throw new ExpiredToken();
					}
				}
			}
			
			throw new InvalidToken();
		}
		
		
		
	}
	
	private enum ValidatingTokenStatus {
		valid, invalid, expired
	}
	
	private static class Token {
		
		private String token;
		private Date expirationDate;
		
		public Token(String token) {
			this.token = token;
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.HOUR, 1);
			this.expirationDate = cal.getTime();
		}
		
	}
	
	private static class ReceiptFactory {
		public static Receipt createReceipt(ReceiptTypes type, int money, int sourceId, int destId, String description) {
			if(type.equals(ReceiptTypes.deposit) ) {
				return new DepositReceipt(type, money, sourceId, destId, description);
			} else if (type.equals(ReceiptTypes.withdraw)) {
				return new WithdrawReceipt(type, money, sourceId, destId, description);

			} else  {
				return new MoveReceipt(type, money, sourceId, destId, description);
			}
		}
	}
	
	private enum ReceiptTypes {
		deposit, withdraw, move
	}
	
	private static abstract class Receipt {
		static List<Receipt> allReceipts = new ArrayList<Receipt>();
		
		ReceiptTypes type;
		int money;
		int sourceId;
		int destId;
		String description;
		int receiptId;
		boolean payStatus;
		
		public Receipt(ReceiptTypes type, int money, int sourceId, int destId, String description) {
			this.type = type;
			this.money = money;
			this.sourceId = sourceId;
			this.destId = destId;
			this.description = description;
			allReceipts.add(this);
			this.receiptId = allReceipts.size();
			if(type == ReceiptTypes.deposit) {
				Account.allAccounts.get(destId - 1).receiptssWithThisAccountAsDestination.add(this);
			} else if(type == ReceiptTypes.withdraw) {
				Account.allAccounts.get(sourceId - 1).receiptsWithThisAccountAsSource.add(this);
			} else {
				Account.allAccounts.get(sourceId - 1).receiptsWithThisAccountAsSource.add(this);
				Account.allAccounts.get(destId - 1).receiptssWithThisAccountAsDestination.add(this);
			}
		}
		
		static ReceiptTypes getTypeByName(String string) {
			switch(string) {
				case "deposit" :
					return ReceiptTypes.deposit;
				case "withdraw":
					return ReceiptTypes.withdraw;
				case "move":
					return ReceiptTypes.move;
			}
			return null;
		}
		
		static List<Receipt> getAllReceiptsWithSourceAccount(int id) {
			List<Receipt> output = new LinkedList<Receipt>();
			for(Receipt receipt: allReceipts) {
				if(receipt.sourceId == id) {
					output.add(receipt);
				}
			}
			return output;
			
		}
		
		static List<Receipt> getAllReceiptsWithDestinationAccount(int id) {
			List<Receipt> output = new LinkedList<Receipt>();
			for(Receipt receipt: allReceipts) {
				if(receipt.destId == id) {
					output.add(receipt);
				}
			}
			return output;
		}
		
		static List<Receipt> getAllReceiptsWithAccount(int id) {
			List<Receipt> output = new LinkedList<>();
			output.addAll(getAllReceiptsWithDestinationAccount(id));
			output.addAll(getAllReceiptsWithSourceAccount(id));
			return output;
			
		}
		
		
		JSONObject convertToJSONObject() {
			JSONObject object = new JSONObject();
			object.put("receiptTypes", type);
			object.put("money", money);
			object.put("sourceAccountID", sourceId);
			object.put("destAccountID", destId);
			object.put("description", description);
			object.put("id", receiptId);
			object.put("paid", payStatus);
			return object;
		}
		
		public abstract void pay() throws Exception;
		
	}
	
	private static class DepositReceipt extends Receipt {
		public DepositReceipt(ReceiptTypes type, int money, int sourceId, int destId, String description) {
			super(type, money, sourceId, destId, description);
		}

		@Override
		public void pay() throws Exception {
			Account.allAccounts.get(destId - 1).money += money;
		}
		
	}
	
	private static class WithdrawReceipt extends Receipt {
		public WithdrawReceipt(ReceiptTypes type, int money, int sourceId, int destId, String description) {
			super(type, money, sourceId, destId, description);
		}

		@Override
		public void pay() throws Exception {
			Account sourceAccount;
			if((sourceAccount = Account.allAccounts.get(sourceId - 1)).money < money) {
				throw new NotEnoughMoneyInSourceAccount();
			} else {
				sourceAccount.money -= money;
			}
		}
		
	}
	
	private static class MoveReceipt extends Receipt {

		public MoveReceipt(ReceiptTypes type, int money, int sourceId, int destId, String description) {
			super(type, money, sourceId, destId, description);
		}

		@Override
		public void pay() throws Exception {
			Account sourceAccount;
			Account destAccount;
			if((sourceAccount = Account.allAccounts.get(sourceId - 1)).money < money) {
				throw new NotEnoughMoneyInSourceAccount();
			} else {
				destAccount = Account.allAccounts.get(destId - 1);
				sourceAccount.money -= money;
				destAccount.money += money;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		startListeningOnPort();
	}
	
	public static void startListeningOnPort() throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		while(true) {
			new HandleClientThread(serverSocket.accept()).start();
		}
	}
	
	private static class HandleClientThread extends Thread {
		
		private Scanner scanner;
		private Formatter formatter;
		
		public HandleClientThread(Socket socket) {
			try {
				scanner = new Scanner(socket.getInputStream());
				formatter = new Formatter(socket.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			String command;
			while(!(command = scanner.nextLine()).equals("exit")) {
				String[] commandParts = command.split(" ");
				if(commandParts[0].equals("create_account")) {
					format(createAccount(commandParts));
				} else if(commandParts[0].equals("get_token")) {
					format(getToken(commandParts) + "" );
				} else if(commandParts[0].equals("create_receipt")) {
					format(createReceipt(commandParts));
				} else if(commandParts[0].equals("get_transactions")) {
					format(getTransactions(commandParts));
				} else if(commandParts[0].equals("pay")) {
					format(pay(commandParts));
				} else if(commandParts[0].equals("getBalance")) {
					format(getBalance(commandParts));
				}
				
				// TODO checking number of parameters in each of states
			}
			
		}
		
		private void format(String msg) {
			formatter.format(msg + "\n");
			formatter.flush();
		}
	}
	
	public static String createAccount(String[] commandParts) {
		String firstname = commandParts[1];
		String lastname = commandParts[2];
		String username = commandParts[3];
		String password = commandParts[4];
		String repeatedPassword = commandParts[5];
		
		if(!repeatedPassword.equals(password)) {
			return "passowrds do not match";
		}
		
		for(Account account: Account.allAccounts) {
			if(account.username.equals(username)) {
				return "username is not available";
			}
		}
		return new Account(firstname, lastname, username, password).accountId + "";
	}
	
	public static String getToken(String[] commandParts) {
		String username = commandParts[1];
		String password = commandParts[2];
		for(Account account: Account.allAccounts) {
			if(account.username.equals(username) && account.password.equals(password)) {
				return generateToken(account);
			}
		}
		return "invalid username or password";
	}
	
	public static String createReceipt(String[] commandParts) {
		String token = commandParts[1];
		ReceiptTypes type = Receipt.getTypeByName(commandParts[2]);
		int money;
		int sourceId;
		int destId;
		
		
		if(type == null) {
			return "invalid receipt type";
		}
		
		try {
			money = Integer.parseInt(commandParts[3]);
			if(money <= 0) {
				throw new Exception();
			}
		} catch(Exception e)  {
			return "invalid money";
		}
		
		
		try {
			sourceId = Integer.parseInt(commandParts[4]);
			if(type == ReceiptTypes.deposit && (sourceId!= -1 )) {
				throw new Exception();
			}
			if(sourceId > Account.allAccounts.size()) {
				throw new Exception();
			}
		} catch(Exception e) {
			return "source account id is invalid";
		}
		
		
		try {
			destId = Integer.parseInt(commandParts[5]);
			if(type == ReceiptTypes.withdraw && destId!= -1) {
				throw new Exception();
			}
			if(destId > Account.allAccounts.size()) {
				throw new Exception();
			}
		} catch(Exception e) {
			return "dest account id is invalid";
		}
		
		if(type == ReceiptTypes.deposit) {
			if(Account.checkToken(destId, token) == ValidatingTokenStatus.invalid) {
				return "token is invalid";
			} 
			if(Account.checkToken(destId, token) == ValidatingTokenStatus.expired) {
				return "token expired";
			}
		} else if(type == ReceiptTypes.withdraw || type == ReceiptTypes.move) {
			if(Account.checkToken(sourceId, token) == ValidatingTokenStatus.invalid) {
				return "token is invalid";
			} 
			if(Account.checkToken(sourceId, token) == ValidatingTokenStatus.expired) {
				return "token expired";
			}
		}
		
		if(sourceId == destId) {
			return "equal source and dest account";
		}
		
		
		String description = commandParts[6];
		
		return ReceiptFactory.createReceipt(type, money, sourceId, destId, description).receiptId + "";
	}

	public static String getTransactions(String[] commandParts) {
		
		String token = commandParts[1];
		String type = commandParts[2];
		
		try {
			String answer = "";
			int accountId = Account.getAccountIdByToken(token);
			
			if(type.matches("\\d+")) {
				for(Receipt receipt : Receipt.getAllReceiptsWithAccount(accountId)) {
					if(receipt.receiptId == Integer.parseInt(type)) {
						return receipt.convertToJSONObject() + "";
					}
				}
				
				return "invalid receipt id";
			}
			
			switch(type) {
			case "*":
				for(Receipt receipt : Receipt.getAllReceiptsWithAccount(accountId)) {
					answer += receipt.convertToJSONObject();
					answer += "*";
				}
				break;
			case "+":
				for(Receipt receipt : Receipt.getAllReceiptsWithDestinationAccount(accountId)) {
					answer += receipt.convertToJSONObject();
					answer += "*";
				}
				break;
			case "-":
				for(Receipt receipt : Receipt.getAllReceiptsWithSourceAccount(accountId)) {
					answer += receipt.convertToJSONObject();
					answer += "*";
				}
				break;
				
			}
			
			return answer;
		} catch (ExpiredToken e) {
			return "token expired";
		} catch (InvalidToken e) {
			return "token is invalid";
		}
	}
	
	public static String pay(String[] commandParts) {
		
		int receiptId;
		Receipt receipt;
		
		try {
			receiptId = Integer.parseInt(commandParts[1]);
			if(receiptId > Receipt.allReceipts.size()) {
				throw new Exception();
			}
			
		} catch(Exception e) {
			return "invalid receipt id";
		}
		
		if((receipt = Receipt.allReceipts.get(receiptId - 1)).payStatus) {
			return "receipt is paid before";
		} else {
			try {
				receipt.pay();
			} catch(NotEnoughMoneyInSourceAccount e) {
				return "source account does not have enough money";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "done successfully";
	}
	
	public static String getBalance(String[] commandParts) {
		String token = commandParts[1];
		try {
			Account account = Account.allAccounts.get(Account.getAccountIdByToken(token) - 1);
			return account.money + "";
		} catch (ExpiredToken e) {
			return "token expired";
		} catch (InvalidToken e) {
			return "token is invalid";
		}
	}
	
	public static String generateToken(Account account) {
		Random random = new Random();
		Token token = new Token( random.nextInt(1000) + "");
		account.addToken(token);
		return token.token;
	}

}