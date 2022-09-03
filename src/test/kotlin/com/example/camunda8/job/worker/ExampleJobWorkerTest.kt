package com.example.camunda8.job.worker

import io.camunda.zeebe.client.ZeebeClient
import io.camunda.zeebe.process.test.assertions.BpmnAssert.*
import io.camunda.zeebe.protocol.Protocol.USER_TASK_JOB_TYPE
import io.camunda.zeebe.spring.test.ZeebeSpringTest
import io.camunda.zeebe.spring.test.ZeebeTestThreadSupport.waitForProcessInstanceCompleted
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
        val processInstanceEvent = zeebeClient
            ?.newCreateInstanceCommand()
            ?.bpmnProcessId("camunda-8-example-process")
            ?.latestVersion()
            ?.send()
            ?.join()
        assertThat(processInstanceEvent).isStarted

        assertThat(processInstanceEvent).isWaitingAtElements(
            "Activity_ServiceTask",
            "Activity_UserTask"
        )

        val jobs = zeebeClient
            ?.newActivateJobsCommand()
            ?.jobType(USER_TASK_JOB_TYPE)
            ?.maxJobsToActivate(1)
            ?.send()
            ?.join()
            ?.jobs

        jobs?.forEach { activatedJob -> zeebeClient?.newCompleteCommand(activatedJob.key)?.send()?.join() }

        waitForProcessInstanceCompleted(processInstanceEvent)
        assertThat(processInstanceEvent).isCompleted
    }
}