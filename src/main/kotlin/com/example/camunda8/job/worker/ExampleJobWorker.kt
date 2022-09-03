package com.example.camunda8.job.worker

import io.camunda.zeebe.client.api.response.ActivatedJob
import io.camunda.zeebe.client.api.worker.JobClient
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker
import org.springframework.stereotype.Component

@Component
class ExampleJobWorker {

    @ZeebeWorker(type = "example-task-type", autoComplete = true)
    fun execute(
        jobClient: JobClient,
        activatedJob: ActivatedJob
    ) {
    }
}