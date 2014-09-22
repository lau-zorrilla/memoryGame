package com.example.memorygame;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends Activity implements OnClickListener{
	
	public static String settings = "default";
	public static String retorno = "return";
	protected int men;
	Button diff12, diff16, diff20, done;
	TextView tvDif;
	int gameDifficulty, cardDefaultBk;
	Intent data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);
				
		tvDif = (TextView) findViewById(R.id.textViewHola);
		gameDifficulty = getIntent().getIntExtra(ThirdActivity.settings, 0);
		tvDif.setText("Dificultad: " + gameDifficulty);
		
		done = (Button) findViewById(R.id.buttonDone);
		diff12 = (Button) findViewById(R.id.button12Cards);
		diff16 = (Button) findViewById(R.id.button16Cards);		
		diff20 = (Button) findViewById(R.id.button20Cards);
		
		done.setOnClickListener(this);
		diff12.setOnClickListener(this);
		diff16.setOnClickListener(this);
		diff20.setOnClickListener(this);		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if ( v.getId() == R.id.button12Cards)
		{
			gameDifficulty = 12;
		}
		else if ( v.getId() == R.id.button16Cards)
		{
			gameDifficulty = 16;
		}
		else if ( v.getId() == R.id.button20Cards)
		{
			gameDifficulty = 20;
		}
		else if ( v.getId() == R.id.buttonDone)
		{
			data = new Intent();
			data.putExtra(ThirdActivity.retorno,(int)gameDifficulty);
			setResult(RESULT_OK, data);
			finish();
		}
		tvDif.setText("Dificultad: " + gameDifficulty);	
	}

}
