package com.ui;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bookmanage.R;
import com.bookmanage.R.id;
import com.control.Controller;
import com.model.Book;
import com.model.BookList;

public class SelectActivity extends Activity {
	private List<Book> booklist = new ArrayList<Book>();//in store for books
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//set no title
		setContentView(R.layout.activity_select);
		Controller control = new Controller();
		BookList list = control.selectALlBook();//change method
		for(int i = 0; i< list.size(); i++) {
			booklist.add(list.get(i));
		}
		BookAdapter adapter = new BookAdapter(SelectActivity.this,R.layout.book_item,booklist);
		ListView listView = (ListView) findViewById(id.booklist);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,int positon, long id) {
						Book book = booklist.get(positon);
						String msg = "number: " + book.getId() + "\nname: " + book.getName()+ "\nprice: " + book.getPrice();
						Toast.makeText(SelectActivity.this, msg, Toast.LENGTH_LONG).show();
					}
		});
		//define the long click matter
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				return false;
			}
		});
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
	}
}

class BookAdapter extends ArrayAdapter<Book> {
	private int resourceId;
	public BookAdapter(Context context, int resource, List<Book> objects) {
		super(context, resource, objects);
		resourceId = resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Book book = getItem(position);
		View view;
		ViewHolder viewHolder;
		if(convertView == null) {
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.bookNum = (TextView) view.findViewById(R.id.bookNum);
			viewHolder.bookName = (TextView) view.findViewById(R.id.bookName);
			viewHolder.bookPrice = (TextView) view.findViewById(R.id.bookPrice);
			view.setTag(viewHolder);
		}else{
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.bookNum.setText(book.getId());
		viewHolder.bookName.setText(book.getName());
		viewHolder.bookPrice.setText(book.getPrice());
		return view;
	}
	class ViewHolder{
		TextView bookNum;
		TextView bookName;
		TextView bookPrice;
	}
}
