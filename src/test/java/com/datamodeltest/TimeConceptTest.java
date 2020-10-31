package com.datamodeltest;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;

import com.datamodel.TimeConcept;
import com.datamodel.leaguedatamodel.Game;


public class TimeConceptTest {

    @Test
    public void getNextDateTest(){
       Game game = MockGame.mockGame(2,2,3);
       TimeConcept timeConcept = new TimeConcept();
       Calendar cal = Calendar.getInstance();
       cal.setTime(game.getLeagues().get(0).getCurrentDate());
       cal.add(Calendar.DAY_OF_YEAR, 1);
       cal.set(Calendar.HOUR_OF_DAY, 0);
       cal.set(Calendar.MINUTE, 0);
       cal.set(Calendar.SECOND, 0);
       cal.set(Calendar.MILLISECOND, 0);
       Date nextDay = new java.sql.Date(cal.getTimeInMillis());
       assertEquals(timeConcept.getNextDate(game.getLeagues().get(0).getCurrentDate()), nextDay);
    }
}
