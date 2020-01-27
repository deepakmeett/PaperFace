package com.meet.paperface.activity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.meet.paperface.R;
public class Shopkeeper_Activity extends AppCompatActivity {

    private EditText id_Shopkeeper;
    private EditText password;
    private Button login;
    private int click = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_shopkeeper );
        id_Shopkeeper = findViewById( R.id.email_ShopKeeper );
        password = findViewById( R.id.password_ShopKeeper );
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        if (click == 1 ){
            password.setTransformationMethod( new PasswordTransformationMethod() );
            click++;
        }
        password.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click==2){
                    password.setTransformationMethod( null );
                    click--;
                }else if (click==1){
                    password.setTransformationMethod( new PasswordTransformationMethod() );
                    click++;
                }
            }
        } );
        
        login = findViewById( R.id.log_in_ShopKeeper );
        login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_sk = id_Shopkeeper.getText().toString();
                String password_sk = password.getText().toString();
                if (email_sk.isEmpty()) {
                    Toast.makeText( Shopkeeper_Activity.this, "Field can't be empty", Toast.LENGTH_SHORT ).show();
                } else if (password_sk.isEmpty()) {
                    Toast.makeText( Shopkeeper_Activity.this, "Field can't be empty", Toast.LENGTH_SHORT ).show();
                } else if (!email_sk.equals( "diamond@gmail.com" ) || !password_sk.equals( "diamondstart@up" )) {
                    Toast.makeText( Shopkeeper_Activity.this, "Credential does not match", Toast.LENGTH_SHORT ).show();
                } else {
                    Intent intent = new Intent( Shopkeeper_Activity.this, Recycler_Activity.class );
                    startActivity( intent );
                }

            }
        } );

    }
}
