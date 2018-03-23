package com.teamshunya.silencio.Activities.ShowActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.teamshunya.silencio.Database.db;
import com.teamshunya.silencio.R;


public class Addnums extends ActionBarActivity {
    EditText Number1,Number2;
    db dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_otp);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dbhandler= new db(this,null,null,1);
        if(dbhandler.number()==2) {
            String phoneNo1 = dbhandler.databaseToPhoneFirst();
            String phoneNo2 = dbhandler.databaseToPhoneSecond();
            Number1=(EditText)findViewById(R.id.Number1);
            //Number2=(EditText)findViewById(R.id.Number2);
            Number1.setHint(phoneNo1);
            Number2.setHint(phoneNo2);
        }

    }

    public void addcontact1(View view)
    {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void addcontact2(View view)
    {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, 1);
    }


    public void Onclick(View view)
    {
        Number1=(EditText)findViewById(R.id.Number1);
        //Number2=(EditText)findViewById(R.id.Number2);
        String n1=Number1.getText().toString();
        String n2=Number2.getText().toString();
        if(n1.length()!=10 || n2.length()!=10)
        {
            Toast.makeText(Addnums.this,"Invalid Phone Number. Please enter both 10 digit phone numbers....", Toast.LENGTH_LONG).show();
        }
        else {
            Intent i = new Intent(this, SOS.class);
            i.putExtra("Number1", n1);
            i.putExtra("Number2", n2);
            startActivity(i);
            overridePendingTransition  (R.anim.enter_from_left, R.anim.exit_to_right);
        }
    }
}
