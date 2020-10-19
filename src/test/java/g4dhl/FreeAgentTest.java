package g4dhl;

import org.junit.Assert;
import org.junit.Test;

public class FreeAgentTest {

    @Test
    public void setFreeAgentWithEmptyFreeAgentNameTest(){
        FreeAgent freeAgent = new FreeAgent();
        Assert.assertFalse("Free Agent name cannot be empty", freeAgent.setFreeAgentName(""));
    }

    @Test
    public void setFreeAgentWithEmptyFreeAgentPositionTest(){
        FreeAgent freeAgent = new FreeAgent();
        Assert.assertFalse("Free Agent position cannot be empty", freeAgent.setFreeAgentPosition(""));
    }

    @Test
    public void setFreeAgentWithNullFreeAgentNameTest(){
        FreeAgent freeAgent = new FreeAgent();
        Assert.assertFalse("Free Agent name cannot be null", freeAgent.setFreeAgentName(null));
    }

    @Test
    public void setFreeAgentWithNullFreeAgentPositionTest(){
        FreeAgent freeAgent = new FreeAgent();
        Assert.assertFalse("Free Agent position cannot be null", freeAgent.setFreeAgentPosition(null));
    }
}


