package com.sqisland.android.dice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
  private static final int REQUEST_CODE_DICE = 1000;

  private TextView resultView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    resultView = (TextView) findViewById(R.id.result);

    View button = findViewById(R.id.roll_button);
    button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        roll();
      }
    });
  }

  private void roll() {
    Intent intent = new Intent(this, DiceActivity.class);
    intent.putExtra(DiceActivity.KEY_NUM_DICE, 3);
    startActivityForResult(intent, REQUEST_CODE_DICE);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE_DICE && resultCode == Activity.RESULT_OK) {
      int total = data.getIntExtra(DiceActivity.KEY_TOTAL, 0);
      resultView.setText(String.valueOf(total));
      resultView.setVisibility(View.VISIBLE);
      return;
    }
    super.onActivityResult(requestCode, resultCode, data);
  }
}