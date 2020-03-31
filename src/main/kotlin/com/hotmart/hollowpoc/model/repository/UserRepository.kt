package com.hotmart.hollowpoc.model.repository

import com.hotmart.hollowpoc.model.entity.User
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository: PagingAndSortingRepository<User, Long> {
}
