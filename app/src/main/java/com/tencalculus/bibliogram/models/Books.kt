package com.tencalculus.bibliogram.models

data class Books(
        val items: List<Item>,
        val kind: String,
        val totalItems: Int
)

data class Item(
//    val accessInfo: AccessInfo,
    //val etag: String,
    val id: String,
    //val kind: String,
    //val saleInfo: SaleInfo,
    //val searchInfo: SearchInfo,
    //val selfLink: String,
    val volumeInfo: VolumeInfo
)
//
//data class AccessInfo(
//    val accessViewStatus: String,
//    val country: String,
//    val embeddable: Boolean,
//    val epub: Epub,
//    val pdf: Pdf,
//    val publicDomain: Boolean,
//    val quoteSharingAllowed: Boolean,
//    val textToSpeechPermission: String,
//    val viewability: String,
//    val webReaderLink: String = ""
//)
//
//data class SaleInfo(
//    val buyLink: String = "",
//    val country: String,
//    val isEbook: Boolean,
//    val listPrice: ListPrice,
//    val offers: List<Offer>,
//    val retailPrice: RetailPriceX,
//    val saleability: String
//)
//
//data class SearchInfo(
//    val textSnippet: String
//)
//
data class VolumeInfo(
    //val allowAnonLogging: Boolean,
    //val authors: List<String>,
    //val averageRating: Int = -1,
    //val canonicalVolumeLink: String,
    //val categories: List<String>,
    //val contentVersion: String,
        val description: String = "",
        val imageLinks: ImageLinks = ImageLinks("", ""),
    //val industryIdentifiers: List<IndustryIdentifier>,
    //val infoLink: String,
        val language: String = "",
    //val maturityRating: String,
        val pageCount: Int = -1,
    //val panelizationSummary: PanelizationSummary,
        val previewLink: String = "",
//    val printType: String,
//    val publishedDate: String,
//    val publisher: String = "",
//    val ratingsCount: Int = -1,
//    val readingModes: ReadingModes,
    //val subtitle: String = "",
        val title: String
)
//
//data class Epub(
//    val acsTokenLink: String = "",
//    val isAvailable: Boolean
//)
//
//data class Pdf(
//    val acsTokenLink: String = "",
//    val isAvailable: Boolean
//)
//
//data class ListPrice(
//    val amount: Double,
//    val currencyCode: String
//)
//
//data class Offer(
//    val finskyOfferType: Int,
//    val listPrice: ListPriceX,
//    val retailPrice: RetailPrice
//)
//
//data class RetailPriceX(
//    val amount: Double,
//    val currencyCode: String
//)
//
//data class ListPriceX(
//    val amountInMicros: Long,
//    val currencyCode: String
//)
//
//data class RetailPrice(
//    val amountInMicros: Long,
//    val currencyCode: String
//)
//
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)
data class imageLinks1(
        val smallThumbnail: String = "",
        val thumbnail: String = ""
)
//
//data class IndustryIdentifier(
//    val identifier: String,
//    val type: String
//)
//
//data class PanelizationSummary(
//    val containsEpubBubbles: Boolean,
//    val containsImageBubbles: Boolean
//)
//
//data class ReadingModes(
//    val image: Boolean,
//    val text: Boolean
//)