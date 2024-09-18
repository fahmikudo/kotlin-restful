package id.fahmikudo.kotlin.restful.repository

import id.fahmikudo.kotlin.restful.entitiy.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, String> {
}