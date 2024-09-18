package id.fahmikudo.kotlin.restful.service.impl

import id.fahmikudo.kotlin.restful.entitiy.Product
import id.fahmikudo.kotlin.restful.model.CreateProductRequest
import id.fahmikudo.kotlin.restful.model.EditProductRequest
import id.fahmikudo.kotlin.restful.model.ListProductRequest
import id.fahmikudo.kotlin.restful.model.ProductResponse
import id.fahmikudo.kotlin.restful.repository.ProductRepository
import id.fahmikudo.kotlin.restful.service.ProductService
import id.fahmikudo.kotlin.restful.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.Date
import java.util.stream.Collectors

@Service
class ProductServiceImpl (
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
) : ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {

        validationUtil.validate(createProductRequest)

        val product = Product(
            id = createProductRequest.id!!,
            name = createProductRequest.name!!,
            price = createProductRequest.price!!,
            quantity = createProductRequest.quantity!!,
            createdAt = Date(),
            updatedAt = null
        )

        productRepository.save(product)

        return toProductResponse(product)

    }

    override fun get(id: String): ProductResponse {
        val product = productRepository.findByIdOrNull(id)
        if (product == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.")
        }
        return toProductResponse(product)
    }

    override fun edit(id: String, editProductRequest: EditProductRequest): ProductResponse {
        val product = productRepository.findByIdOrNull(id)
        if (product == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.")
        }

        validationUtil.validate(editProductRequest)

        product.apply {
            name = editProductRequest.name!!
            price = editProductRequest.price!!
            quantity = editProductRequest.quantity!!
            updatedAt = Date()
        }

        productRepository.save(product)

        return toProductResponse(product)
    }

    override fun delete(id: String) {
        val product = productRepository.findByIdOrNull(id)
        if (product == null) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found.")
        }
        productRepository.delete(product)
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        val pageRequest: Pageable = PageRequest.of(listProductRequest.page, listProductRequest.size)
        val productPages = productRepository.findAll(pageRequest)
        val products: List<Product> = productPages.get().collect(Collectors.toList())
        return products.map { toProductResponse(it) }
    }


    private fun toProductResponse(product: Product): ProductResponse {
        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            updatedAt = product.updatedAt
        )
    }

}