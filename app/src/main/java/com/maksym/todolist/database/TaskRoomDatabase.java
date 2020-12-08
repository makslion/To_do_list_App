package com.maksym.todolist.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TaskEntity.class}, version = 1, exportSchema = false)
public abstract class TaskRoomDatabase extends RoomDatabase
{
    public abstract TaskDAO taskDAO();
    private static TaskRoomDatabase INSTANCE;

    static TaskRoomDatabase getDatabase(final Context context)
    {
        if (INSTANCE == null)
        {
            synchronized (TaskRoomDatabase.class)
            {
                if (INSTANCE == null)
                {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TaskRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(roomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }




    /**
     * Override the onOpen method to populate the database.
     * For this sample, we clear the database every time it is created or opened.
     */
    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate (@NonNull SupportSQLiteDatabase db)
        {
            super.onCreate(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.
            //new PopulateDbAsync(INSTANCE).execute();
            Log.d("RoomDatabase", "On create db");
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db)
        {
            super.onOpen(db);
            Log.d("RoomDatabase", "On open db");

            // If you want to keep the data through app restarts,
            // comment out the following line.
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    /**
     * Populate the database in the background.
     * If you want to start with more words, just add them.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TaskDAO taskDAO;

        //sample data  {"Name", "default fingering", " "Note"}

        private String [] taskNames = {"Pet a cat", "Sleep well", "Have fun"};
        private String [] tastDesc = {"Susan is a very good cat! You should go and pat it ASAP","Ok, when the last time you were sleeping more then 5 hours? SLEEEP NOW!","just have fun lol. What are you waiting for?"};





        PopulateDbAsync(TaskRoomDatabase db)
        {
            taskDAO = db.taskDAO();
        }

        @Override
        protected Void doInBackground(final Void... params)
        {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            taskDAO.deleteAll();

            Log.d("RoomDatabse", "Populating database");
            for( int i = 0; i < taskNames.length; i++)
            {
                Log.d("RoomDatabse", "adding entity: "+taskNames[i]);
                TaskEntity entity = new TaskEntity(taskNames[i],tastDesc[i]);
                taskDAO.insert(entity);
            }
            return null;
        }
    }
}

