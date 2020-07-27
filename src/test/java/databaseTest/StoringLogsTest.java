package databaseTest;

import Server.Model.BuyLog;
import Server.Model.Good;
import Server.Model.Logs;
import Server.Model.SellLog;
import org.junit.Test;

import java.util.Date;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class StoringLogsTest {

    @Test
    public void storingSellLogs() {
        Good good = mock(Good.class);
        doReturn("hello").when(good).getGoodID();
        Logs logs = new SellLog("a", new Date(), 1.2f, 0.6f, good, "alisharifi", "posted");
    }

    @Test
    public void storingBuyLogs() {
        Good good = mock(Good.class);
        doReturn("hello").when(good).getGoodID();
        //Logs logs = new BuyLog("a", new Date(), 1.2f, 0.6f, good, "alisharifi", "posted");
    }

}
