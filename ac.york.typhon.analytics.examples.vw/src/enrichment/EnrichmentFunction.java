package enrichment;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.RichCoFlatMapFunction;
import org.apache.flink.util.Collector;

import ac.york.typhon.analytics.examples.vw.datatypes.ESP;
import ac.york.typhon.analytics.examples.vw.datatypes.VehicleMetadata;

public class EnrichmentFunction extends RichCoFlatMapFunction<ESP, VehicleMetadata, Tuple2<ESP, VehicleMetadata>> {

		private ValueState<ESP> espState;
		private ValueState<VehicleMetadata> metadataState;
		
		@Override
		public void open(Configuration config) throws Exception {
			espState = getRuntimeContext()
					.getState(new ValueStateDescriptor<>("ride", ESP.class));
			metadataState = getRuntimeContext()
					.getState(new ValueStateDescriptor<>("fare", VehicleMetadata.class));
		}

		@Override
		public void flatMap1(ESP esp, Collector<Tuple2<ESP, VehicleMetadata>> out) throws Exception {
			if (metadataState.value() == null) {
				espState.update(esp);
			} else {
				out.collect(new Tuple2<ESP, VehicleMetadata>(esp, metadataState.value()));
				metadataState.clear();
			}
		}

		@Override
		public void flatMap2(VehicleMetadata metadata, Collector<Tuple2<ESP, VehicleMetadata>> out) throws Exception {
			if (espState.value() == null) {
				metadataState.update(metadata);
			} else {
				out.collect(new Tuple2<ESP, VehicleMetadata>(espState.value(), metadata));
				espState.clear();
			}
		}
	}