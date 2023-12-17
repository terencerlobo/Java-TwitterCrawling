package twitterFeed;

import twitter4j.Status;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterFeedApp {
	public static void main(String[] args) {
        // Twitter API credentials
        String consumerKey = "ljewkNEjUU6r9QzxDtQLz5Gf2";
        String consumerSecret = "ZES7q34K1lvcmxM9LXiud7jpWzGkuYK1oR1GBqcGgjIAE75ohT";
        String accessToken = "3245655800-ielBtm2pgfWsHHAV65wl1eWpbN9oCDBLK3qcUYC";
        String accessTokenSecret = "5ob2KgmsBe14ERkqCHKziFScR2vluQbw6bBf2HInw2YVw";

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey(consumerKey)
          .setOAuthConsumerSecret(consumerSecret)
          .setOAuthAccessToken(accessToken)
          .setOAuthAccessTokenSecret(accessTokenSecret);

        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        try {
            Query query = new Query("Sports"); 
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName() + ": " + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
        }
    }
}
