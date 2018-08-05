package pl.futuredev.capstoneproject.viewmodel;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private static final Object LOCK = new Object();
    private static AppExecutors appExecutors;
    private final Executor diskIO;
    private final Executor recipeID;
    private final Executor mainThread;

    public AppExecutors(Executor diskIO, Executor recipeID, Executor mainThread) {
        this.diskIO = diskIO;
        this.recipeID = recipeID;
        this.mainThread = mainThread;
    }

    public static AppExecutors getInstance() {
        if (appExecutors == null) {
            synchronized (LOCK) {
                appExecutors = new AppExecutors(Executors.newSingleThreadExecutor(),
                        Executors.newFixedThreadPool(2),
                        new MainThreadExecutor());
            }
        }
        return appExecutors;
    }

    public Executor getDiskIO() {
        return diskIO;
    }

    public Executor getRecipeID() {
        return recipeID;
    }

    public Executor getMainThread() {
        return mainThread;
    }

    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
