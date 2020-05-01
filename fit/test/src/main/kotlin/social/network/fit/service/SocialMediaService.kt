package social.network.fit.service

import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import social.network.fit.models.Picture
import social.network.fit.models.Status
import social.network.fit.models.Tweet
import java.util.concurrent.CompletableFuture

/**
 * Fetches data asynchronously from the mocked apis
 * every method contains error handling in case if response
 * doesn't comply with the expected one
 * an empty array is returned if any error occurs while matching the json from mocked apis
 */

@EnableAsync
@Service
class SocialMediaService {


    @Async
    fun fetchTweets(url: String): CompletableFuture<List<Tweet>> {

        // an empty array is returned if any error occurs while matching the json from mocked apis
        var tweets = emptyList<Tweet>()
        try {
            tweets = RestTemplate().getForObject(url, Array<Tweet>::class.java)!!.toList()
        } catch (e: Exception) {
        }


        return CompletableFuture.completedFuture(tweets)
    }

    @Async
    fun fetchStatuses(url: String): CompletableFuture<List<Status>> {

        var statuses = emptyList<Status>()
        try {
            statuses = RestTemplate().getForObject(url, Array<Status>::class.java)!!.toList()
        } catch (e: Exception) {
        }

        return CompletableFuture.completedFuture(statuses)
    }

    @Async
    fun fetchPictures(url: String): CompletableFuture<List<Picture>> {

        var pictures = emptyList<Picture>()
        try {
            pictures = RestTemplate().getForObject(url, Array<Picture>::class.java)!!.toList()
        } catch (e: Exception) {
        }

        return CompletableFuture.completedFuture(pictures)
    }

}