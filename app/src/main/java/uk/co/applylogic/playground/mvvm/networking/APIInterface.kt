package uk.co.applylogic.playground.mvvm.networking

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import uk.co.applylogic.playground.mvvm.model.BlogWrapper

/**
 * Created by Golan Shay @golanshy on 26 June,2019
 */
interface APIInterface {
    @GET("/api/feed.json")
    fun getPopularBlogAsync(): Deferred<BlogWrapper>
}