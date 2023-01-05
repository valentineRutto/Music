package com.valentinerutto.music.data.remote


import com.google.gson.annotations.SerializedName

data class TopAlbumsResponse(
    @SerializedName("feed")
    val feed: Feed?
) {
    data class Feed(
        @SerializedName("author")
        val author: Author?,
        @SerializedName("entry")
        val entry: List<Entry?>?,
        @SerializedName("icon")
        val icon: Icon?,
        @SerializedName("id")
        val id: Id?,
        @SerializedName("link")
        val link: List<Link?>?,
        @SerializedName("rights")
        val rights: Rights?,
        @SerializedName("title")
        val title: Title?,
        @SerializedName("updated")
        val updated: Updated?
    ) {
        data class Author(
            @SerializedName("name")
            val name: Name?,
            @SerializedName("uri")
            val uri: Uri?
        ) {
            data class Name(
                @SerializedName("label")
                val label: String?
            )

            data class Uri(
                @SerializedName("label")
                val label: String?
            )
        }

        data class Entry(
            @SerializedName("category")
            val category: Category?,
            @SerializedName("id")
            val id: Id?,
            @SerializedName("im:artist")
            val imArtist: ImArtist?,
            @SerializedName("im:contentType")
            val imContentType: ImContentType?,
            @SerializedName("im:image")
            val imImage: List<ImImage?>?,
            @SerializedName("im:itemCount")
            val imItemCount: ImItemCount?,
            @SerializedName("im:name")
            val imName: ImName?,
            @SerializedName("im:price")
            val imPrice: ImPrice?,
            @SerializedName("im:releaseDate")
            val imReleaseDate: ImReleaseDate?,
            @SerializedName("link")
            val link: Link?,
            @SerializedName("rights")
            val rights: Rights?,
            @SerializedName("title")
            val title: Title?
        ) {
            data class Category(
                @SerializedName("attributes")
                val attributes: Attributes?
            ) {
                data class Attributes(
                    @SerializedName("im:id")
                    val imId: String?,
                    @SerializedName("label")
                    val label: String?,
                    @SerializedName("scheme")
                    val scheme: String?,
                    @SerializedName("term")
                    val term: String?
                )
            }

            data class Id(
                @SerializedName("attributes")
                val attributes: Attributes?,
                @SerializedName("label")
                val label: String?
            ) {
                data class Attributes(
                    @SerializedName("im:id")
                    val imId: String?
                )
            }

            data class ImArtist(
                @SerializedName("attributes")
                val attributes: Attributes?,
                @SerializedName("label")
                val label: String?
            ) {
                data class Attributes(
                    @SerializedName("href")
                    val href: String?
                )
            }

            data class ImContentType(
                @SerializedName("attributes")
                val attributes: Attributes?,
                @SerializedName("im:contentType")
                val imContentType: ImContentType?
            ) {
                data class Attributes(
                    @SerializedName("label")
                    val label: String?,
                    @SerializedName("term")
                    val term: String?
                )

                data class ImContentType(
                    @SerializedName("attributes")
                    val attributes: Attributes?
                ) {
                    data class Attributes(
                        @SerializedName("label")
                        val label: String?,
                        @SerializedName("term")
                        val term: String?
                    )
                }
            }

            data class ImImage(
                @SerializedName("attributes")
                val attributes: Attributes?,
                @SerializedName("label")
                val label: String?
            ) {
                data class Attributes(
                    @SerializedName("height")
                    val height: String?
                )
            }

            data class ImItemCount(
                @SerializedName("label")
                val label: String?
            )

            data class ImName(
                @SerializedName("label")
                val label: String?
            )

            data class ImPrice(
                @SerializedName("attributes")
                val attributes: Attributes?,
                @SerializedName("label")
                val label: String?
            ) {
                data class Attributes(
                    @SerializedName("amount")
                    val amount: String?,
                    @SerializedName("currency")
                    val currency: String?
                )
            }

            data class ImReleaseDate(
                @SerializedName("attributes")
                val attributes: Attributes?,
                @SerializedName("label")
                val label: String?
            ) {
                data class Attributes(
                    @SerializedName("label")
                    val label: String?
                )
            }

            data class Link(
                @SerializedName("attributes")
                val attributes: Attributes?
            ) {
                data class Attributes(
                    @SerializedName("href")
                    val href: String?,
                    @SerializedName("rel")
                    val rel: String?,
                    @SerializedName("type")
                    val type: String?
                )
            }

            data class Rights(
                @SerializedName("label")
                val label: String?
            )

            data class Title(
                @SerializedName("label")
                val label: String?
            )
        }

        data class Icon(
            @SerializedName("label")
            val label: String?
        )

        data class Id(
            @SerializedName("label")
            val label: String?
        )

        data class Link(
            @SerializedName("attributes")
            val attributes: Attributes?
        ) {
            data class Attributes(
                @SerializedName("href")
                val href: String?,
                @SerializedName("rel")
                val rel: String?,
                @SerializedName("type")
                val type: String?
            )
        }

        data class Rights(
            @SerializedName("label")
            val label: String?
        )

        data class Title(
            @SerializedName("label")
            val label: String?
        )

        data class Updated(
            @SerializedName("label")
            val label: String?
        )
    }
}