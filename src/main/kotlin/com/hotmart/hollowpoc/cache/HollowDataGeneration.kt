package com.hotmart.hollowpoc.cache

import com.netflix.hollow.api.producer.HollowProducer
import com.netflix.hollow.api.producer.fs.HollowFilesystemAnnouncer
import com.netflix.hollow.api.producer.fs.HollowFilesystemPublisher
import org.springframework.stereotype.Component
import java.nio.file.Path
import java.util.*

@Component
class HollowDataGeneration  {

    private final val localPublishDir = Path.of("src/main/resources")
    val publisher = HollowFilesystemPublisher(localPublishDir)
    val announcer = HollowFilesystemAnnouncer(localPublishDir)

    fun dataGenerator(data: Any) {
        val hollowProducer = HollowProducer.withPublisher(publisher).withAnnouncer(announcer).build()

        hollowProducer.runCycle { state: HollowProducer.WriteState ->
            if (data is List<*>) {
                data.stream().forEach { state.objectMapper.add(it!!) }
            }
        }
    }

}
