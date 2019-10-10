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

import java.util.HashMap;
import java.util.Map;

public class LaundryCompleteFragment extends Fragment {

	public LaundryCompleteFragment(){};

	FloatingActionButton fabDone;
	EditText roomEditText;

	private DatabaseReference mDatabase;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_laundry_complete, container, false);

		fabDone = rootView.findViewById(R.id.complete_laundry_done_fab);
		roomEditText = rootView.findViewById(R.id.complete_laundry_room_no_edit_text);

		fabDone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String roomNo = roomEditText.getText().toString().toUpperCase();
				mDatabase = FirebaseDatabase.getInstance().getReference();

				Map<String, Object> map = new HashMap<>();
				map.put("isDone", "true");

				mDatabase.child("Laundry orders").child(roomNo).updateChildren(map);
				Snackbar.make(rootView.findViewById(R.id.complete_laundry_coordinator), getString(R.string.order_mark_complete), Snackbar.LENGTH_LONG).show();
				roomEditText.setText("");
			}
		});

		return rootView;
	}
}
