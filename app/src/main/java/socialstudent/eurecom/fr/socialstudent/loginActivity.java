package socialstudent.eurecom.fr.socialstudent;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class    loginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button loginButton = null ;
    EditText emailEdit = null ;
    EditText passwordEdit = null ;
    int radhwen ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.loginButton);
        emailEdit = (EditText) findViewById(R.id.emailEdit) ;
        passwordEdit =(EditText) findViewById(R.id.passwordEdit) ;



    }

    public void onClick (View view){

        switch (view.getId()){
            case R.id.loginButton :
                mAuth = FirebaseAuth.getInstance();
                String email = emailEdit.getText().toString();
                String password = passwordEdit.getText().toString();


                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("LOGIN", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("LOGIN", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(loginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }

                            }
                        });
                break;
            case R.id.registerButton :
                Intent i = new Intent(loginActivity.this,registerActivity.class);
                startActivity(i);
            default:
                break ;


        }
    }

    private void updateUI(FirebaseUser user) {
        if(user!=null){
            Intent i = new Intent(loginActivity.this,mainActivity.class);
            i.putExtra("user",user);
            startActivity(i);
        } else if (user==null)  {
            AlertDialog.Builder builder= new AlertDialog.Builder(getApplication());
            builder.setMessage("Wrong ID or Password").setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            AlertDialog dialog = builder.create();

        }
    }


}
