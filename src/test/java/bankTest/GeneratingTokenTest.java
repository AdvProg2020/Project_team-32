package bankTest;

import bank.BankServer;
import org.junit.Test;
import static org.junit.Assert.*;

public class GeneratingTokenTest {
	
	@Test
	public void invalidUsername() {
		String[] arg = new String[] {
			"", "ali", "sharifi"	
		};
		assertEquals("invalid username or password", BankServer.getToken(arg));
	}
	
	@Test
	public void invalidPassword() {
		String[] arg = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "ali"
		};
		BankServer.createAccount(arg);
		
		assertEquals("invalid username or password", BankServer.getToken(new String[] {
			"", "alisharifi", "sharifi"	
		}));
		
	}
	
	@Test
	public void getTokenSuccessfully() {
		String[] arg = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "ali"
		};
		BankServer.createAccount(arg);
		assertNotEquals("invalid username or password", BankServer.getToken(new String[] {
			"", "alisharifi", "ali"	
		}));
	}

}
