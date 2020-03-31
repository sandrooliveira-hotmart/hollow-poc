package com.hotmart.hollowpoc.cache

import com.hotmart.hollowpoc.model.entity.User
import com.netflix.hollow.api.codegen.HollowAPIGenerator
import com.netflix.hollow.core.write.HollowWriteStateEngine
import com.netflix.hollow.core.write.objectmapper.HollowObjectMapper
import org.springframework.stereotype.Component


@Component
class HollowApiGeneration {

    final val writeEngine = HollowWriteStateEngine()
    val mapper = HollowObjectMapper(writeEngine)

    fun generateApi() {
        mapper.initializeTypeState(User::class.java)
        val apiGeneration = HollowAPIGenerator.Builder()
                .withAPIClassname("UserAPI")
                .withPackageName("com.hotmart.hollowpoc.hollow")
                .withDataModel(writeEngine)
                .withDestination("src/main/kotlin/com/hotmart/hollowpoc/hollow")
                .build()
        apiGeneration.generateSourceFiles()
    }

}
