package vn.edu.usth.firebasesdkauthtest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import vn.edu.usth.firebasesdkauthtest.Model.Person;


public class PersonalInfoFragment extends Fragment {
    TextView fullNameTextView,genderTextView,birthYearTextView;
    Button updateInfoButton;
    private DatabaseReference personRef;
    private FirebaseAuth mAuth;
    private String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_personal_info, container, false);
        TextView fullNameTextView = view.findViewById(R.id.fullNameTextView);
        genderTextView = view.findViewById(R.id.genderTextView);
        birthYearTextView = view.findViewById(R.id.birthYearTextView);
        updateInfoButton = view.findViewById(R.id.updateInfoButton);
        updateInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnterInfoFragment dialog = new EnterInfoFragment();
                dialog.show(getParentFragmentManager(), "UpdatePersonInfoDialog");
            }
        });
        //read and show the data for personal info from realtime database
        mAuth = FirebaseAuth.getInstance();
        userId = mAuth.getCurrentUser().getUid();
        personRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("person");
        //this function will instantly pull the data from db if there is any change in database
        personRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Person person = snapshot.getValue(Person.class);
                if (person!= null ){
                    //set TextView appropriately
                    fullNameTextView.setText(person.getName());
                    genderTextView.setText(person.getGender());
                    birthYearTextView.setText(String.valueOf(person.getBirthYear()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}