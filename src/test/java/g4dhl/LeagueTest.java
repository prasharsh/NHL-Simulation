//package g4dhl;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//public class LeagueTest {
//
//    @Test
//    public void getLeagueNameTest(){
//        League league = new League();
//        league.setLeagueName("Dalhousie Hockey League");
//        Assert.assertEquals("Dalhousie Hockey League", league.getLeagueName());
//    }
//
//    @Test
//    public void setConferencesTest(){
//        League league = new League();
//        ArrayList<IConference> conferences = new ArrayList<>();
//        league.setConferences(conferences);
//        Assert.assertEquals(conferences, league.getConferences());
//    }
//
//    @Test
//    public void setFreeAgentsTest(){
//        League league = new League();
//        ArrayList<IFreeAgent> freeAgents = new ArrayList<>();
//        league.setFreeAgents(freeAgents);
//        Assert.assertEquals(freeAgents, league.getFreeAgents());
//    }
//}
