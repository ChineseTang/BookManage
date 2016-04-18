package com.ui;

import com.bookmanage.R;
import com.control.Controller;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
public class UpdateActivity extends Activity implements OnClickListener {
	private EditText name;
	private EditText id;
	private EditText price;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_update);
		name = (EditText)findViewById(R.id.bookupdateName);
		id = (EditText)findViewById(R.id.bookupdateNum);
		price = (EditText)findViewById(R.id.bookupdatePrice);
		
		Button updateBtn = (Button)findViewById(R.id.updateBt);
		updateBtn.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		String bookName = name.getText().toString();
		String bookNum = id.getText().toString();
		String bookPrice = price.getText().toString();
		
		Controller control = new Controller();
		
		if("".equals(bookNum) || "".equals(bookPrice) || "".equals(bookName)) {
			new Builder(this).setMessage("The book infor should not be null").show();
		}else{
			if(control.updateBook(bookNum, bookName, bookPrice)) {
				id.setText("");
				name.setText("");
				price.setText("");
				showMessage();
			}else{
				new Builder(this).setMessage("the book number does not exist, please reinput").show();
			}
		}
	}
	/**
	 * if succeed, call the function
	 */
	private void showMessage() {
		Builder builder = new Builder(this);
		builder.setTitle("Update successfully,continue?");
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
