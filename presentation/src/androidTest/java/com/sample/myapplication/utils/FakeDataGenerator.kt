package com.sample.myapplication.utils

import com.sample.domain.model.Ad
import java.util.*

object FakeDataGenerator {

    fun getFakeAds(): List<Ad> {
        val ads = mutableListOf<Ad>()
        for (i in 1 until 6) {
            ads.add(
                Ad(
                    uid = UUID.randomUUID().toString(),
                    name = "Fake Ad $i",
                    price = "AED ${i * 100}",
                    createdAt = Calendar.getInstance().time,
                    imageUrls = listOf("https://demo-app-photos-45687895456123.s3.amazonaws.com/9355183956e3445e89735d877b798689?AWSAccessKeyId=ASIASV3YI6A43NKWD7EP&Signature=ZUxuqdtqpV9XGCFLJUjAWLRkG0k%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEAQaCXVzLWVhc3QtMSJHMEUCIQDOfI3D6rVkfg55fCZUckp46d7fEoADaZqsY67JcqlsmQIgftzcjd62CxEdnwXTMLYQdfyRHTna3m9JcP6FgvQodekqlAIIfRADGgwxODQzOTg5NjY4NDEiDCSB3uE9FFrcKTcyhyrxAQK%2BLQvTpSoZbcRgywyYoiElLpNUjeOuhGkbkeg33cKVzeIFg7kQAqV4APYUvbjQYqeATUXkOe5bNJVD0qh%2FoSwOMC9f3VyAX2h7vi9%2FILlTRNYftmTW5YoWiIx4t6rCIBa1VMyEtzUxUPxq1e%2B0BXntp%2BpN8nXjRdkLZUXNeO6Q5Bjz3w8qRYU7kvgOEvj5cUmWyQMgWvJXWeXlx5EiYRK74vkAYo94pAmMbY0xmrJgcHeU17O%2F14RZvHlCoAUUE%2FkZWp4lcvMTd8k3TML4I7148PtT9kPhTLP3Dp9iQk4GFFWGcbEim9RvAFSod%2FDJFAMw%2Bs%2FDkQY6mgHBTPAFk8WSpQjRmQlb%2F2tq4qcqM0YC9zs%2Bh%2FuTqw3CVL04VfhzZ%2FzIsB2xe%2F62L2XPyA%2BwLD2LX%2F8sMccb7w%2F7QeDyz3qn47pwOt0uV2HaOuaG1TdumoJHzJbYfusQgnjgoHjMQWIYZW564YMDXYqALatzaheBdzyzVtGc0uEPoVapsNpoL9mR1W3H2s9UBcJmxMWcb42%2BDtlL&Expires=1647375884"),
                    imageUrlsThumbnails = listOf("https://demo-app-photos-45687895456123.s3.amazonaws.com/9355183956e3445e89735d877b798689-thumbnail?AWSAccessKeyId=ASIASV3YI6A43NKWD7EP&Signature=g4LDUjxFEBngnHjtqFA4aiRBnBQ%3D&x-amz-security-token=IQoJb3JpZ2luX2VjEAQaCXVzLWVhc3QtMSJHMEUCIQDOfI3D6rVkfg55fCZUckp46d7fEoADaZqsY67JcqlsmQIgftzcjd62CxEdnwXTMLYQdfyRHTna3m9JcP6FgvQodekqlAIIfRADGgwxODQzOTg5NjY4NDEiDCSB3uE9FFrcKTcyhyrxAQK%2BLQvTpSoZbcRgywyYoiElLpNUjeOuhGkbkeg33cKVzeIFg7kQAqV4APYUvbjQYqeATUXkOe5bNJVD0qh%2FoSwOMC9f3VyAX2h7vi9%2FILlTRNYftmTW5YoWiIx4t6rCIBa1VMyEtzUxUPxq1e%2B0BXntp%2BpN8nXjRdkLZUXNeO6Q5Bjz3w8qRYU7kvgOEvj5cUmWyQMgWvJXWeXlx5EiYRK74vkAYo94pAmMbY0xmrJgcHeU17O%2F14RZvHlCoAUUE%2FkZWp4lcvMTd8k3TML4I7148PtT9kPhTLP3Dp9iQk4GFFWGcbEim9RvAFSod%2FDJFAMw%2Bs%2FDkQY6mgHBTPAFk8WSpQjRmQlb%2F2tq4qcqM0YC9zs%2Bh%2FuTqw3CVL04VfhzZ%2FzIsB2xe%2F62L2XPyA%2BwLD2LX%2F8sMccb7w%2F7QeDyz3qn47pwOt0uV2HaOuaG1TdumoJHzJbYfusQgnjgoHjMQWIYZW564YMDXYqALatzaheBdzyzVtGc0uEPoVapsNpoL9mR1W3H2s9UBcJmxMWcb42%2BDtlL&Expires=1647375884")
                )
            )
        }
        return ads
    }


}