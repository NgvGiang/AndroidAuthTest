package vn.edu.usth.firebasesdkauthtest;

import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.credentials.Credential;
import androidx.credentials.CredentialManager;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.CustomCredential;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    Button loginButton;
    private FirebaseAuth mAuth;

    CredentialManager credentialManager;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.googleAuth);
        mAuth = FirebaseAuth.getInstance();

        credentialManager = CredentialManager.create(this);
        

        GetSignInWithGoogleOption getSignInWithGoogleOption = new GetSignInWithGoogleOption.Builder(getString(R.string.Oauth_client_id))
                .build();
        GetCredentialRequest getCredentialRequest = new GetCredentialRequest.Builder()
                .addCredentialOption(getSignInWithGoogleOption) // request google login
                //more options can be added here, such as email login, etc.
                .build();
        //click event for logging in with google
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                credentialManager.getCredentialAsync(
                        LoginActivity.this,
                        getCredentialRequest,
                        new CancellationSignal(),
                        LoginActivity.this.getMainExecutor(),
                        new CredentialManagerCallback<GetCredentialResponse, GetCredentialException>(){
                            @Override
                            public void onResult(GetCredentialResponse result) {
                                handleSignIn(result);
                            }
                            @Override
                            public void onError(GetCredentialException e) {
                                Log.e(TAG, "Error retrieving credential", e);
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }

                        }
                );
            }
        });

    }
    // sign in with google, then google return an idToken, send this token to firebase to authenticate
    private void handleSignIn(GetCredentialResponse result) {
        Log.d(TAG, "handleSignIn called");
        Credential credential = result.getCredential();

        if (credential instanceof CustomCredential) {
            if (GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL.equals(credential.getType())) {
                GoogleIdTokenCredential googleIdTokenCredential = GoogleIdTokenCredential.createFrom(((CustomCredential) credential).getData());
                String idToken = googleIdTokenCredential.getIdToken();
                firebaseAuthWithGoogle(idToken);
            }
        }
    }

    //firebase authenticate using above idToken
    private void firebaseAuthWithGoogle(String idToken) {
        Log.d(TAG, "firebaseAuthWithGoogle called with ID Token: " + idToken);
        mAuth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithCredential:success");
                        // Đăng nhập thành công, chuyển tới MainActivity
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
//                        Log.w(TAG, "signInWithCredential:failure", task.getException());
//                        // Đăng nhập thất bại
//                        try {
//                            throw task.getException();
//                        } catch (FirebaseAuthInvalidCredentialsException |
//                                 FirebaseAuthInvalidUserException e) {
//                            Log.e(TAG, "Invalid credentials: ", e);
//                        } catch (Exception e) {
//                            Log.e(TAG, "Sign-in failed: ", e);
//                        }
                    }
                });
    }

    //firebase authenticate with email and password
    private void firebaseAuthWithEmailAndPassword(String email,String password){

    }

}
