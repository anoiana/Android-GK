package com.example.studentinformationmanagement;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.studentinformationmanagement.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.checkerframework.checker.nullness.qual.NonNull;
public class LoginActivity extends AppCompatActivity {
    EditText edtEmai,edtPassword;
    Button btnQuenMK,btnlogin;
    boolean check = false;
    public FirebaseAuth mAuth;
    ProgressBar progressBar;
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent( LoginActivity.this,Activitymain.class);
            startActivity(intent);
            finish();
        }
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmai = findViewById(R.id.edtEmai);
        edtPassword = findViewById(R.id.edtPassword);
//        btnQuenMK = findViewById(R.id.btnQuenMK);
        btnlogin = findViewById(R.id.btnlogin);
//        btnDangKy = findViewById(R.id.btnDangKy);
//        unseepasswpord = findViewById(R.id.unseepasswpord);
        mAuth = FirebaseAuth.getInstance();
//        progressBar = findViewById(R.id.progressBar);
//        unseepasswpord.setOnClickListener(view ->{
//            if (check){
//                matkhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                unseepasswpord.setImageResource(R.drawable.eyeofff);
//            } else {
//                matkhau.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                unseepasswpord.setImageResource(R.drawable.password_show);
//            }
//            check = !check;
//        });
        btnlogin.setOnClickListener(view -> {
//            progressBar.setVisibility(View.VISIBLE);
            String email,password;
            email = edtEmai.getText().toString();
            password = edtPassword.getText().toString();
            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Toast.makeText( LoginActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            if (task.isSuccessful()) {
                                Toast.makeText( LoginActivity.this, "Đăng nhập thành công",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, Activitymain.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText( LoginActivity.this, "Đăng nhập thất bại",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        });
    }
}
