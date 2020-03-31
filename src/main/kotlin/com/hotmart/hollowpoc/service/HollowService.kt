package com.hotmart.hollowpoc.service

import com.hotmart.hollowpoc.cache.HollowApiGeneration
import com.hotmart.hollowpoc.cache.HollowConsumerConfig
import com.hotmart.hollowpoc.cache.HollowDataGeneration
import com.hotmart.hollowpoc.hollow.UserAPI
import com.hotmart.hollowpoc.model.entity.User
import com.hotmart.hollowpoc.model.repository.UserRepository
import com.hotmart.hollowpoc.model.repository.WalletRepository
import com.hotmart.hollowpoc.vo.UserVO
import com.netflix.hollow.api.producer.HollowProducer
import com.netflix.hollow.api.producer.fs.HollowFilesystemAnnouncer
import com.netflix.hollow.api.producer.fs.HollowFilesystemPublisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.io.File
import java.util.*
import kotlin.streams.toList

@Service
class HollowService @Autowired constructor(val userRepository: UserRepository,
                                           val walletRepository: WalletRepository,
                                           val hollowDataGeneration: HollowDataGeneration,
                                           val hollowApiGeneration: HollowApiGeneration,
                                           val hollowConsumerConfig: HollowConsumerConfig) {

    fun getAllUser(): UserVO {
        val users = userRepository.findAll()
        return UserVO(users = users.toList(), size = users.toList().size)
    }

    fun saveInLocal() {
        val users = userRepository.findAll()
        hollowDataGeneration.dataGenerator(users.toList())
    }

    fun generateFiles() {
        hollowApiGeneration.generateApi()
    }

    fun hollowConsumerTest(): UserVO {
        val consumer = hollowConsumerConfig.consumer()
        consumer.triggerRefresh()
        val userApi = consumer.api as UserAPI
        val users = userApi.allUser.stream().map { User(id = it.id.value, name = it.name.value,
                dateBorn = Date(it.dateBorn.value), userCode = it.userCode.value,
                description = it.description.value) }.toList()
        return UserVO(size = users.size, users = users)
    }
}
