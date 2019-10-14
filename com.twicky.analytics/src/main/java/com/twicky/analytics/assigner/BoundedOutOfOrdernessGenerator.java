package com.twicky.analytics.assigner;



import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import com.twicky.dto.TweetDTO;



public class BoundedOutOfOrdernessGenerator implements
		AssignerWithPeriodicWatermarks<TweetDTO> {

	private final long maxOutOfOrderness = 3600;
	private long currentMaxTimestamp;

	@Override
	public long extractTimestamp(TweetDTO fnv_ec,
			long previousElementTimestamp) {
		// Our data have no timezones and in order to be able to translate to
		// millis since epoch that Flink needs, we need to add the timezone.
		long timestamp = 0;//fnv_ec.getDate().getTime();
		currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);
		return timestamp;
	}

	@Override
	public Watermark getCurrentWatermark() {
		// return new Watermark(System.currentTimeMillis() - 500000000);
		return new Watermark(currentMaxTimestamp - maxOutOfOrderness);
	}
}