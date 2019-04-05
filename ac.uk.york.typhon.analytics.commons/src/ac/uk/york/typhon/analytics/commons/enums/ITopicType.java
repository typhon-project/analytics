package ac.uk.york.typhon.analytics.commons.enums;

public interface ITopicType {

	public default String getLabel(){
		return ((Enum<?>)this).name().toLowerCase();
		
	}
}