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

public class DeleteActivity extends Activity implements OnClickListener{
	private EditText bookname;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_delete);
		bookname = (EditText)findViewById(R.id.deletename);
		Button delete = (Button)findViewById(R.id.deleteNamebt);
		delete.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		String bookname2 = bookname.getText().toString();
		Controller control = new Controller();
		if(bookname2.equals("")){
			new Builder(DeleteActivity.this).setMessage("The delte book should not be null").show();
		}else{
			if(control.deleteBook(bookname2)) {
				bookname.setText("");
				buildDialog();
			}else{
				bookname.setText("");
				new Builder(this).setMessage("No book").show();
			}
		}
	}
	
	private void buildDialog() {
		Builder builder = new Builder(this);
		builder.setTitle("delete successfully,continue?");
		builder.setNegativeButton("return to home page", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		builder.setPositiveButton("continue deleting", null);
		builder.show();
	}
}
