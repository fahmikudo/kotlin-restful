package id.fahmikudo.kotlin.restful.controller

import id.fahmikudo.kotlin.restful.model.*
import id.fahmikudo.kotlin.restful.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController (val productService: ProductService) {


    @PostMapping(
        produces = [ APPLICATION_JSON_VALUE ],
        consumes = [ APPLICATION_JSON_VALUE ]
    )
    fun createProduct(@RequestBody request: CreateProductRequest) : BaseResponse<ProductResponse> {
        val productResponse = productService.create(request)
        return BaseResponse(
            code = HttpStatus.OK.value(),
            status = HttpStatus.OK.name,
            data = productResponse
        )
    }

    @GetMapping(
        value = ["/{id}"],
        produces = [ APPLICATION_JSON_VALUE ],
    )
    fun getProduct(@PathVariable id: String) : BaseResponse<ProductResponse> {
        val productResponse = productService.get(id)
        return BaseResponse(
            code = HttpStatus.OK.value(),
            status = HttpStatus.OK.name,
            data = productResponse
        )
    }

    @PutMapping(
        value = ["/{id}"],
        consumes = [ APPLICATION_JSON_VALUE ],
        produces = [ APPLICATION_JSON_VALUE ]
    )
    fun editProduct(@PathVariable id: String, @RequestBody request: EditProductRequest) : BaseResponse<ProductResponse> {
        val productResponse = productService.edit(id, request)
        return BaseResponse(
            code = HttpStatus.OK.value(),
            status = HttpStatus.OK.name,
            data = productResponse
        )
    }

    @DeleteMapping(
        value = ["/{id}"],
        produces = [ APPLICATION_JSON_VALUE ]
    )
    fun deleteProduct(@PathVariable id: String) : BaseResponse<String> {
        productService.delete(id)
        return BaseResponse(
            code = HttpStatus.OK.value(),
            status = HttpStatus.OK.name,
            data = id
        )
    }

    @GetMapping(
        produces = [ APPLICATION_JSON_VALUE ],
    )
    fun listProducts(@RequestParam(value = "page", defaultValue = "1") page: Int,
                     @RequestParam(value = "size", defaultValue = "10") size: Int) : BaseResponse<List<ProductResponse>> {
        val listProductRequest = ListProductRequest(page - 1, size)
        val productResponses = productService.list(listProductRequest)
        return BaseResponse(
            code = HttpStatus.OK.value(),
            status = HttpStatus.OK.name,
            data = productResponses
        )
    }



}