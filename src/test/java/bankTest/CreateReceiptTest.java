package bankTest;

import bank.BankServer;
import org.junit.Test;
import static org.junit.Assert.*;

public class CreateReceiptTest {
	
	@Test
	public void invalidReceiptType() {
		String[] arg = new String[] {
			"", "78", "pardakht", "89", "980", "870", "mobark bashe!"	
		};
		
		assertEquals("invalid receipt type", BankServer.createReceipt(arg));
	}
	
	
	@Test
	public void invalidMoney() {
		String[] arg = new String[] {
				"", "78", "deposit", "-1", "980", "870", "mobark bashe!"	
		};
		assertEquals("invalid money", BankServer.createReceipt(arg));
	}
	
	@Test
	public void invalidToken() {
		String[] arg1 = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "ali"
		};
		
		String[] arg2 = new String[] {
				"", "hasan", "hosseini", "hasanHosseini", "55", "55"
		};
		
		String[] arg3 = new String[] {
				"", "78", "deposit", "5", "-1", "1", "mobark bashe!"	
		};
		
		String[] arg4 = new String[] {
				"", "78", "withdraw", "5", "1", "-1", "mobark bashe!"	
		};
		BankServer.createAccount(arg1);
		BankServer.createAccount(arg2);
		assertEquals("token is invalid", BankServer.createReceipt(arg3));
		assertEquals("token is invalid", BankServer.createReceipt(arg4));
	}
	
	
	@Test
	public void validToken() {
		String[] arg1 = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "ali"
		};
		BankServer.createAccount(arg1);
		
		String[] arg2 = new String[] {
				"", "hasan", "hosseini", "hasanHosseini", "55", "55"
		};
		BankServer.createAccount(arg2);
		
		String token = BankServer.getToken(new String[] {
				"", "alisharifi", "ali"
		});
		
		String[] arg3 = new String[] {
				"", token, "deposit", "1000", "-1", "1", "mobark bashe!"	
		};
		
		assertEquals("1", BankServer.createReceipt(arg3));
	}
	
	
	@Test 
	public void invalidIds() {
		String[] arg1 = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "ali"
		};
		BankServer.createAccount(arg1);
		
		String[] arg2 = new String[] {
				"", "hasan", "hosseini", "hasanHosseini", "55", "55"
		};
		BankServer.createAccount(arg2);
		
		String token = BankServer.getToken(new String[] {
				"", "alisharifi", "ali"
		});
		
		String[] arg3 = new String[] {
				"", token, "deposit", "1000", "-1", "3", "mobark bashe!"	
		};
		
		assertEquals("dest account id is invalid", BankServer.createReceipt(arg3));
	}
}
