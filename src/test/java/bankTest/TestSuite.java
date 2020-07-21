package bankTest;


import org.junit.runner.*;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses ({
	CreateAccountTest.class,
	CreateReceiptTest.class,
	GeneratingTokenTest.class,
	GetTransactionsTest.class,
	PayReceiptAndGetBalanceTest.class
})

public class TestSuite {
	
}
