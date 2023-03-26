package kafka_tests;

/*
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class AnomalyPointGenerator {

	public AnomalyPointGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public static String GetTimeUTC()
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(date);
	}
	
	public static String GetTimeUTCWithOffset(int minutes)
	{
		Date date = new Date();
		if (date.getMinutes() >= minutes)
			date.setMinutes(date.getMinutes() - minutes);
		else {
			date.setHours(date.getHours() - 1);
			date.setMinutes(date.getMinutes() + 60 - minutes);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		return sdf.format(date);
	}
	
	
	
	public static void Build_0Anomaly(ArrayList<AnomalyPoint> points) {
		
		for (int i = 480; i > 0; i--)
			points.add(new AnomalyPoint(GetTimeUTCWithOffset(i), 125, 3000));
	}
	
	public static void Build_1Anomaly(ArrayList<AnomalyPoint> points) {
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(29), 125, 3000));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(28), 125, 3502));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(27), 125, 4003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(26), 125, 4504));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(25), 125, 5005));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(24), 125, 7000));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(23), 125, 7100));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(22), 125, 17400));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(21), 125, 18000));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(20), 125, 18600));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(19), 125, 19200));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(18), 125, 29800));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(17), 125, 30500));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(16), 125, 42000));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(15), 125, 41500));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(14), 125, 30900));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(13), 125, 19000));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(12), 125, 11100));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(11), 125, 7200));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(10), 125, 4900));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(9), 125, 2105));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(8), 125, 1304));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(7), 125, 503));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(6), 125, 372));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(5), 125, 250));
	}
	
	public static void Build_2Anomaly(ArrayList<AnomalyPoint> points) {
		points.add(new AnomalyPoint(40000, GetTimeUTCWithOffset(29), 125, 3000));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(28), 125, 3502));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(27), 125, 4003));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(26), 125, 4504));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(25), 125, 5005));
		
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(24), 125, 7000));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(23), 125, 7100));
		
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(22), 125, 17400));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(21), 125, 18000));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(20), 125, 18600));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(19), 125, 19200));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(18), 125, 29800));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(17), 125, 30500));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(16), 125, 42000));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(15), 125, 41500));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(14), 125, 30900));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(13), 125, 19000));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(12), 125, 11100));
		
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(11), 125, 7200));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(10), 125, 4900));
		
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(9), 125, 2105));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(8), 125, 1304));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(7), 125, 503));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(6), 125, 372));
		points.add(new AnomalyPoint(40000,GetTimeUTCWithOffset(5), 125, 250));
	}
	
	public static void Build_1Anomaly_2PointUnderProfile(ArrayList<AnomalyPoint> points) {
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(29), 125, 5000));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(28), 125, 5002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(27), 125, 5003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(26), 125, 5004));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(25), 125, 7000));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(24), 125, 7050));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(23), 125, 7100));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(22), 125, 7200));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(21), 125, 7300));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(20), 125, 7400));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(19), 125, 7500));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(18), 125, 7600));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(17), 125, 7700));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(16), 125, 7600));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(15), 125, 5004));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(14), 125, 5003));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(13), 125, 7300));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(12), 125, 7200));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(11), 125, 7100));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(10), 125, 7050));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(9),  125, 7000));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(8),  125, 5004));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(7),  125, 5003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(6),  125, 5002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(5),  125, 5001));
	}

	public static void Build_1Anomaly_2x2PointUnderProfile(ArrayList<AnomalyPoint> points) {
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(29), 125, 5000));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(28), 125, 5002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(27), 125, 5003));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(26), 125, 7000));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(25), 125, 7025));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(24), 125, 7050));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(23), 125, 7100));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(22), 125, 7200));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(21), 125, 4999));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(20), 125, 4999));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(19), 125, 7500));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(18), 125, 7600));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(17), 125, 7700));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(16), 125, 7600));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(15), 125, 7500));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(14), 125, 4999));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(13), 125, 4999));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(12), 125, 7200));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(11), 125, 7100));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(10), 125, 7050));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(9),  125, 7025));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(8),  125, 7000));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(7),  125, 5003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(6),  125, 5002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(5),  125, 5001));
	}
	
	public static void Build_1Anomaly_1x3PointUnderProfile(ArrayList<AnomalyPoint> points) {
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(29), 125, 5000));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(28), 125, 5002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(27), 125, 5003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(26), 125, 5004));
		

		points.add(new AnomalyPoint(GetTimeUTCWithOffset(25), 125, 7025));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(24), 125, 7050));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(23), 125, 7100));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(22), 125, 4999));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(21), 125, 7200));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(20), 125, 7400));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(19), 125, 7500));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(18), 125, 7600));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(17), 125, 4999));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(16), 125, 7700));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(15), 125, 7500));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(14), 125, 7400));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(13), 125, 7300));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(12), 125, 4999));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(11), 125, 7200));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(10), 125, 7100));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(9),  125, 7000));

		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(8),  125, 5004));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(7),  125, 5003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(6),  125, 5002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(5),  125, 5001));
	}
	
	public static void Build_5Anomaly_2UnitChecks(ArrayList<AnomalyPoint> points) {
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(29), 125, 5000));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(28), 123, 5002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(27), 124, 5003));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(26), 119, 7001));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(25), 127, 7002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(24), 125, 7003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(23), 201, 7003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(22), 213, 7002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(21), 243, 7001));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(20), 266, 5003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(19), 249, 5002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(18), 235, 5003));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(17), 221, 7001));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(16), 125, 7002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(15), 114, 7003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(14), 125, 7003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(13), 135, 7002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(12), 211, 7001));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(11), 295, 5003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(10), 354, 5002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(9), 395, 5003));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(8), 418, 7001));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(7), 351, 7002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(6), 289, 7003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(5), 239, 7002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(4), 125, 7001));
		
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(3), 125, 5003));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(2), 104, 5002));
		points.add(new AnomalyPoint(GetTimeUTCWithOffset(1), 125, 5001));
	}
}
*/
