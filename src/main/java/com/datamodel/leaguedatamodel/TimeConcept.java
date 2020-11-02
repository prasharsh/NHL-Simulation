package com.datamodel.leaguedatamodel;

import java.sql.Date;
import java.util.Calendar;

public class TimeConcept {

    public Date getNextDate(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date nextDay = new java.sql.Date(cal.getTimeInMillis());
        return nextDay;
    }

}
