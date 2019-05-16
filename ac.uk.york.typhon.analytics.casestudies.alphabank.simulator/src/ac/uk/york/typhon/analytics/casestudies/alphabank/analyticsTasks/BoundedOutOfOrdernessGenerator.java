package ac.uk.york.typhon.analytics.casestudies.alphabank.analyticsTasks;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import ac.uk.york.typhon.analytics.casestudies.alphabank.simulator.datatypes.FNC_EV;

public class BoundedOutOfOrdernessGenerator implements AssignerWithPeriodicWatermarks<FNC_EV> { 
	
	private final long maxOutOfOrderness = 3600;
	private long currentMaxTimestamp; 
	
	@Override 
	public long extractTimestamp(FNC_EV fnv_ec, long previousElementTimestamp) { 
		// Our data have no timezones and in order to be able to translate to millis since epoch that Flink needs, we need to add the timezone.
		long timestamp = fnv_ec.getFNC_EV_DT().getTime(); 
		currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp); 
		return timestamp; 
	} 
	@Override 
	public Watermark getCurrentWatermark() {
		//return new Watermark(System.currentTimeMillis() - 500000000);
		return new Watermark(currentMaxTimestamp - maxOutOfOrderness); 
	} 
}