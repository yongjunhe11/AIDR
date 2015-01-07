package qa.qcri.aidr.collector.collectors;

import java.util.function.Predicate;

import javax.json.JsonObject;

import qa.qcri.aidr.common.redis.LoadShedder;

public class ShedderFilter implements Predicate<JsonObject> {
	
	private String channel;
	private LoadShedder delegate;
	
	public ShedderFilter(String channel, LoadShedder shedder){
		this.channel = channel;
		this.delegate = shedder;
	}

	@Override
	public boolean test(JsonObject t) {
		return delegate.canProcess(channel);
	}

}
