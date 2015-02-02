package main;

import java.util.List;

import org.apache.log4j.Logger;

import twitter4j.Status;
import twitter4j.TwitterException;

import common.TweetUtils;

/**
 * @author shiorin
 *
 */
public class Base {
	private static final Logger logger = Logger.getLogger(Base.class);

	public static void main(String[] args) {
		try {
			// 自TLの最新ツイートを取得
			Status latest = TweetUtils.getLastTweet();

			// タグのついているツイートを検索
			List<Status> statusList = TweetUtils.searchTweet("#おしりんbot");
			int count = 0;
			for (Status st : statusList) {
				// 最新ツイートより投稿時間が新しかったら、ツイート
				if (latest.getCreatedAt().compareTo(st.getCreatedAt()) < 0) {
					String text = st.getText();
					if (!text.startsWith("@")) {
						TweetUtils.tweet(st.getText() + " @" + st.getUser().getScreenName());
						count++;
					}
				}
			}
			logger.info(count + "件ツイートしました。");
		} catch (TwitterException e) {
			logger.debug("ツイート失敗：" + e.getMessage());
		}
	}
}