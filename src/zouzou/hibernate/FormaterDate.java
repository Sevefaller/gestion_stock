package zouzou.hibernate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FormaterDate {
	private Date d;
	
	public FormaterDate(String dt){
		SimpleDateFormat fd = new SimpleDateFormat("dd/MM/yy");
		Calendar cd = new GregorianCalendar();
		Date dc = new Date();
		try{
			dc = fd.parse(dt);
		}catch(Exception ew){
            ew.printStackTrace();
        }
		//cd.setTime(dc);
		this.d = dc;
	}

	public Date getD() {
		return d;
	}
}
