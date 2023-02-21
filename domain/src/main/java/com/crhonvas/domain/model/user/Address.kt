package com.crhonvas.domain.model.user

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

class Address private constructor(builder: Builder) {
    private val street: String?
    private val suite: String?
    private val city: String?
    private val zipCode: String?
    private val geo: Geo?

    init {
        street = builder.street
        suite = builder.suite
        city = builder.city
        zipCode = builder.zipCode
        geo = builder.geo
    }

    fun getStreet(): String {
        return street ?: ""
    }

    fun getSuite(): String {
        return suite ?: ""
    }

    fun getCity(): String {
        return city ?: ""
    }

    fun getZipCode(): String {
        return zipCode ?: ""
    }

    fun getGeo(): Geo {
        return geo ?: Geo.Builder().build()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val address = o as Address
        return EqualsBuilder()
            .append(street, address.street)
            .append(suite, address.suite)
            .append(city, address.city)
            .append(zipCode, address.zipCode)
            .append(geo, address.geo)
            .isEquals
    }

    override fun hashCode(): Int {
        return HashCodeBuilder(17, 37)
            .append(street)
            .append(suite)
            .append(city)
            .append(zipCode)
            .append(geo)
            .toHashCode()
    }

    class Builder {
        var street: String? = null
        var suite: String? = null
        var city: String? = null
        var zipCode: String? = null
        var geo: Geo? = null
        fun street(street: String): Builder {
            this.street = street
            return this
        }

        fun suite(suite: String): Builder {
            this.suite = suite
            return this
        }

        fun city(city: String): Builder {
            this.city = city
            return this
        }

        fun zipCode(zipCode: String): Builder {
            this.zipCode = zipCode
            return this
        }

        fun geo(geo: Geo): Builder {
            this.geo = geo
            return this
        }

        fun build(): Address {
            return Address(this)
        }
    }
}