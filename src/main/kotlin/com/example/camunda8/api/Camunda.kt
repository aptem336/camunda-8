package com.example.camunda8.api

interface Camunda {
    /**
     * Запуск процесса по идентификатор
     */
    fun startProcessInstanceById(id: String)

    /**
     * Получения списка пользовательских задач
     */
    fun getUserTaskList(): MutableList<CamundaUserTask>?

    /**
     * Получения списка пользовательских задач по бизнес-ключу процесса
     */
    fun getUserTaskListByKey(key: String): MutableList<CamundaUserTask>?
}