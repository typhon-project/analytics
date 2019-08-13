package ac.york.typhon.generator.extractors.select.extractor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ac.york.typhon.generator.commons.TwickyConstants;
import ac.york.typhon.generator.extractors.select.SelectExtractor;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.WithItem;

public class TweetSelectExtractor extends SelectExtractor {

	private static final String INVALID_CHARACTERS = "?";
	private HashMap<String, String> fieldValueMap;
	private JsonNode jsonNode;

	public TweetSelectExtractor(String query) throws JSQLParserException {

		super(query);
		// this.fieldValueMap = populateFieldValueMap();

		// remove question marks in prepared statements
		// for (Entry<String, String> entry : this.fieldValueMap.entrySet()) {
		// if (StringUtils.containsOnly(entry.getValue(), INVALID_CHARACTERS)) {
		// entry.setValue(null);
		// }
		// }

		// String escapedJsonString = fieldValueMap
		// .get(TwickyConstants.Table.Tweet.JSON);
		// if (StringUtils.isNoneBlank(escapedJsonString)) {
		//
		// try {
		//
		// String unescapedJsonString = StringEscapeUtils
		// .unescapeJson(escapedJsonString);
		// ObjectMapper objectMapper = new ObjectMapper();
		// jsonNode = objectMapper.readValue(unescapedJsonString,
		// JsonNode.class);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

	}

	public String getBlocked() {

		return null; // fieldValueMap.get(TwickyConstants.Table.Tweet.BLOCKED);

	}

	public String getCreatedAt() {
		String field = null;

		// fieldValueMap
		// .get(TwickyConstants.Table.Tweet.CREATED_AT);
		// if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode,
		// null)) {
		// field = jsonNode.path(TwickyConstants.TwickyJson.CREATED_AT)
		// .asText();
		//
		// }
		return field;

	}

	public String getDiscoveredAt() {
		// return fieldValueMap.get(TwickyConstants.Table.Tweet.DISCOVERED_AT);

		String field = null;

		// fieldValueMap
		// .get(TwickyConstants.Table.Tweet.DISCOVERED_AT);
		// if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode,
		// null)) {
		// field = jsonNode.path(TwickyConstants.TwickyJson.QUOTED_STATUS)
		// .path(TwickyConstants.TwickyJson.CREATED_AT).asText();
		// }
		return field;
	}

	public String getDiscovererScreenName() {
		// return fieldValueMap
		// .get(TwickyConstants.Table.Tweet.DISCOVERER_SCREEN_NAME);

		String field = null;

		// fieldValueMap
		// .get(TwickyConstants.Table.Tweet.DISCOVERER_SCREEN_NAME);
		// if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode,
		// null)) {
		//
		// field = jsonNode.path(TwickyConstants.TwickyJson.QUOTED_STATUS)
		// .path(TwickyConstants.TwickyJson.USER)
		// .path(TwickyConstants.TwickyJson.SCREEN_NAME).asText();
		// }
		return field;

	}

	public String getFavoriteCount() {

		String field = null;

		// fieldValueMap
		// .get(TwickyConstants.Table.Tweet.FAVORITE_COUNT);
		// if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode,
		// null)) {
		// field = jsonNode.path(TwickyConstants.TwickyJson.FAVORITE_COUNT)
		// .asText();
		// }
		return field;

	}

	public String getGreek() {
		return null;
		// fieldValueMap.get(TwickyConstants.Table.Tweet.GREEK);
	}

	public String getID() {

//		Select select;

		// select = (Select)
		// CCJSqlParserUtil.parse("SELECT a,b,c FROM tableName t WHERE t.col = 9 and b=c LIMIT 3, ?");
		//
		// PlainSelect ps = (PlainSelect) select.getSelectBody();
		// System.out.println(ps.getWhere().toString());
		// System.out.println(ps.getSelectItems().get(1).toString());
		// AndExpression e = (AndExpression) ps.getWhere();
		// System.out.println(e.getLeftExpression());

		PlainSelect plainSelect = (PlainSelect) parsedStatement.getSelectBody();
//		System.out.println(plainSelect.getWhere().getASTNode()
//				.jjtGetLastToken().image);
		String id = plainSelect.getWhere().getASTNode().jjtGetLastToken().image;

		return StringUtils.containsOnly(id, INVALID_CHARACTERS) ? null : id;

	}

	public String getJSON() {
		// return fieldValueMap.get(TwickyConstants.Table.Tweet.JSON);
		return null;
	}

	public String getRetweetCount() {

		String field = null;

		// fieldValueMap
		// .get(TwickyConstants.Table.Tweet.RETWEET_COUNT);
		// if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode,
		// null)) {
		// field = jsonNode
		// .findValue(TwickyConstants.TwickyJson.RETWEET_COUNT)
		// .asText();
		// }
		return field;

	}

	public String getText() {

		String field = null;

		// fieldValueMap.get(TwickyConstants.Table.Tweet.TEXT);
		// if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode,
		// null)) {
		// field = jsonNode.findValue(TwickyConstants.TwickyJson.FULL_TEXT)
		// .asText();
		// }
		return field;
	}

	public String getUserScreenName() {

		String field = null;
		// fieldValueMap
		// .get(TwickyConstants.Table.Tweet.USER_SCREEN_NAME);
		// if (StringUtils.isBlank(field) && ObjectUtils.notEqual(jsonNode,
		// null)) {
		//
		// field = jsonNode.path(TwickyConstants.TwickyJson.USER)
		// .path(TwickyConstants.TwickyJson.SCREEN_NAME).asText();
		// }
		return field;
	}

	@Override
	public String toString() {
		// return "TweetUpdateExtractor [fieldValueMap=" + fieldValueMap
		// + ", where ID =" + getID()
		// /* + ", parsedStatement=" + parsedStatement */+ "]";

		return "TweetUpdateExtractor [Blocked=" + getBlocked() + " CreatedAt="
				+ getCreatedAt() + " DiscoveredAt=" + getDiscoveredAt()
				+ " DiscovererScreenName=" + getDiscovererScreenName()
				+ " FavoriteCount=" + getFavoriteCount() + " Greek="
				+ getGreek() + " ID=" + getID() + " RetweetCount="
				+ getRetweetCount() + " Text=" + getText() + " UserScreenName="
				+ getUserScreenName() + "]";

	}

}
