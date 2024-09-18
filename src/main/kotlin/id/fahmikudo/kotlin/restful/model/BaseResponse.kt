package id.fahmikudo.kotlin.restful.model

data class BaseResponse <T> (
    val code: Int,
    val status: String,
    val data: T
) {
}