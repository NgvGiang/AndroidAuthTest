package vn.edu.usth.firebasesdkauthtest;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

//this fragment is used to enter ( Create, Push ) personal info
public class EnterInfoFragment extends DialogFragment {
    private EditText fullNameEditText, genderEditText, birthYearEditText;
    private Button saveButton, cancelButton;

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

        // Sự kiện nút Lưu
        saveButton.setOnClickListener(v -> {
            // Xử lý lưu thông tin
        });

        // Sự kiện nút Hủy
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return rootView;
    }
}