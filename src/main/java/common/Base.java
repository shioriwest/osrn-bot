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

/**
 * @author shiorin
 *
 */
public class Base {
	// おしりんBot用トークン
	private static final String consumerKey = "oPkGPmoh7no8zlsBSVATlmreM";
	private static final String consumerSecret = "jAHncHD7ZuaGuIPdyqyW21bdMs6BbxWQjD9K2VBhk44zSChPpe";
	private static final String accessToken = "1593356700-H3ZOnZOBSpj6shd8IgZqX3Rz1Uj2yb65akvuspo";
	private static final String accessTokenSecret = "sdMJsovcEpuXdAHFsUwUOSY4QDWtw8G4VtLTilMt6mWkD";

	public static void main(String[] args) {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(accessToken,
			accessTokenSecret));

		// TODO 最新のツイートのIDを取得し、それ以降のツイートを取得

		Utils utils = new Utils(twitter);
		try {
			// 最新のツイートを取得
			System.out.println(utils.getLastTweet());

			// Botの発言を収集
			List<String> tweetList = utils.searchTweet("#おしりんbot");
			for (String tweet : tweetList) {
				// utils.tweet(tweet);
				System.out.println(tweet);
			}
		} catch (TwitterException e) {
			System.err.println("ツイート失敗：" + e.getMessage());
		}
	}

	public static class Utils {
		Twitter twitter;

		public Utils(Twitter twitter) {
			this.twitter = twitter;
		}

		/**
		 * つぶやく。
		 */
		public void tweet(String murmur) throws TwitterException {
			twitter.updateStatus(murmur);
		}

		/**
		 * 最新のツイートを取得
		 */
		public String getLastTweet() throws TwitterException {
			User user = twitter.verifyCredentials();
			Status st = user.getStatus();

			return st.getText();
		}

		/**
		 * 特定の文字列を含むツイートを検索し、リストで返却。<br>
		 * 
		 */
		public List<String> searchTweet(String searchWord) throws TwitterException {
			Query query = new Query(searchWord);
			QueryResult result = twitter.search(query);

			List<String> tweetList = new ArrayList<String>();
			for (Status status : result.getTweets()) {
				String murmur = status.getText() + " @" + status.getUser().getScreenName();

				// Bot自身の発言は排除する。
				if (murmur.contains("@osrn_bot")) {
					continue;
				}
				// // タグを検索した場合は、タグの部分を排除。
				// if (searchWord.contains("#")) {
				// murmur = murmur.replaceFirst(searchWord, "");
				// }
				tweetList.add(murmur);
			}
			return tweetList;
		}
	}
}