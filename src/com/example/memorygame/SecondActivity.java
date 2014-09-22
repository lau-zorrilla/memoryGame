package com.example.memorygame;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends Activity implements OnClickListener{
	
	public static String message = "Empty",
						 pairs = "None",
						 counter = "Zero";
	protected int rights, clickCounter;
	protected String messageContent;
	protected MediaPlayer soundEnd;
	TextView tv;
	ImageView faceDisplay;
    Button btReturn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		tv = (TextView) findViewById(R.id.textViewMessage);		
		
		messageContent = getIntent().getStringExtra(message);
		rights = getIntent().getIntExtra(pairs, 0);
		clickCounter = getIntent().getIntExtra(counter, 0);
		
		tv.setText( messageContent + " Aciertos: " + rights);
		
		faceDisplay = (ImageView) findViewById(R.id.imageViewFace);
		
		soundEnd = MediaPlayer.create(this, R.raw.goodend);
		soundEnd.seekTo(0);
		soundEnd.start();
		soundEnd.setOnCompletionListener(new OnCompletionListener() {   
            @Override
            public void onCompletion(MediaPlayer media) {
                // TODO Auto-generated method stub
                media.release();
            }
		});
		
        if(rights < 3){
        	faceDisplay.setImageResource(R.drawable.cry);
        }
        else if(rights <= 5){
			faceDisplay.setImageResource(R.drawable.sad);
		}
		else if(rights == 5){
			faceDisplay.setImageResource(R.drawable.happy);
		}
		else {
			faceDisplay.setImageResource(R.drawable.loving);
		}
        btReturn = (Button) findViewById(R.id.buttonRetrn);
        btReturn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		this.finish();
	}

}
