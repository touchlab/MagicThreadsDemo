package co.touchlab.magicthreadsdemo.test.ordering;

import android.content.Context;
import android.util.Log;

import co.touchlab.android.threading.errorcontrol.SoftException;
import co.touchlab.android.threading.tasks.persisted.Command;

/**
 * Created by kgalligan on 10/5/14.
 */
public class NumberedCommand extends Command
{
    int commandCount;

    public NumberedCommand(int commandCount)
    {
        this.commandCount = commandCount;
    }

    public NumberedCommand()
    {
    }

    @Override
    public void run(Context context) throws SoftException, Throwable
    {
        Log.i("Lots", "Numbered: "+ commandCount);
    }

    @Override
    public boolean handlePermanentError(Context context, Throwable exception)
    {
        return false;
    }
}