package g4dhl;
import org.junit.Assert;
import org.junit.Test;

public class GeneralManagerTest{

    @Test
    public void setGeneralManagerNameTest() {
        GeneralManager generalManager = new GeneralManager();
        generalManager.setGeneralManagerName("Mister Fred");
        Assert.assertEquals("Mister Fred", generalManager.getGeneralManagerName());
    }
}