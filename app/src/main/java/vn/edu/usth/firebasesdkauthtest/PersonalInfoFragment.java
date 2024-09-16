package vn.edu.usth.firebasesdkauthtest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


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

        return view;
    }
}