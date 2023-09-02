package com.example.widgetactivity;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.concurrent.Executor;

public class Utilities {
    final static Executor EXECUTOR_RUN_ASYNC = AsyncTask.THREAD_POOL_EXECUTOR;

    public static AsyncRun runAsync(@NonNull AsyncRun.Run background, @Nullable AsyncRun.Run after) {
        return (AsyncRun) new AsyncRun(background, after).executeOnExecutor(EXECUTOR_RUN_ASYNC);
    }
    public static class AsyncRun extends AsyncTask<Void, Void, Void> {
        private final Run mBackground;
        private final Run mAfter;

        public interface Run {
            void run(@NonNull AsyncRun task);
        }

        public AsyncRun(@NonNull Run background, @Nullable Run after) {
            super();
            mBackground = background;
            mAfter = after;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mBackground.run(this);
            return null;
        }

        @Override
        protected void onCancelled(Void aVoid) {
            if (mAfter != null)
                mAfter.run(this);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (mAfter != null)
                mAfter.run(this);
        }

        public boolean cancel() {
            return cancel(false);
        }
    }
}
