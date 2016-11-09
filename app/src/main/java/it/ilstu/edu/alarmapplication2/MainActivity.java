package it.ilstu.edu.alarmapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addButtonClickListener();
    }

    private void addButtonClickListener() {
        Button alarmButton = (Button) findViewById(R.id.alarmButton);
        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAlarmActivity();
            }
        });

        Button timerButton = (Button) findViewById(R.id.timerButton);
        timerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        Button movementButton = (Button) findViewById(R.id.movementButton);
        movementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMovementActivity();
            }
        });

    }

    private void goToAlarmActivity() {
        Intent i = new Intent(this, AlarmActivity.class);
        startActivity(i);
    }

    private void goToMovementActivity(){
        Intent i = new Intent(this, MovementActivity.class);
        startActivity(i);
    }
}
