package com.example.mahdi.languagelearning.RoomDB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static volatile WordRoomDatabase INSTANCE;

    static WordRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };


    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            mDao.deleteAll();

            Word word = new Word("A", true);
            mDao.insert(word);

            word = new Word("B", false);
            mDao.insert(word);

            word = new Word("C", false);
            mDao.insert(word);

            word = new Word("D", false);
            mDao.insert(word);

            word = new Word("E", false);
            mDao.insert(word);

            word = new Word("F", false);
            mDao.insert(word);

            word = new Word("G", false);
            mDao.insert(word);

            word = new Word("H", false);
            mDao.insert(word);

            word = new Word("I", false);
            mDao.insert(word);

            word = new Word("J", false);
            mDao.insert(word);

            word = new Word("K", false);
            mDao.insert(word);

            word = new Word("L", false);
            mDao.insert(word);

            word = new Word("M", false);
            mDao.insert(word);

            word = new Word("N", false);
            mDao.insert(word);

            word = new Word("O", false);
            mDao.insert(word);

            word = new Word("P", false);
            mDao.insert(word);

            word = new Word("Q", false);
            mDao.insert(word);

            word = new Word("R", false);
            mDao.insert(word);

            word = new Word("S", false);
            mDao.insert(word);

            word = new Word("T", false);
            mDao.insert(word);

            word = new Word("U", false);
            mDao.insert(word);

            word = new Word("V", false);
            mDao.insert(word);

            word = new Word("W", false);
            mDao.insert(word);

            word = new Word("X", false);
            mDao.insert(word);

            word = new Word("Y", false);
            mDao.insert(word);

            word = new Word("Z", false);
            mDao.insert(word);
            return null;
        }
    }

}