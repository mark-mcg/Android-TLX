package markphd.org.android_tlx;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);

            // so there are two ways we can use the TLX activity
            boolean askForNameAndCondition = false;

            if (askForNameAndCondition) {
                Intent startTLX = new Intent(this, TLXActivity.class);
                startActivity(startTLX);

            } else {
                Intent startTLX = new Intent(this, TLXActivity.class);
                startTLX.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle b = new Bundle();
                startTLX.putExtra("participant-id", "Mark");
                startTLX.putExtra("condition", "3");
                startActivity(startTLX);
            }
        } catch (Exception e){
            Log.v("MainActivity", "Exception " + e);
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
