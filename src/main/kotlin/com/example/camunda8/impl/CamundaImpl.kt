package com.example.camunda8.impl

import com.example.camunda8.api.Camunda
import com.example.camunda8.api.CamundaUserTask
import io.camunda.tasklist.CamundaTaskListClient
import io.camunda.zeebe.client.ZeebeClient
import io.camunda.zeebe.client.api.response.ActivatedJob
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CamundaImpl : Camunda {
    @Autowired
    private val zeebeClient: ZeebeClient? = null

    @PostMapping("/process/{id}/start")
    override fun startProcessInstanceById(@PathVariable id: String) {
        zeebeClient
            ?.newCreateInstanceCommand()
            ?.bpmnProcessId(id)
            ?.latestVersion()
            ?.send()
            ?.join()
    }

    @GetMapping("/task")
    override fun getUserTaskList(): MutableList<CamundaUserTask>? {
        TODO("Not yet implemented")
    }

    @GetMapping("/task/{key}")
    override fun getUserTaskListByKey(@PathVariable key: String): MutableList<CamundaUserTask>? {
        TODO("Not yet implemented")
    }
}