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
        String consumerKey = "CONSUMER_KEY";
        String consumerSecret = "CONSUMER_SECRET";
        String accessToken = "ACCESS_TOKEN";
        String accessTokenSecret = "ACCESS_TOKEN_SECRET";

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
