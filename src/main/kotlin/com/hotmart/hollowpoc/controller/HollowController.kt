package com.hotmart.hollowpoc.controller

import com.hotmart.hollowpoc.service.HollowService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("hollow/rest/v1/")
class HollowController @Autowired constructor(private val hollowService: HollowService) {

    @RequestMapping(value = ["test"])
    fun test(): ResponseEntity<Any> {
        return ResponseEntity.ok("Return of application")
    }

    @RequestMapping(value = ["getAllUsers"])
    fun getAllUsers(): ResponseEntity<Any> {
        val listUsers = hollowService.getAllUser()
        return ResponseEntity.ok(listUsers)
    }

    @RequestMapping(value = ["generateDataSnapshot"])
    fun generateDataSnapshot(): ResponseEntity<Any> {
        return ResponseEntity.ok(hollowService.saveInLocal())
    }

    @RequestMapping(value = ["generateApiFiles"])
    fun generateApiFiles(): ResponseEntity<Any> {
        return ResponseEntity.ok(hollowService.generateFiles())
    }

    @RequestMapping(value = ["getHollowData"])
    fun getHollowData(): ResponseEntity<Any> {
        return ResponseEntity.ok(hollowService.hollowConsumerTest())
    }

}
