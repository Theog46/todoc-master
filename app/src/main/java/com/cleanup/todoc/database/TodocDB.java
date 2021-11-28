package com.cleanup.todoc.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.Date;
import java.util.Random;

@Database(entities = {Project.class, Task.class}, version = 2, exportSchema = false)
public abstract class TodocDB extends RoomDatabase {

    private static volatile TodocDB INSTANCE;

    // --- DAO ---
    public abstract ProjectDao projectDao();
    public abstract TaskDao taskDao();

    // --- INSTANCE ---
    public static TodocDB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDB.class, "TodocDB")
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues projectContentValues = new ContentValues();

                projectContentValues.put("id", 1L);
                projectContentValues.put("name", "Projet Tartampion");
                projectContentValues.put("color", 0xFFEADAD1);
                db.insert("Project", OnConflictStrategy.REPLACE, projectContentValues);

                projectContentValues.put("id", 2L);
                projectContentValues.put("name", "Projet Circus");
                projectContentValues.put("color", 0xFFA3CED2);
                db.insert("Project", OnConflictStrategy.REPLACE, projectContentValues);

                projectContentValues.put("id", 3L);
                projectContentValues.put("name", "Projet Circus");
                projectContentValues.put("color", 0xFFA3CED2);
                db.insert("Project", OnConflictStrategy.REPLACE, projectContentValues);

                ContentValues taskContentValues = new ContentValues();

                long cpt = 1;
                long pid = 1;
                String pname = "";
                while (cpt < 10) {
                    Random random = new Random();
                    int nb;
                    pid = random.nextInt(3);
                    pid ++;
                    pname = "Tâche n° " + String.valueOf(cpt);
                    taskContentValues.put("projectId", pid);
                    taskContentValues.put("name", pname);
                    taskContentValues.put("creationTimeStamp", new Date().getTime());

                    db.insert("Task", OnConflictStrategy.REPLACE, taskContentValues);
                    cpt ++;
                }
            }
        };
    }
}