package com.hotmart.hollowpoc.model.entity

import lombok.Builder
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import java.util.*
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Temporal
import javax.persistence.TemporalType

@Entity
@Builder
data class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column
        val userCode: String = "",

        @Column
        val name: String = "",

        @Column(columnDefinition = "TEXT")
        val description: String = "",

        @Column(columnDefinition = "datetime")
        @Temporal(TemporalType.TIMESTAMP)
        val dateBorn: Date = Date(),

        @LazyCollection(LazyCollectionOption.FALSE)
        @OneToMany(mappedBy = "user", cascade = arrayOf(CascadeType.ALL))
        val wallets: List<Wallet>? = null

)
