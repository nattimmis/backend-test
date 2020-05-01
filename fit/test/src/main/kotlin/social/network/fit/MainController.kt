package social.network.fit

import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import social.network.fit.models.Response
import social.network.fit.service.SocialMediaService
import java.util.concurrent.CompletableFuture

/**
 * @MainController contains the "/" root route handler
 * @getSocialMediaUpdates() is the route handler
 * which returns mocked social media data
 */
@Component
@RestController
class MainController(var socialMediaService: SocialMediaService) {


    @GetMapping("/")
    fun getSocialMediaUpdates(): Response {

        val twitterUrl = "https://takehome.io/twitter"
        val fbUrl = "https://takehome.io/facebook"
        val instaUrl = "https://takehome.io/instagram"

        // tweet, status & pictures represent json mapped with the
        // corresponding models
        // the 3 variables technically are of type @CompletableFuture
        val tweet = socialMediaService.fetchTweets(twitterUrl)
        val status = socialMediaService.fetchStatuses(fbUrl)
        val pictures = socialMediaService.fetchPictures(instaUrl)

        // response to be returned
        //  { twitter: [tweets], facebook: [statuses], instagram: [photos] }
        // is the json representation of response
        val response: Response = Response()

        // executing mocked api requests
        CompletableFuture.allOf(tweet, status, pictures).join()

        // extracting specific keys from mocked response
        response.twitter = tweet.get().filter{it != null && !it.tweet.isNullOrBlank()}.map { it.tweet!! }
        response.facebook = status.get().filter{it != null && !it.status.isNullOrBlank()}.map { it.status!! }
        response.instagram = pictures.get().filter{it != null && !it.picture.isNullOrBlank()}.map { it.picture!! }

        return response
    }
}