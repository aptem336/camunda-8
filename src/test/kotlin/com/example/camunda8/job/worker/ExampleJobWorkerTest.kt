package com.example.camunda8.job.worker

import io.camunda.zeebe.client.ZeebeClient
import io.camunda.zeebe.process.test.assertions.BpmnAssert
import io.camunda.zeebe.process.test.assertions.BpmnAssert.*
import io.camunda.zeebe.spring.test.ZeebeSpringTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@ZeebeSpringTest
class ExampleJobWorkerTest {
    @Autowired
    private val zeebeClient: ZeebeClient? = null

    @Test
    fun exampleJobWorkerExecuteTest() {
        val processInstanceResult = zeebeClient
            ?.newCreateInstanceCommand()
            ?.bpmnProcessId("camunda-8-example-process")
            ?.latestVersion()
            ?.withResult()
            ?.send()
            ?.join()
        assertThat(processInstanceResult)
    }
}