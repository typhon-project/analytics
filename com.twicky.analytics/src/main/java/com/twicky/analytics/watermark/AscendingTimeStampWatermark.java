package com.twicky.analytics.watermark;

import java.sql.Timestamp;

import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;

import com.twicky.dto.TweetDTO;

public class AscendingTimeStampWatermark extends
		AscendingTimestampExtractor<TweetDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public long extractAscendingTimestamp(TweetDTO element) {

		System.out.println(element);

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println("Remember to retrieve time from DTO currently it is now()"+ timestamp);

		return timestamp.getTime();// Timestamp.valueOf(element.getCreatedAt()).getTime();
	}

}
