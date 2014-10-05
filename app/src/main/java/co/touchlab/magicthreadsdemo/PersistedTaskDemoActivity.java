package co.touchlab.magicthreadsdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import co.touchlab.android.threading.eventbus.EventBusExt;
import co.touchlab.android.threading.tasks.persisted.storage.DefaultPersistedTaskQueue;
import co.touchlab.magicthreadsdemo.persisted.NullCommand;

public class PersistedTaskDemoActivity extends Activity
{
    public static void callMe(Activity a)
    {
        Intent i = new Intent(a, PersistedTaskDemoActivity.class);
        a.startActivity(i);
    }

    private View doThing;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_demo);

        EventBusExt.getDefault().register(this);

        doThing = findViewById(R.id.doThing);
        doThing.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendTask();
            }
        });
        checkRunning();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        EventBusExt.getDefault().unregister(this);
    }

    private void sendTask()
    {
        DefaultPersistedTaskQueue.getInstance(this).execute(new NullCommand());
        checkRunning();
    }

    @SuppressWarnings("UnusedDeclaration")
    public void onEventMainThread(NullCommand task)
    {
        checkRunning();
        findViewById(R.id.taskDone).setVisibility(View.VISIBLE);
    }

    private void checkRunning()
    {
//        TaskQueryTasksOfType queueQuery = new TaskQueryTasksOfType(NullTask.class);
//        TaskQueue.loadQueueDefault().query(queueQuery);
//        doThing.setEnabled(!queueQuery.found);
    }
}