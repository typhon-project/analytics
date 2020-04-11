package analytics.utils;

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;

public class PostEventTimeAssigner implements AssignerWithPeriodicWatermarks<PostEvent> {
	
	private final long maxOutOfOrderness = 3600;
	private long currentMaxTimestamp; 
	
	@Override
	public long extractTimestamp(PostEvent arg0, long arg1) {
		long timestamp = arg0.getStartTime().getTime(); 
		currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp); 
		return timestamp; 
	}

	@Override
	public Watermark getCurrentWatermark() {
		return new Watermark(currentMaxTimestamp - maxOutOfOrderness);
	}

}
