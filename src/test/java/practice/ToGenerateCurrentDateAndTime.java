package practice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ToGenerateCurrentDateAndTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date d = new Date();
		System.out.println(d);
		//String date = d.toString().replace("", "_").replace(":", "_");
		//System.out.println(date);
		SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		System.out.println(sim.format(d));
		Calendar cal = sim.getCalendar();
		cal.add(cal.DAY_OF_MONTH, 30);
		System.out.println(cal.getTime());
		System.out.println(sim.format(cal.getTime()));

	}

}
