package com.datamodel.leaguedatamodel;
import java.sql.Date;

public interface ITimeConcept {

    Date getNextDate(Date currentDate);
}
