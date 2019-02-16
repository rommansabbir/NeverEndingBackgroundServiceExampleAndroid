package com.rommansabbir.backgroundserviceexample;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private JobScheduler jobScheduler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
        Instantiate JobScheduler
         */
        jobScheduler = (JobScheduler) (MainActivity.this).getSystemService(JOB_SCHEDULER_SERVICE);

        /**
        Handle start button action
         */
        (findViewById(R.id.startBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Create a new Component
                 * instantiate the new component
                 */
                ComponentName componentName = new ComponentName((MainActivity.this), MyBackgroundService.class);

                /**
                 * Build a new job to do by JobScheduler
                 * Set interval to 5 sec
                 */
                JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                        .setPeriodic(5000).build();

                /**
                Assign it to the jobScheduler
                 */
                jobScheduler.schedule(jobInfo);
            }
        });

        /**
        Handle stop button action
         */
        (findViewById(R.id.stopBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * cancel all job
                 */
                jobScheduler.cancelAll();
                Toast.makeText(getApplicationContext(), "Service stopped!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
