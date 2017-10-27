package com.imagecolletorsllc.imagecollectors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class ConventionSetupActivity extends AppCompatActivity {

    private EditText conventionText;
    private EditText clientNumberText;
    private Button submitButton;
    private DatePicker conventionDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convention_setup);
        setupWidgets();
    }

    private void setupWidgets(){
        conventionText = (EditText) findViewById(R.id.conventionNameText);
        clientNumberText = (EditText) findViewById(R.id.startingClientText);
        conventionDate = (DatePicker) findViewById(R.id.datePicker);
        submitButton = (Button) findViewById(R.id.submitConvention);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String conventionName = conventionText.getText().toString();
                int iclientNumberText = Integer.parseInt(clientNumberText.getText().toString());
                int conventionYear = conventionDate.getYear();
                int convetionMonth = conventionDate.getMonth();
                int conventionDay = conventionDate.getDayOfMonth();
                Convention conv = new Convention(conventionName, iclientNumberText, conventionYear, convetionMonth, conventionDay);
                Utils.setCurrentConvention(conv);
                Toast.makeText(ConventionSetupActivity.this, "Convention Saved", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, SelectionScreen.class);
        startActivity(intent);
    }
}
