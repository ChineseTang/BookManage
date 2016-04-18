package com.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.bookmanage.R;
import com.db.DBconnect;

public class MainActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//not show the title menu in the activity
		setContentView(R.layout.activity_main);
		DBconnect.setContext(this.getApplicationContext());
		Button insertbt = (Button) findViewById(R.id.insert);
		Button updatebt = (Button) findViewById(R.id.update);
		Button selectbt = (Button) findViewById(R.id.select);
		Button deletebt = (Button) findViewById(R.id.delete);
		insertbt.setOnClickListener(this);
		updatebt.setOnClickListener(this);
		selectbt.setOnClickListener(this);
		deletebt.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		Intent intent = new Intent();
		switch(id) {
		case R.id.insert:
			intent.setClass(this, InsertActivity.class);
			startActivity(intent);
			break;
		case R.id.delete:
			intent.setClass(this, DeleteActivity.class);
			startActivity(intent);
			break;
		case R.id.update:
			intent.setClass(this, UpdateActivity.class);
			startActivity(intent);
			break;
		case R.id.select:
			intent.setClass(this, SelectActivity.class);
			startActivity(intent);
			break;
		}
	}
}
