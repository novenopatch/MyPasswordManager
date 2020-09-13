package jin.jerrykel.mypasswordmanager.vue.AppActivity.Save;

import android.os.SystemClock;
import android.view.View;

public abstract class SingleClickListener  implements View.OnClickListener {
    protected int defaultInterval;
    private long lastTimeClicked = 0;
    public SingleClickListener() {
        this(1000);
    }
    public SingleClickListener(int minInterval) {
        this.defaultInterval = minInterval;
    }
    @Override
    public void onClick(View v) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return;
        }
        lastTimeClicked = SystemClock.elapsedRealtime();
        performClick(v);
    }

    public abstract void performClick(View v);
}
