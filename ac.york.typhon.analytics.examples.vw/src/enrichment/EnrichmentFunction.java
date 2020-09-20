package enrichment;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.RichCoFlatMapFunction;
import org.apache.flink.util.Collector;

import ac.york.typhon.analytics.examples.vw.datatypes.ESP_old;
import ac.york.typhon.analytics.examples.vw.datatypes.VehicleMetadata_old;

public class EnrichmentFunction extends RichCoFlatMapFunction<ESP_old, VehicleMetadata_old, Tuple2<ESP_old, VehicleMetadata_old>> {

		private ValueState<ESP_old> espState;
		private ValueState<VehicleMetadata_old> metadataState;
		
		@Override
		public void open(Configuration config) throws Exception {
			espState = getRuntimeContext()
					.getState(new ValueStateDescriptor<>("ride", ESP_old.class));
			metadataState = getRuntimeContext()
					.getState(new ValueStateDescriptor<>("fare", VehicleMetadata_old.class));
		}

		@Override
		public void flatMap1(ESP_old esp, Collector<Tuple2<ESP_old, VehicleMetadata_old>> out) throws Exception {
			if (metadataState.value() == null) {
				espState.update(esp);
			} else {
				out.collect(new Tuple2<ESP_old, VehicleMetadata_old>(esp, metadataState.value()));
				metadataState.clear();
			}
		}

		@Override
		public void flatMap2(VehicleMetadata_old metadata, Collector<Tuple2<ESP_old, VehicleMetadata_old>> out) throws Exception {
			if (espState.value() == null) {
				metadataState.update(metadata);
			} else {
				out.collect(new Tuple2<ESP_old, VehicleMetadata_old>(espState.value(), metadata));
				espState.clear();
			}
		}
	}