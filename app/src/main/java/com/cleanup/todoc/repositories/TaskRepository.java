package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;

    public TaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    // GET
    public LiveData<List<Task>> getAllTasksDefault() {
        return this.taskDao.getAllTasksDefault();
    }

    // INSERT
    public void insertTask(Task task) {
        taskDao.insertTask(task);
    }

    // DELETE
    public void deleteTask(long id) {
        taskDao.deleteTask(id);
    }

    // A-Z
    public LiveData<List<Task>> getTaskAZ() {
        return this.taskDao.getTasksAlphabeticalAZ();
    }

    // Z-A
    public LiveData<List<Task>> getTaskZA() {
        return this.taskDao.getTasksAlphabeticalZA();
    }

    // NEW -> OLD
    public LiveData<List<Task>> getTaskNewToOld() {
        return this.taskDao.getTasksNewToOld();
    }

    // OLD -> NEW
    public LiveData<List<Task>> getTaskOldToNew() {
        return this.taskDao.getTasksOldToNew();
    }
}
