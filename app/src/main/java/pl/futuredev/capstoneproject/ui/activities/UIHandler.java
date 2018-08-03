package pl.futuredev.capstoneproject.ui.activities;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;


public class UIHandler  extends Handler
{
    public static final int DISPLAY_UI_TOAST = 0;
    public static final int DISPLAY_UI_DIALOG = 1;

    public UIHandler(Looper looper)
    {
        super(looper);
    }

    @Override
    public void handleMessage(Message msg)
    {
        switch(msg.what)
        {
            case UIHandler.DISPLAY_UI_TOAST:
            {
                Context context = getApplicationContext();
                Toast t = Toast.makeText(context, (String)msg.obj, Toast.LENGTH_LONG);
                t.show();
            }
            case UIHandler.DISPLAY_UI_DIALOG:
            default:
                break;
        }
    }
}