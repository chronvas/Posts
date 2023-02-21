package com.crhonvas.domain.model.user

import org.apache.commons.lang3.builder.EqualsBuilder
import org.apache.commons.lang3.builder.HashCodeBuilder

class User private constructor(builder: Builder) {
    private val id: Int?
    private val name: String?
    private val userName: String?
    private val email: String?
    private val address: Address?
    private val phone: String?
    private val website: String?
    private val company: Company?

    init {
        id = builder.id
        name = builder.name
        userName = builder.userName
        email = builder.email
        address = builder.address
        phone = builder.phone
        website = builder.website
        company = builder.company
    }

    fun getId(): Int {
        return id ?: -1
    }

    fun getName(): String {
        return name ?: ""
    }

    fun getUserName(): String {
        return userName ?: ""
    }

    fun getEmail(): String {
        return email ?: ""
    }

    fun getAddress(): Address {
        return address ?: Address.Builder().build()
    }

    fun getPhone(): String {
        return phone ?: ""
    }

    fun getWebsite(): String {
        return website ?: ""
    }

    fun getCompany(): Company {
        return company ?: Company.Builder().build()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val user = o as User
        return EqualsBuilder()
            .append(id, user.id)
            .append(name, user.name)
            .append(userName, user.userName)
            .append(email, user.email)
            .append(address, user.address)
            .append(phone, user.phone)
            .append(website, user.website)
            .append(company, user.company)
            .isEquals
    }

    override fun hashCode(): Int {
        return HashCodeBuilder(17, 37)
            .append(id)
            .append(name)
            .append(userName)
            .append(email)
            .append(address)
            .append(phone)
            .append(website)
            .append(company)
            .toHashCode()
    }

    class Builder {
        var id: Int? = null
        var name: String? = null
        var userName: String? = null
        var email: String? = null
        var address: Address? = null
        var phone: String? = null
        var website: String? = null
        var company: Company? = null
        fun id(id: Int): Builder {
            this.id = id
            return this
        }

        fun name(name: String): Builder {
            this.name = name
            return this
        }

        fun userName(userName: String): Builder {
            this.userName = userName
            return this
        }

        fun email(email: String): Builder {
            this.email = email
            return this
        }

        fun address(address: Address): Builder {
            this.address = address
            return this
        }

        fun phone(phone: String): Builder {
            this.phone = phone
            return this
        }

        fun website(website: String): Builder {
            this.website = website
            return this
        }

        fun company(company: Company): Builder {
            this.company = company
            return this
        }

        fun build(): User {
            return User(this)
        }
    }
}