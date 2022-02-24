package com.adrc95.marvelappsample.data.server

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDataWrapper(
    @SerialName("code")
    val code: Int, //The HTTP status code of the returned result.
    @SerialName("status")
    val status: String, //A string description of the call status.
    @SerialName("copyright")
    val copyright: String, //The copyright notice for the returned result.
    @SerialName("attributionText")
    val attributionText : String, //The attribution notice for this result. Please display either this notice or the contents of the attributionHTML field on all screens which contain data from the Marvel Comics API.
    @SerialName("attributionHTML")
    val attributionHTML : String, //An HTML representation of the attribution notice for this result. Please display either this notice or the contents of the attributionText field on all screens which contain data from the Marvel Comics API.
    @SerialName("data")
    val characterDataContainer : CharacterDataContainer, //The results returned by the call.
    @SerialName("etag")
    val etag : String //A digest value of the content returned by the call.
)

@Serializable
data class CharacterDataContainer(
    @SerialName("offset")
    val offset: Int, //The requested offset (number of skipped results) of the call.
    @SerialName("limit")
    val limit: Int,  //The requested result limit.
    @SerialName("total")
    val total: Int,  //The total number of resources available given the current filter set.
    @SerialName("count")
    val count: Int,  //The total number of results returned by this call.
    @SerialName("results")
    val results: List<Character> //The list of characters returned by the call.
)

@Serializable
data class Character(
    @SerialName("id")
    val id: Long, //The unique ID of the character resource.
    @SerialName("name")
    val name: String, //The name of the character.
    @SerialName("description")
    val description: String, //A short bio or description of the character.
    @SerialName("modified")
    val modified: String, //The date the resource was most recently modified.
    @SerialName("resourceURI")
    val uri: String, //The canonical URL identifier for this resource.
    @SerialName("urls")
    val urls: List<Url>, //A set of public web site URLs for the resource.
    @SerialName("thumbnail")
    val thumbnail: Image //The representative image for this character.
    /*@SerialName("comics")
    val comics: List<ComicList> //The representative image for this character.*/
)

@Serializable
data class Url(
    @SerialName("type")
    val type: String, //A text identifier for the URL.
    @SerialName("url")
    val url: String //A full URL (including scheme, domain, and path).
)

@Serializable
data class Image(
    @SerialName("path")
    val path: String, //The directory path of to the image.
    @SerialName("extension")
    val extension: String //The file extension for the image.
)

@Serializable
data class ComicList(
    @SerialName("available")
    val available: Int, //The number of total available issues in this list. Will always be greater than or equal to the "returned" value.
    @SerialName("returned")
    val returned: Int, //The number of issues returned in this collection (up to 20).
    @SerialName("collectionURI")
    val uri: String, //The path to the full list of issues in this collection.
    @SerialName("items")
    val comics: List<ComicSummary> //The list of returned issues in this collection.
)

@Serializable
data class ComicSummary(
    @SerialName("resourceURI")
    val uri: String, //The path to the individual comic resource.
    @SerialName("name")
    val name: String, //The canonical name of the comic.
)
