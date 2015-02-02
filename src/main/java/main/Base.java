package main;

import java.util.List;

import common.TweetUtils;

import twitter4j.TwitterException;


/**
 * @author shiorin
 *
 */
public class Base {
	public static void main(String[] args) {
		// TODO 最新のツイートのIDを取得し、それ以降のツイートを取得
		try {
			// 最新のツイートを取得
			System.out.println(TweetUtils.getLastTweet());

			// Botの発言を収集
			List<String> tweetList = TweetUtils.searchTweet("#おしりんbot");
			for (String tweet : tweetList) {
				// utils.tweet(tweet);
				System.out.println(tweet);
			}
		} catch (TwitterException e) {
			System.err.println("ツイート失敗：" + e.getMessage());
		}
	}
}