package id.fahmikudo.kotlin.restful.model

import java.util.Date

data class ProductResponse(
    val id: String,
    val name: String,
    val price: Long,
    val quantity: Int,
    val createdAt: Date,
    val updatedAt: Date?
) {

}
