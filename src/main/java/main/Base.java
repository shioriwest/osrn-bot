package main;

import java.util.List;

import org.apache.log4j.Logger;

import twitter4j.TwitterException;

import common.TweetUtils;

/**
 * @author shiorin
 *
 */
public class Base {
	private static final Logger logger = Logger.getLogger(Base.class);

	public static void main(String[] args) {
		// TODO 最新のツイートのIDを取得し、それ以降のツイートを取得
		try {
			// 最新のツイートを取得
			System.out.println(TweetUtils.getLastTweet());

			// Botの発言を収集
			List<String> tweetList = TweetUtils.searchTweet("#おしりんbot");
			for (String tweet : tweetList) {
				// TweetUtils.tweet(tweet);
				System.out.println(tweet);
			}
		} catch (TwitterException e) {
			logger.debug("ツイート失敗：" + e.getMessage());
		}
	}
}