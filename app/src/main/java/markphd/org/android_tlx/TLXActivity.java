package markphd.org.android_tlx;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This activity does two things:
 *  - If no bundle is received, shows some form fields requesting a name/id and condition
 *  - if a bundle is received containing a name and condition, then skip the fields and start the TLX questions
 * 
 * @author Mark McGill, Euan Freeman
 */

public class TLXActivity extends Activity {
	public WebView mWebView;
	public TLXWebApp mWebApp;
	public TextView mMessage;
    private EditText mParticipantText;
    private EditText mBlockText;
    private LinearLayout mContainer;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tlx);
        mWebView = (WebView) findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);

        mParticipantText = (EditText) findViewById(R.id.participant_number);
        mBlockText = (EditText) findViewById(R.id.block_number);
        mContainer = (LinearLayout) findViewById(R.id.container);
        mMessage = (TextView) findViewById(R.id.message);

        Bundle extras = getIntent().getExtras();
		String nameID = "NO-NAME-SET", condition = "1";


		if (extras != null) {
            // if we've got a bundle, assume we don't want the activity to get
            // condition number / participant name
            nameID = extras.getString("participant-name", "NO-NAME-SET");
		    condition = extras.getString("condition", "3");

            // start TLX questions up straight away
            mWebApp = new TLXWebApp(this, nameID, condition);
            mWebView.addJavascriptInterface(mWebApp, "Android");
            mWebView.loadUrl("file:///android_asset/tlx.html");

            // set visibility of form fields versus TLX questions
            mContainer.setVisibility(View.INVISIBLE);
            mMessage.setVisibility(View.INVISIBLE);
            mWebView.setVisibility(View.VISIBLE);
		} else {

            // set visibility of form fields versus TLX questions
            mContainer.setVisibility(View.VISIBLE);
            mMessage.setVisibility(View.VISIBLE);
            mWebView.setVisibility(View.INVISIBLE);
        }
	}

    public void startTLX(View trigger){
        String nameID = mParticipantText.getText().toString();
        String condition = mBlockText.getText().toString();

        if (nameID.length() == 0 || condition.length() == 0) {
            Toast.makeText(this, "Please enter participant ID and block number", Toast.LENGTH_SHORT).show();
            return;
        }

        mWebApp = new TLXWebApp(this, nameID, condition);

        mWebView.addJavascriptInterface(mWebApp, "Android");
        mWebView.loadUrl("file:///android_asset/tlx.html");

        mContainer.setVisibility(View.INVISIBLE);
        mMessage.setVisibility(View.INVISIBLE);
        mWebView.setVisibility(View.VISIBLE);
    }
	
	/**
	 * Hide the TLX questionnaire and show a message to the participant.
	 */
	public void finishedTLX() {
		Log.v("Finishing..", "");
		//Intent startMain = new Intent(Intent.ACTION_MAIN);
        //startMain.addCategory(Intent.CATEGORY_HOME);
        //startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //startActivity(startMain);
        runOnUiThread(actionFinishTLX);
    }

    /**
     * This runnable should be run on the UI thread. Hides the
     * TLX webview and displays a message to the participant.
     */
    private Runnable actionFinishTLX = new Runnable() {
        @Override
        public void run() {
            mWebView.setVisibility(View.INVISIBLE);

            mMessage.setText("End of questionnaire - please tell the experimenter.");
            mMessage.setVisibility(View.VISIBLE);
            final Handler handler = new Handler();
            handler.postDelayed(delayedFinish, 3000); // close activity after 3 seconds
        }
    };

    private Runnable delayedFinish = new Runnable() {
        @Override
        public void run() {
            finish();
        }
    };

    /*
        Prevent the back button from being pressed accidentally
     */
	@Override
	public void onBackPressed() {
	    new AlertDialog.Builder(this)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle("Closing Activity")
	        .setMessage("Are you sure you want to close this activity?")
	        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
	    {
	        public void onClick(DialogInterface dialog, int which) {
	            finish();    
	        }

	    })
	    .setNegativeButton("No", null)
	    .show();
	}
}
