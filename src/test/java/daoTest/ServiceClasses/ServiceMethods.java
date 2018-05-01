package daoTest.ServiceClasses;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class ServiceMethods
{

	public static String getCurrentTimeAsString()
	{
		Calendar  calender         = Calendar.getInstance();
		Date      now              = calender.getTime();
		Timestamp currentTimeStamp = new Timestamp(now.getTime());
		return currentTimeStamp.toString();
	}
}
