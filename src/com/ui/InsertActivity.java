package com.ui;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.bookmanage.R;
import com.control.Controller;

public class InsertActivity extends Activity implements OnClickListener{

	private EditText name;
	private EditText id;
	private EditText price;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_insert);
		
		name = (EditText)findViewById(R.id.bookinsertName);
		id = (EditText)findViewById(R.id.bookinsertNum);
		price = (EditText)findViewById(R.id.bookinsertPrice);
		
		Button insertBtn = (Button)findViewById(R.id.insertBt);
		insertBtn.setOnClickListener(this);
	}
	//call the controller layer
	@Override
	public void onClick(View v) {
		String bookName = name.getText().toString();
		String bookNum = id.getText().toString();
		String bookPrice = price.getText().toString();
		
		Controller control = new Controller();
		
		if("".equals(bookNum) || "".equals(bookPrice) || "".equals(bookName)) {
			new Builder(InsertActivity.this).setMessage("The book infor should not be null").show();
		}else{
			if(control.addBook(bookNum, bookName, bookPrice)) {
				id.setText("");
				name.setText("");
				price.setText("");
				showMessage();
			}else{
				new Builder(InsertActivity.this).setMessage("the book has existed,please reinput").show();
			}
		}
	}
	/**
	 * if succeed, call the function
	 */
	private void showMessage() {
		Builder builder = new Builder(InsertActivity.this);
		builder.setTitle("Insert successfully,continue?");
		builder.setNegativeButton("return to HomePage",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});
		builder.setPositiveButton("continue inserting", null);
		builder.show();
	}
}
