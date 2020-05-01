package social.network.fit.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

/**
 * configuration to handle multiple api calls
 * to mocked social network
 * The api calls take place asynchronously in a parallel fashion
 */
@Configuration
@EnableAsync
class AsyncConfiguration {
    @Bean(name = ["taskExecutor"])
    fun taskExecutor(): Executor? {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 3
        executor.maxPoolSize = 3
        executor.setQueueCapacity(100)
        executor.setThreadNamePrefix("userThread-")
        executor.initialize()
        return executor
    }
}