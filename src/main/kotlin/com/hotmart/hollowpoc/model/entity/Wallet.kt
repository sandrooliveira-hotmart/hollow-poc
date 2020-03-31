package com.hotmart.hollowpoc.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import java.math.BigDecimal
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class Wallet (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    val balance: BigDecimal = BigDecimal.ZERO,

    @Column
    val releaseBalance: BigDecimal = BigDecimal.ZERO,

    @Column(columnDefinition = "char(3) default 'BRL'")
    val currencyCode: String = "BRL",

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    val user: User? = null

)
