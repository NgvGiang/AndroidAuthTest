package vn.edu.usth.firebasesdkauthtest;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import vn.edu.usth.firebasesdkauthtest.Model.Person;

//this fragment is used to enter ( Create, Push ) personal info
public class EnterInfoFragment extends DialogFragment {
    private EditText fullNameEditText, genderEditText, birthYearEditText;
    private Button saveButton, cancelButton;
    private FirebaseAuth mAuth;
    DatabaseReference personRef;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_enter_info, container, false);
        fullNameEditText = rootView.findViewById(R.id.fullNameEditText);
        genderEditText = rootView.findViewById(R.id.genderEditText);
        birthYearEditText = rootView.findViewById(R.id.birthYearEditText);
        saveButton = rootView.findViewById(R.id.saveButton);
        cancelButton = rootView.findViewById(R.id.cancelButton);
        mAuth = FirebaseAuth.getInstance();
        //init firebase
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            personRef = FirebaseDatabase.getInstance().getReference("users").child(userId).child("person");
        } else {
            Toast.makeText(getContext(), "Người dùng chưa đăng nhập", Toast.LENGTH_SHORT).show();
            dismiss();
        }
        // Save button
        saveButton.setOnClickListener(v -> {
            savePersonInfo(); //call CRUD function
        });

        // Cancel Button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        return rootView;
    }
    //Create function ( CRUD )
    private void savePersonInfo() {
        String fullName = fullNameEditText.getText().toString().trim();
        String gender = genderEditText.getText().toString().trim();
        String birthYearStr = birthYearEditText.getText().toString().trim();

        // Kiểm tra dữ liệu đầu vào
        if (fullName.isEmpty() || gender.isEmpty() || birthYearStr.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        int birthYear;
        try {
            birthYear = Integer.parseInt(birthYearStr);
        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Năm sinh không hợp lệ", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tạo đối tượng Person
        Person person = new Person(fullName, gender, birthYear);

        // Lưu vào Firebase
        personRef.setValue(person)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(), "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                        dismiss();
                    } else {
                        Toast.makeText(getContext(), "Cập nhật thông tin thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}