package social.network.fit.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = false)
class Response {
    var twitter: List<String> = emptyList()
    var facebook: List<String> = emptyList()
    var instagram: List<String> = emptyList()
}