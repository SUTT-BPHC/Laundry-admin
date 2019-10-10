package com.example.laundryadmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddLaundryFragment extends Fragment {

	public AddLaundryFragment(){};

	FloatingActionButton fabDone;
	EditText roomEditText;
	EditText amountEditText;

	private DatabaseReference mDatabase;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_add_laundry, container, false);

		fabDone = rootView.findViewById(R.id.add_laundry_done_fab);
		roomEditText = rootView.findViewById(R.id.add_laundry_room_no_edit_text);
		amountEditText = rootView.findViewById(R.id.add_laundry_amount_edit_text);

		fabDone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String roomNo = roomEditText.getText().toString().toUpperCase();
				Double amount = Double.parseDouble(amountEditText.getText().toString());
				String curDate = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

				//TODO: Add code to upload data to database (making sure verification is done)
				mDatabase = FirebaseDatabase.getInstance().getReference();
				mDatabase.child("Laundry orders").child(roomNo).child("amount").setValue(amount);
				mDatabase.child("Laundry orders").child(roomNo).child("done").setValue(false);
				mDatabase.child("Laundry orders").child(roomNo).child("givenDate").setValue(curDate);

				Snackbar.make(rootView.findViewById(R.id.add_laundry_coordinator), getString(R.string.order_added), Snackbar.LENGTH_LONG).show();
				roomEditText.setText("");
				amountEditText.setText("");
                roomEditText.requestFocus();
			}
		});
		return rootView;
	}
}
