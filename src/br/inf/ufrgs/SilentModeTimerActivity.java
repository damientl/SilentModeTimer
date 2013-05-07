package br.inf.ufrgs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SilentModeTimerActivity extends Activity {
    /** Called when the activity is first created. */
	private String strIni = "";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Capture our button from layout
        Button button = (Button)findViewById(R.id.button1);
        strIni = button.getText().toString();
        // Register the onClick listener with the implementation above
        button.setOnClickListener(mCorkyListener);
    }
    // Create an anonymous implementation of OnClickListener
    private OnClickListener mCorkyListener = new OnClickListener() {
        public void onClick(View v) {
        	 Button myButton = (Button)v;
        	 //showMyDialog();
        	
        	 
          // do something when the button is clicked
        	AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

        	am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        	
       		char text[] = "silent".toCharArray();
       		TextView te = (TextView) findViewById(R.id.textView4);
        	te.setText(text,0,text.length);
        	
       		EditText hora = (EditText) findViewById(R.id.editText1);
       		EditText minuto = (EditText) findViewById(R.id.editText2);

        	String strHora = hora.getText().toString();
        	String strMin = minuto.getText().toString();
        	int miliHora = Integer.parseInt(strHora) * 1000 * 3600;
        	int miliMin = Integer.parseInt(strMin) * 1000 * 60;
        	int m2=1;m2=m2 + 3;
        	new CountDownTimer(miliHora + miliMin, 1000)  {
				
				@Override
				public void onTick(long millisUntilFinished) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onFinish() {
					// TODO Auto-generated method stub

		        	AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

		        	am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
	           		char text[] = "done!".toCharArray();
	           		TextView te = (TextView) findViewById(R.id.textView4);
	            	te.setText(text,0,text.length);
					
				}
			}.start();
        }
    };
//nao chamo do evento
    private void setOutput(char text[])
    {
    	EditText te = (EditText) findViewById(R.id.editText1);
    	te.setText(text,0,text.length);
    }
    private void showMyDialog()
    {
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Ta de brincadeira? Ve se para de estorva! vai parar?")
    	       .setCancelable(false)
    	       .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
   	                	dialog.cancel();
    	           }
    	       })
    	       .setNegativeButton("Não", new DialogInterface.OnClickListener() {
    	           public void onClick(DialogInterface dialog, int id) {
    	        	   SilentModeTimerActivity.this.finish();
    	           }
    	       });
    	AlertDialog alert = builder.create();
    	alert.show();
    }
}