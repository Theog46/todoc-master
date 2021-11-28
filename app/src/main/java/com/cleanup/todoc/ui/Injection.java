package com.cleanup.todoc.ui;

import android.content.Context;

import com.cleanup.todoc.database.TodocDB;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static TaskRepository provideTaskData(Context context) {
        TodocDB db = TodocDB.getInstance(context);
        return new TaskRepository(db.taskDao());
    }

    public static ProjectRepository provideProjectData(Context context) {
        TodocDB db = TodocDB.getInstance(context);
        return new ProjectRepository(db.projectDao());
    }

    public static Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    public static ViewModelFactory provideVMF(Context context) {
        TaskRepository taskRepository = provideTaskData(context);
        ProjectRepository projectRepository = provideProjectData(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(projectRepository, taskRepository, executor);
    }
}
