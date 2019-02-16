package com.rommansabbir.backgroundserviceexample;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;


public class MyBackgroundService extends JobService {

    private JobParameters params;
    private myTask doThisTask;


    @Override
    public boolean onStartJob(JobParameters params) {
        this.params = params;
        /**
         * Create a new task
         */
        doThisTask = new myTask();

        /**
         * Execute the task
         */
        doThisTask.execute();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {

        /**
         * Checking the task is null or not
         * if not then cancel the current running task
         */
        if (doThisTask != null)
            doThisTask.cancel(true);
        return false;
    }

    private class myTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            /**
             * Run the service in a thread
             * Show a toast message
             */
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "DONE !", Toast.LENGTH_SHORT).show();
                }
            });
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            jobFinished(params, false);
            super.onPostExecute(aVoid);
        }
    }

}
