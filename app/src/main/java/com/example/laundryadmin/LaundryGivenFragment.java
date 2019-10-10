package com.example.laundryadmin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LaundryGivenFragment extends Fragment {

    FloatingActionButton fabDone;
    EditText roomEditText;
    private DatabaseReference mDatabase;

    public LaundryGivenFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_laundry_given, container, false);

        fabDone = rootView.findViewById(R.id.given_laundry_done_fab);
        roomEditText = rootView.findViewById(R.id.given_laundry_room_no_edit_text);

        fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String roomNo = roomEditText.getText().toString().toUpperCase();

                mDatabase = FirebaseDatabase.getInstance().getReference();
                mDatabase.child("Laundry orders").child(roomNo).removeValue();

                Snackbar.make(rootView.findViewById(R.id.laundry_given_coordinator), getString(R.string.order_mark_given), Snackbar.LENGTH_LONG).show();

                roomEditText.setText("");
            }
        });

        return rootView;
    }

}
