package com.mrad.ecommercebackend.product

import com.fasterxml.jackson.annotation.JsonIgnore
import com.mrad.ecommercebackend.inventory.Inventory
import lombok.Getter
import lombok.Setter

@Getter
@Setter
class Product {
    private var id: Long? = null
    private var productName: String? = null
    private var shortDescription: String? = null
    private var longDescription: String? = null
    private var price: Double? = null

    @JsonIgnore
    private val inventory: Inventory? = null

    constructor()
    constructor(id: Long?, productName: String?, shortDescription: String?, longDescription: String?, price: Double?) {
        this.id = id
        this.productName = productName
        this.shortDescription = shortDescription
        this.longDescription = longDescription
        this.price = price
    }
}