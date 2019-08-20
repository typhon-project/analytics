package com.twicky.analytics.watermark;

import java.sql.Timestamp;

import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import com.twicky.dto.TweetDTO;

public class BoundedOutOfOrdernessWatermark implements
		AssignerWithPeriodicWatermarks<TweetDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final long MAX_OUT_OF_ORDERNESS = 3600;
	private long currentMaxTimestamp;

	@Override
	public long extractTimestamp(TweetDTO element, long previousElementTimestamp) {

		// long timestamp = element.getDate().getTime();
		// currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out
				.println("Remember to retrieve time from DTO currently it is now()"
						+ timestamp);

		return timestamp.getTime();

	}

	@Override
	public Watermark getCurrentWatermark() {

		return new Watermark(currentMaxTimestamp - MAX_OUT_OF_ORDERNESS);
	}

}