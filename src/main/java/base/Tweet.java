package base;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class Tweet {
	private static final String consumerKey = "oPkGPmoh7no8zlsBSVATlmreM";
	private static final String consumerSecret = "jAHncHD7ZuaGuIPdyqyW21bdMs6BbxWQjD9K2VBhk44zSChPpe";
	private static final String accessToken = "1593356700-H3ZOnZOBSpj6shd8IgZqX3Rz1Uj2yb65akvuspo";
	private static final String accessTokenSecret = "sdMJsovcEpuXdAHFsUwUOSY4QDWtw8G4VtLTilMt6mWkD";

	public static void main(String[] args) {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(consumerKey, consumerSecret);
		twitter.setOAuthAccessToken(new AccessToken(accessToken,
				accessTokenSecret));
		try {
			Query query = new Query("#おしりんbot");
			QueryResult result = twitter.search(query);
			
			for (Status status : result.getTweets()) {
				twitter.updateStatus(status.getText() + " @"
						+ status.getUser().getScreenName());
			}
		} catch (TwitterException e) {
			System.err.println("ツイート失敗：" + e.getMessage());
		}
	}
}