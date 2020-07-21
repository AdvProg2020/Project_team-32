package bankTest;

import bank.BankServer;
import org.junit.Test;

public class GetTransactionsTest {
	
	@Test
	public void allTransactionsTest() {
		
		String[] arg = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "ali"
		};
		
		BankServer.createAccount(arg);
		
		String token = BankServer.getToken(new String[] {
				"", "alisharifi", "ali"	
			});
		
		String[] arg1 = new String[] {
				"", token, "deposit", "1000", "-1", "1", "mobark bashe!"	
		};
		
		BankServer.createReceipt(arg1);
		
		String[] arg2 = new String[] {
				"", token, "*"
		};
		
		System.out.println(BankServer.getTransactions(arg2));
	}
	
	@Test
	public void allTransactionsWithDest() {
		String[] arg = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "ali"
		};
		
		BankServer.createAccount(arg);
		
		String token = BankServer.getToken(new String[] {
				"", "alisharifi", "ali"	
			});
		
		String[] arg1 = new String[] {
				"", token, "deposit", "1000", "-1", "1", "mobark bashe!"	
		};
		
		BankServer.createReceipt(arg1);
		
		String[] arg2 = new String[] {
				"", token, "-"
		};
		
		System.out.println(BankServer.getTransactions(arg2));
	}
	
	
	@Test
	public void allTransactionsWithSource() {
		String[] arg = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "ali"
		};
		
		BankServer.createAccount(arg);
		
		String token = BankServer.getToken(new String[] {
				"", "alisharifi", "ali"	
			});
		
		String[] arg1 = new String[] {
				"", token, "deposit", "1000", "-1", "1", "mobark bashe!"	
		};
		
		BankServer.createReceipt(arg1);
		
		String[] arg2 = new String[] {
				"", token, "+"
		};
		
		System.out.println(BankServer.getTransactions(arg2));
	}
	
	@Test
	public void getTransactionById() {
		
		String[] accountArg1 = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "ali"
		};
		
		String[] accountArg2 = new String[] {
				"", "amir", "sharifi", "amirsharifi", "amir", "amir"
		};
		
		BankServer.createAccount(accountArg1);
		BankServer.createAccount(accountArg2);
		
		String[] tokenArg1 = new String[] {
				"", "alisharifi", "ali"
		};
		
		String[] tokenArg2 = new String[] {
				"", "amirsharifi", "amir"
		};
		
		String token2 = BankServer.getToken(tokenArg2);
		String token1 = BankServer.getToken(tokenArg1);

		String[] receiptArg1 = new String[] {
				"", token1, "deposit", "1000", "-1", "1", "hello world"
		};
		
		String[] receiptArg2 = new String[] {
				"", token2, "move", "100", "2", "1", "bye bye"
		};
		
		BankServer.createReceipt(receiptArg1);
		BankServer.createReceipt(receiptArg2);
		
		String[] getTransactionArg1 = new String[] {
				"", token1, "1"
		};
		
		String[] getTransactionArg2 = new String[] {
				"", token1, "2"
		};
		
		String[] getTransactionArg3 = new String[] {
				"", token1, "+"
		};
		
		String[] getTransactionArg4 = new String[] {
				"", token1, "5"
		};
		
		
		
		System.out.println(BankServer.getTransactions(getTransactionArg1));
		System.out.println(BankServer.getTransactions(getTransactionArg2));
		System.out.println(BankServer.getTransactions(getTransactionArg3));
		System.out.println(BankServer.getTransactions(getTransactionArg4));
	}
	
	

}
