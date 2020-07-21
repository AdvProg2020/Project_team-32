package bankTest;

import bank.BankServer;
import org.junit.Test;
import static org.junit.Assert.*;

public class CreateAccountTest {
	
	@Test
	public void wrongRepeat() {
		String[] arg = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "amir"
		};
		assertEquals("passowrds do not match", BankServer.createAccount(arg));
	}
	
	@Test
	public void duplicateUsername() {
		String[] arg1 = new String[] {
				"", "ali", "sharifi", "alisharifi", "ali", "ali"
		};
		
		String[] arg2 = new String[] {
				"", "hasan", "hosseini", "alisharifi", "55", "55"
		};
		
		String[] arg3 = new String[] {
				"", "hasan", "hosseini", "hasanHosseini", "55", "55"
		};
	
		assertEquals("1", BankServer.createAccount(arg1));
		assertEquals("username is not available", BankServer.createAccount(arg2));
		assertEquals("2", BankServer.createAccount(arg3));
	}
}
