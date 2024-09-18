package id.fahmikudo.kotlin.restful.service

import id.fahmikudo.kotlin.restful.model.CreateProductRequest
import id.fahmikudo.kotlin.restful.model.EditProductRequest
import id.fahmikudo.kotlin.restful.model.ListProductRequest
import id.fahmikudo.kotlin.restful.model.ProductResponse

interface ProductService {

    fun create(createProductRequest: CreateProductRequest): ProductResponse;

    fun get(id: String): ProductResponse

    fun edit(id: String, editProductRequest: EditProductRequest): ProductResponse

    fun delete(id: String)

    fun list(listProductRequest: ListProductRequest): List<ProductResponse>

}