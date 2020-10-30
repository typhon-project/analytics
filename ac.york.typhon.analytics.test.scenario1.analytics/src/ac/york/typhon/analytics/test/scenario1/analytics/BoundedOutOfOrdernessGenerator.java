package ac.york.typhon.analytics.test.scenario1.analytics;

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import ac.york.typhon.analytics.commons.datatypes.events.DeserializedPostEvent;

public class BoundedOutOfOrdernessGenerator implements AssignerWithPeriodicWatermarks<DeserializedPostEvent> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 700855007705555226L;
	private final long maxOutOfOrderness = 3600;
	private long currentMaxTimestamp;

	public long extractTimestamp(DeserializedPostEvent event, long previousElementTimestamp) {
		// Our data have no time zones and in order to be able to translate to
		// milliseconds since epoch that Flink needs, we need to add the time zone.
		long timestamp = event.getStartTime().getTime();
		currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);
		return timestamp;
	}

	public Watermark getCurrentWatermark() {
		// return new Watermark(System.currentTimeMillis() - 500000000);
		return new Watermark(currentMaxTimestamp - maxOutOfOrderness);
	}
}