package analytics.utilities;

import java.util.Date;

import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

public class BoundedOutOfOrdernessGeneratorUserSpam implements AssignerWithPeriodicWatermarks<Tuple3<String, String, Date>> { 
	
	private final long maxOutOfOrderness = 1000;
	private long currentMaxTimestamp; 
	
	@Override 
	public long extractTimestamp(Tuple3<String, String, Date> tuple, long previousElementTimestamp) { 
		long timestamp = tuple.f2.getTime(); 
		currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp); 
		return timestamp; 
	} 
	@Override 
	public Watermark getCurrentWatermark() {
		//return new Watermark(System.currentTimeMillis() - 500000000);
		return new Watermark(currentMaxTimestamp - maxOutOfOrderness); 
	} 
}