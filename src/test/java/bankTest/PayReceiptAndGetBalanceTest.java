package bankTest;

import bank.BankServer;
import org.junit.Test;
import static org.junit.Assert.*;

public class PayReceiptAndGetBalanceTest {

	@Test
	public void getInitialMoney () {
		
		String[] accountArg1 = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "ali"
		};
		String accountId1 = BankServer.createAccount(accountArg1);
		
		String[] tokenArg1 = new String[] {
				"", "alisharifi", "ali"
		};
		String token1 = BankServer.getToken(tokenArg1);
		
		assertEquals("1000", BankServer.getBalance(new String[] {
				"", token1
		}));
		
	}
	
	
	@Test
	public void payDeposit() {
		String[] accountArg1 = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "ali"
		};
		String accountId1 = BankServer.createAccount(accountArg1);
		
		String[] tokenArg1 = new String[] {
				"", "alisharifi", "ali"
		};
		String token1 = BankServer.getToken(tokenArg1);
		
		String[] receiptArg1 = new String[] {
				"", token1, "deposit", "200", "-1", accountId1, "should pay successfully"
		};
		
		String receiptId = BankServer.createReceipt(receiptArg1);
		
		String[] payArg1 = new String[] {
				"", receiptId
		};
		
		assertEquals("done successfully", BankServer.pay(payArg1));
		assertEquals("1200", BankServer.getBalance(new String[] {
				"", token1
		}));
	}
	
	@Test
	public void payWithdrawTest() {
		String[] accountArg1 = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "ali"
		};
		String accountId1 = BankServer.createAccount(accountArg1);
		
		String[] tokenArg1 = new String[] {
				"", "alisharifi", "ali"
		};
		String token1 = BankServer.getToken(tokenArg1);
		
		String[] receiptArg1 = new String[] {
				"", token1, "withdraw", "200", accountId1, "-1", "should pay successfully"
		};
		
		String receiptId1 = BankServer.createReceipt(receiptArg1);
		
		String[] payArg1 = new String[] {
				"", receiptId1
		};
		
		assertEquals("done successfully", BankServer.pay(payArg1));
		assertEquals("800", BankServer.getBalance(new String[] {
				"", token1
		}));
		
		String[] receiptArg2 = new String[] {
				"", token1, "withdraw", "900", accountId1, "-1", "should not pay successfully"
		};
		
		String receiptId2 = BankServer.createReceipt(receiptArg2);

		String[] payArg2 = new String[] {
				"", receiptId2
		};
		
		assertEquals("source account does not have enough money", BankServer.pay(payArg2));
		assertEquals("800", BankServer.getBalance(new String[] {
				"", token1
		}));
	}
	
}
