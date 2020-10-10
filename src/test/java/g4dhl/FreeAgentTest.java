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

    @Test
    public void isFreeAgentCaptainTest(){
        FreeAgent freeAgent = new FreeAgent();
        freeAgent.setFreeAgentName("Agent One");
        freeAgent.setFreeAgentPosition("forward");
        freeAgent.setFreeAgentCaptain(true);
        Assert.assertTrue("Agent Name should be a captain", freeAgent.isFreeAgentCaptain());
    }

    @Test
    public void setFreeAgentCaptainTrueTest(){
        FreeAgent freeAgent = new FreeAgent();
        Assert.assertTrue("Agent should be captain", freeAgent.setFreeAgentCaptain(true));
    }

    @Test
    public void setFreeAgentCaptainFalseTest(){
        FreeAgent freeAgent = new FreeAgent();
        Assert.assertTrue("Agent should not be a captain", freeAgent.setFreeAgentCaptain(false));
    }
}


