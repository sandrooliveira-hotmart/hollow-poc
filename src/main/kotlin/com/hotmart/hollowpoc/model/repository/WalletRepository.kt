package com.hotmart.hollowpoc.model.repository

import com.hotmart.hollowpoc.model.entity.Wallet
import org.springframework.data.jpa.repository.JpaRepository

interface WalletRepository: JpaRepository<Wallet, Long> {
}
