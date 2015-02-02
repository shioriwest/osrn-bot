package common;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

public class TweetUtils {
	static Twitter twitter;

	private static final String consumerKey = PropertiesUtils.TOKEN.getValue("consumerKey");
	private static final String consumerSecret = PropertiesUtils.TOKEN.getValue("consumerSecret");
	private static final String accessToken = PropertiesUtils.TOKEN.getValue("accessToken");
	private static final String accessTokenSecret = PropertiesUtils.TOKEN.getValue("accessTokenSecret");

	// クラスロード時に一度だけ実行する
	static {
		twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(accessToken, accessTokenSecret));
	}

	/**
	 * つぶやく。
	 */
	public static void tweet(String murmur) throws TwitterException {
		twitter.updateStatus(murmur);
	}

	/**
	 * 最新のツイートを取得
	 */
	public static String getLastTweet() throws TwitterException {
		User user = twitter.verifyCredentials();
		Status st = user.getStatus();

		return st.getText();
	}

	/**
	 * 特定の文字列を含むツイートを検索し、リストで返却。<br>
	 * 
	 */
	public static List<String> searchTweet(String searchWord) throws TwitterException {
		Query query = new Query(searchWord);
		QueryResult result = twitter.search(query);

		List<String> tweetList = new ArrayList<String>();
		for (Status status : result.getTweets()) {
			String murmur = status.getText() + " @" + status.getUser().getScreenName();

			// Bot自身の発言は排除する。
			if (murmur.contains("@osrn_bot")) {
				continue;
			}
			tweetList.add(murmur);
		}
		return tweetList;
	}
}
