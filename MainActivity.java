package com.example.videostreamingservicesandsubscribers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText txtCompanyName,txtStockPrice,txtSubscribers,txtVSID;
    Button Addbtn,Showbtn,Updatebtn,Deletebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        txtCompanyName = (EditText)findViewById(R.id.editTextCompanyName);
        txtStockPrice = (EditText)findViewById(R.id.editTextStockPrice);
        txtSubscribers = (EditText)findViewById(R.id.editTextSubscriber);
        txtVSID =(EditText)findViewById(R.id.editTextVSID);
        Addbtn =(Button)findViewById(R.id.btnAdd);
        Showbtn =(Button)findViewById(R.id.btnShow);
        Updatebtn=(Button)findViewById(R.id.btnUpdate);
        Deletebtn=(Button)findViewById(R.id.btnDelete);
        InsertVideoStream();
        UpdateVideoStream();
        DeleteVideoStream();
        ShowAllViews();
    }
    public void InsertVideoStream(){
        Addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean VSSinserted = db.InsertService(txtCompanyName.getText().toString(),txtStockPrice.getText().toString(),txtSubscribers.getText().toString());
                if(VSSinserted == true)
                    Toast.makeText(MainActivity.this,"Video Streaming Service added",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Video Streaming service not added",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void UpdateVideoStream(){
        Updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean VSSupdated = db.UpdateService(txtVSID.getText().toString(),txtCompanyName.getText().toString(),txtStockPrice.getText().toString(),txtSubscribers.getText().toString());
                if(VSSupdated ==true)
                    Toast.makeText(MainActivity.this,"Video Streaming Service updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Video Streaming services not updated",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void DeleteVideoStream(){
        Deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer VSSdeleted = db.DeleteService(txtVSID.getText().toString());
                if(VSSdeleted >0)
                    Toast.makeText(MainActivity.this,"Video Streaming Service deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Video Streaming Serives not deleted",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void ShowAllViews(){
        Showbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor acura = db.viewAllServices();
                if(acura.getCount()==0){
                    showMessage("Error","Video Streaming services not found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(acura.moveToNext()){
                    buffer.append("VSID:"+acura.getString(0)+"\n");
                    buffer.append("Company Name:"+acura.getString(1)+"\n");
                    buffer.append("Stock Price:"+acura.getString(2)+"\n");
                    buffer.append("Subscribers:"+acura.getString(3)+"\n\n");
                }
                showMessage("Video Streaming Services",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}