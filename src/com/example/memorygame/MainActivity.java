package com.example.memorygame;


import java.util.Random;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	public static String setDifficulty;
	protected Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, bt11, bt12, 
	bt13, bt14, bt15, bt16, bt17, bt18, bt19, bt20,
	btSettings, btBackground;
	protected TextView tvDone, tvLeft, tvRightPick;
	protected int[] images, shuffled;
	protected int done, left,
	i, btId, nextPic,
	randomNumber, aux,clicks,
	clickCounter, clickLeft, rightPair;
	public int difficulty;
	protected Random ran;
	protected String[] nameList, messageList;
	protected String btNumber, imageName, nameAux, name, messText;
	protected MediaPlayer soundTurn, soundRight, soundWrong;
	protected Intent settingsActivity, messageActivity; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		difficulty = 12;
		initializeGame();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		btNumber = ((Button)v).getTag().toString();
		btBackground = (Button) v;

		if(!btNumber.equals("settings")){
			cardClicked();		
		}
		else{			
			settingsActivity = new Intent(this, ThirdActivity.class);
			settingsActivity.putExtra(ThirdActivity.settings, difficulty);
			startActivityForResult(settingsActivity,1);
			//getSettings(difficulty);
		}
	}
	protected void cardClicked(){
		name = nameAux;
		btId = Integer.parseInt(btNumber);
		nextPic = images[btId];
		nameAux = nameList[btId];

		clicks++;
		clickCounter++;
		clickLeft--;

		tvDone.setText("Tiradas realizadas: " + clickCounter + "/20");
		tvLeft.setText("Tiradas restantes: " + clickLeft + "/20");
		tvRightPick.setText("Numero de aciertos: " + rightPair);

		soundTurn = MediaPlayer.create(this, R.raw.turnpage);
		soundTurn.seekTo(0);
		soundTurn.start();
		
		soundTurn.setOnCompletionListener(new OnCompletionListener() {   
			@Override
			public void onCompletion(MediaPlayer media) {
				// TODO Auto-generated method stub
				media.release();
			}
		});

		if(clicks <= 2){	
			btBackground.setBackgroundResource(nextPic);			

			if(name == nameAux){
				clicks = 0;
				rightPair++;
				nameAux = "";
				
				soundRight = MediaPlayer.create(this, R.raw.ding);
				soundRight.seekTo(0);
				soundRight.start();
				soundRight.setOnCompletionListener(new OnCompletionListener() {   
					@Override
					public void onCompletion(MediaPlayer media) {
						// TODO Auto-generated method stub
						media.release();
					}
				});

				if(rightPair == 6){
					getMessageList();
					Intent messageActivity = new Intent(this, SecondActivity.class);
					messageActivity.putExtra(SecondActivity.message, messText);
					messageActivity.putExtra(SecondActivity.pairs, rightPair);
					messageActivity.putExtra(SecondActivity.counter, clickCounter);
					startActivity(messageActivity);
					getSettings(difficulty);
				}
			}
			else{ 
				if(clickLeft > 0){
					if(name != ""){
						
						soundWrong = MediaPlayer.create(this, R.raw.error);
						soundWrong.seekTo(0);
						soundWrong.start();
						soundWrong.setOnCompletionListener(new OnCompletionListener() {   
							@Override
							public void onCompletion(MediaPlayer media) {
								// TODO Auto-generated method stub
								media.release();
							}
						});
					}
				}
				else {
					getMessageList();
					Intent messageActivity = new Intent(this, SecondActivity.class);
					messageActivity.putExtra(SecondActivity.message, messText);
					messageActivity.putExtra(SecondActivity.pairs, rightPair);
					messageActivity.putExtra(SecondActivity.counter, clickCounter);
					startActivity(messageActivity);
					getSettings(difficulty);
				}
			}
		}
		else {
			clickCounter--;
			setCardBackground();
			clicks = 1; //Vale 1 porque es la tercera tirada, que equivale a la primera
			rightPair = 0;
			btBackground.setBackgroundResource(nextPic);
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode==1)
		{
			if (resultCode == RESULT_OK)
			{
				difficulty = data.getIntExtra(ThirdActivity.retorno,12);
				getSettings(difficulty);	
			}
		}
		super.onActivityResult(requestCode, resultCode, data);		
	}
	//Method to create an array of messages
	protected void getMessageList(){
		messageList = new String[4];

		messageList[0] = "Upss!";
		messageList[1] = "Neh";
		messageList[2] = "yeah!";
		messageList[3] = "Woohoo!!";

		if(rightPair < 3)
			messText = messageList[0];
		else if (rightPair < 5) {
			messText = messageList[1];
		}
		else if (rightPair == 5) {
			messText = messageList[2];
		}
		else if (rightPair == 6) {
			messText = messageList[3];
		}
	}
	//Method to set the default setting of the application
	protected void initializeGame() {
		bt1  = (Button) findViewById(R.id.button01);
		bt2  = (Button) findViewById(R.id.button02);
		bt3  = (Button) findViewById(R.id.button03);
		bt4  = (Button) findViewById(R.id.button04);
		bt5  = (Button) findViewById(R.id.button05);
		bt6  = (Button) findViewById(R.id.button06);
		bt7  = (Button) findViewById(R.id.button07);
		bt8  = (Button) findViewById(R.id.button08);
		bt9  = (Button) findViewById(R.id.button09);
		bt10 = (Button) findViewById(R.id.button10);
		bt11 = (Button) findViewById(R.id.button11);
		bt12 = (Button) findViewById(R.id.button12);
		bt13 = (Button) findViewById(R.id.button13);
		bt14 = (Button) findViewById(R.id.button14);
		bt15 = (Button) findViewById(R.id.button15);
		bt16 = (Button) findViewById(R.id.button16);
		bt17 = (Button) findViewById(R.id.button17);
		bt18 = (Button) findViewById(R.id.button18);
		bt19 = (Button) findViewById(R.id.button19);
		bt20 = (Button) findViewById(R.id.button20);

		btSettings  = (Button) findViewById(R.id.buttonSettings);
		tvDone      = (TextView) findViewById(R.id.textViewDone);
		tvLeft      = (TextView) findViewById(R.id.textViewLeft);
		tvRightPick = (TextView) findViewById(R.id.textViewRightPick);

		bt1.setOnClickListener(this);
		bt2.setOnClickListener(this);
		bt3.setOnClickListener(this);
		bt4.setOnClickListener(this);
		bt5.setOnClickListener(this);
		bt6.setOnClickListener(this);
		bt7.setOnClickListener(this);
		bt8.setOnClickListener(this);
		bt9.setOnClickListener(this);
		bt10.setOnClickListener(this);
		bt11.setOnClickListener(this);
		bt12.setOnClickListener(this);
		bt13.setOnClickListener(this);
		bt14.setOnClickListener(this);
		bt15.setOnClickListener(this);
		bt16.setOnClickListener(this);
		bt17.setOnClickListener(this);
		bt18.setOnClickListener(this);
		bt19.setOnClickListener(this);
		bt20.setOnClickListener(this);

		btSettings.setOnClickListener(this);
		getSettings(difficulty);		

	}
	//Methods to initialize the game
	public void getSettings(int diff){
		getImagesList(diff);
		getRandomList();

		nextPic = 0;
		clickCounter = 0;
		clickLeft = 20;
		rightPair = 0;
		clicks = 0;

		tvDone.setText("Tiradas realizadas: " + clickCounter + "/20");
		tvLeft.setText("Tiradas restantes: " + clickLeft + "/20");
		tvRightPick.setText("Numero de aciertos: " + rightPair);

		setCardBackground();
	}
	//Method to set the default card background
	protected void setCardBackground(){
		bt1.setBackgroundResource(R.drawable.background_sm2);
		bt2.setBackgroundResource(R.drawable.background_sm2);
		bt3.setBackgroundResource(R.drawable.background_sm2);
		bt4.setBackgroundResource(R.drawable.background_sm2);
		bt5.setBackgroundResource(R.drawable.background_sm2);
		bt6.setBackgroundResource(R.drawable.background_sm2);
		bt7.setBackgroundResource(R.drawable.background_sm2);
		bt8.setBackgroundResource(R.drawable.background_sm2);
		bt9.setBackgroundResource(R.drawable.background_sm2);
		bt10.setBackgroundResource(R.drawable.background_sm2);
		bt11.setBackgroundResource(R.drawable.background_sm2);
		bt12.setBackgroundResource(R.drawable.background_sm2);
		bt13.setBackgroundResource(R.drawable.background_sm2);
		bt14.setBackgroundResource(R.drawable.background_sm2);
		bt15.setBackgroundResource(R.drawable.background_sm2);
		bt16.setBackgroundResource(R.drawable.background_sm2);
		bt17.setBackgroundResource(R.drawable.background_sm2);
		bt18.setBackgroundResource(R.drawable.background_sm2);
		bt19.setBackgroundResource(R.drawable.background_sm2);
		bt20.setBackgroundResource(R.drawable.background_sm2);
	}
	//Method to get a random array of images
	private void getRandomList(){
		ran = new Random();
		aux = 0;
		nameAux = "";

		for(i=0;i<difficulty;i++){
			randomNumber = ran.nextInt(difficulty - 1);

			aux = images[i];
			images[i] = images[randomNumber];
			images[randomNumber] = aux;

			nameAux = nameList[i];
			nameList[i] = nameList[randomNumber];
			nameList[randomNumber] = nameAux;
		}
		nameAux = "";
	}
	//Method to get an array of images
	private void getImagesList(int diff) {
		images = new int[diff];
		shuffled = new int[diff];
		nameList = new String[diff];

		if(diff == 12){
			setDifficulty12();
		}
		if(diff == 16){			
			bt13.setVisibility(View.VISIBLE);
			bt14.setVisibility(View.VISIBLE);
			bt15.setVisibility(View.VISIBLE);
			bt16.setVisibility(View.VISIBLE);

			setDifficulty16();
		}
		if(diff == 20){
			bt13.setVisibility(View.VISIBLE);
			bt14.setVisibility(View.VISIBLE);
			bt15.setVisibility(View.VISIBLE);
			bt16.setVisibility(View.VISIBLE);
			bt17.setVisibility(View.VISIBLE);
			bt18.setVisibility(View.VISIBLE);
			bt19.setVisibility(View.VISIBLE);
			bt20.setVisibility(View.VISIBLE);
			setDifficulty20();
		}	
	}
	//Methods to set the game's difficulty
	//-------------------------------------
	//Methods to set the difficulty of the game
	protected void setDifficulty12(){
		images[0]  = R.drawable.apple1;
		images[1]  = R.drawable.apple2;
		images[2]  = R.drawable.canon1;
		images[3]  = R.drawable.canon2;
		images[4]  = R.drawable.ford1;
		images[5]  = R.drawable.ford2;
		images[6]  = R.drawable.ibm1;
		images[7]  = R.drawable.ibm2;
		images[8]  = R.drawable.mercedes1;
		images[9]  = R.drawable.mercedes2;
		images[10] = R.drawable.microsoft1;
		images[11] = R.drawable.microsoft2;

		nameList[0] = "apple";
		nameList[1] = "apple";
		nameList[2] = "canon";
		nameList[3] = "canon";
		nameList[4] = "ford";
		nameList[5] = "ford";
		nameList[6] = "ibm";
		nameList[7] = "ibm";
		nameList[8] = "mercedes";
		nameList[9] = "mercedes";
		nameList[10] = "microsoft";
		nameList[11] = "microsoft";
	}

	protected void setDifficulty16(){
		images[0]  = R.drawable.apple1;
		images[1]  = R.drawable.apple2;
		images[2]  = R.drawable.canon1;
		images[3]  = R.drawable.canon2;
		images[4]  = R.drawable.ford1;
		images[5]  = R.drawable.ford2;
		images[6]  = R.drawable.ibm1;
		images[7]  = R.drawable.ibm2;
		images[8]  = R.drawable.mercedes1;
		images[9]  = R.drawable.mercedes2;
		images[10] = R.drawable.microsoft1;
		images[11] = R.drawable.microsoft2;
		images[12] = R.drawable.nike1;
		images[13] = R.drawable.nike2;
		images[14] = R.drawable.pepsi1;
		images[15] = R.drawable.pepsi2;

		nameList[0] = "apple";
		nameList[1] = "apple";
		nameList[2] = "canon";
		nameList[3] = "canon";
		nameList[4] = "ford";
		nameList[5] = "ford";
		nameList[6] = "ibm";
		nameList[7] = "ibm";
		nameList[8] = "mercedes";
		nameList[9] = "mercedes";
		nameList[10] = "microsoft";
		nameList[11] = "microsoft";
		nameList[12] = "nike";
		nameList[13] = "nike";
		nameList[14] = "pepsi";
		nameList[15] = "pepsi";
	}

	protected void setDifficulty20(){
		images[0]  = R.drawable.apple1;
		images[1]  = R.drawable.apple2;
		images[2]  = R.drawable.canon1;
		images[3]  = R.drawable.canon2;
		images[4]  = R.drawable.ford1;
		images[5]  = R.drawable.ford2;
		images[6]  = R.drawable.ibm1;
		images[7]  = R.drawable.ibm2;
		images[8]  = R.drawable.mercedes1;
		images[9]  = R.drawable.mercedes2;
		images[10] = R.drawable.microsoft1;
		images[11] = R.drawable.microsoft2;
		images[12] = R.drawable.nike1;
		images[13] = R.drawable.nike2;
		images[14] = R.drawable.pepsi1;
		images[15] = R.drawable.pepsi2;
		images[16] = R.drawable.shell1;
		images[17] = R.drawable.shell2;
		images[18] = R.drawable.wolks1;
		images[19] = R.drawable.wolks2;


		nameList[0] = "apple";
		nameList[1] = "apple";
		nameList[2] = "canon";
		nameList[3] = "canon";
		nameList[4] = "ford";
		nameList[5] = "ford";
		nameList[6] = "ibm";
		nameList[7] = "ibm";
		nameList[8] = "mercedes";
		nameList[9] = "mercedes";
		nameList[10] = "microsoft";
		nameList[11] = "microsoft";
		nameList[12] = "nike";
		nameList[13] = "nike";
		nameList[14] = "pepsi";
		nameList[15] = "pepsi";
		nameList[16] = "shell";
		nameList[17] = "shell";
		nameList[18] = "wolks";
		nameList[19] = "wolks";
	}

}
