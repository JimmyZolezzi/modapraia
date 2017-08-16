import java.util.Calendar;

public class TimeZone {

	public static void main(String[] args) {

		Calendar now = Calendar.getInstance();
		System.out.println(now.getTimeZone());
		System.out.println(now.getTime());
	}
}
