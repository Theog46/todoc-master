package com.cleanup.todoc.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final Executor executor;


    public enum SortListOfTasks {
        DEFAULT,
        TASKS_AZ,
        TASKS_ZA,
        TASKS_NewToOld,
        TASKS_OldToNew
    }

    public SortListOfTasks sortListOfTasks = SortListOfTasks.DEFAULT;

    public LiveData<List<Project>> getAllProjects() {
        return projectRepository.getAllProjects();
    }


    public TaskViewModel(ProjectRepository projectRepository, TaskRepository taskRepository, Executor executor) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.executor = executor;
    }

    public LiveData<List<Task>> getSortedTasks() {
        switch (sortListOfTasks) {
            case TASKS_AZ:
                return taskRepository.getTaskAZ();
            case TASKS_ZA:
                return taskRepository.getTaskZA();
            case TASKS_NewToOld:
                return taskRepository.getTaskNewToOld();
            case TASKS_OldToNew:
                return taskRepository.getTaskOldToNew();
            default:
                return taskRepository.getAllTasksDefault();
        }
    }

    public void insertTask(Task task) {
        executor.execute(() -> taskRepository.insertTask(task));
    }
    public void deleteTask(long id) {
        executor.execute(() -> taskRepository.deleteTask(id));
    }
}
