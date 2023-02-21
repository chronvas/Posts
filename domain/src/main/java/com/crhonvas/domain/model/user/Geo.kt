package com.crhonvas.domain.model.user

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

class Geo private constructor(builder: Builder) {
    val lat: String
    val lng: String

    init {
        lat = builder.lat
        lng = builder.lng
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val geo = o as Geo
        return EqualsBuilder()
            .append(lat, geo.lat)
            .append(lng, geo.lng)
            .isEquals
    }

    override fun hashCode(): Int {
        return HashCodeBuilder(17, 37)
            .append(lat)
            .append(lng)
            .toHashCode()
    }

    class Builder {
        var lat = ""
        var lng = ""
        fun lat(lat: String): Builder {
            this.lat = lat
            return this
        }

        fun lng(lng: String): Builder {
            this.lng = lng
            return this
        }

        fun build(): Geo {
            return Geo(this)
        }
    }
}