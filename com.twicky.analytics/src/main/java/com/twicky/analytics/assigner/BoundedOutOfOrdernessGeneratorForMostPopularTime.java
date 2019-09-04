package com.twicky.analytics.assigner;



import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import com.twicky.dto.TweetDTO;

import ac.york.typhon.analytics.commons.datatypes.events.Event;
import ac.york.typhon.analytics.commons.datatypes.events.PostEvent;
import ac.york.typhon.analytics.commons.datatypes.events.PreEvent;



public class BoundedOutOfOrdernessGeneratorForMostPopularTime implements
		AssignerWithPeriodicWatermarks<Event> {

	private final long maxOutOfOrderness = 3600;
	private long currentMaxTimestamp;

	@Override
	public long extractTimestamp(Event element,
			long previousElementTimestamp) {
		long timestamp = ((PostEvent) element).getPreEvent().getQueryTime().getTime();
		currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);
		return timestamp;
	}

	@Override
	public Watermark getCurrentWatermark() {
		// return new Watermark(System.currentTimeMillis() - 500000000);
		return new Watermark(currentMaxTimestamp - maxOutOfOrderness);
	}
}