package com.crhonvas.domain.model.user

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

class Company private constructor(builder: Builder) {
    private val name: String?
    private val catchPhrase: String?
    private val bs: String?

    init {
        name = builder.name
        catchPhrase = builder.catchPhrase
        bs = builder.bs
    }

    fun getName(): String {
        return name ?: ""
    }

    fun getCatchPhrase(): String {
        return catchPhrase ?: ""
    }

    fun getBs(): String {
        return bs ?: ""
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val company = o as Company
        return EqualsBuilder()
            .append(name, company.name)
            .append(catchPhrase, company.catchPhrase)
            .append(bs, company.bs)
            .isEquals
    }

    override fun hashCode(): Int {
        return HashCodeBuilder(17, 37)
            .append(name)
            .append(catchPhrase)
            .append(bs)
            .toHashCode()
    }

    class Builder {
        var name: String? = null
        var catchPhrase: String? = null
        var bs: String? = null
        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun catchPhrase(catchPhrase: String): Builder {
            this.catchPhrase = catchPhrase
            return this
        }

        fun bs(bs: String): Builder {
            this.bs = bs
            return this
        }

        fun build(): Company {
            return Company(this)
        }
    }
}