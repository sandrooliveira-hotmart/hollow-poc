package com.hotmart.hollowpoc.cache

import com.hotmart.hollowpoc.hollow.UserAPI
import com.netflix.hollow.api.consumer.HollowConsumer
import com.netflix.hollow.api.consumer.fs.HollowFilesystemAnnouncementWatcher
import com.netflix.hollow.api.consumer.fs.HollowFilesystemBlobRetriever
import org.springframework.stereotype.Component
import java.nio.file.Path

@Component
class HollowConsumerConfig {

    private final val localPublishDir = Path.of("src/main/resources")
    val blobRetriever = HollowFilesystemBlobRetriever(localPublishDir)
    val announcementWatcher = HollowFilesystemAnnouncementWatcher(localPublishDir)

    fun consumer(): HollowConsumer {
        return HollowConsumer.withBlobRetriever(blobRetriever)
                .withAnnouncementWatcher(announcementWatcher)
                .withGeneratedAPIClass(UserAPI::class.java)
                .build()
    }

}
