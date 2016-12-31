package net.soragoto.cola.colorchecking;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static net.soragoto.cola.colorchecking.MainActivity.adapter;
import static net.soragoto.cola.colorchecking.MainActivity.colorList;
import static net.soragoto.cola.colorchecking.MainActivity.color_blue;
import static net.soragoto.cola.colorchecking.MainActivity.color_green;
import static net.soragoto.cola.colorchecking.MainActivity.color_red;

/**
 * Created by cola on 2016/12/30.
 */

public class SaveActivity extends Activity {
    int r,g,b;
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        final EditText newNameBox = (EditText)findViewById(R.id.new_name_box);
        final TextView colorBox = (TextView) findViewById(R.id.boxcolor);
        colorBox.setBackgroundColor( Color.rgb(color_red, color_green, color_blue) );
        Button saveButton = (Button) findViewById(R.id.savebutton);

        ( (TextView)findViewById(R.id.new_color_r) ).setText( String.valueOf(color_red) );
        ( (TextView)findViewById(R.id.new_color_g) ).setText( String.valueOf(color_green) );
        ( (TextView)findViewById(R.id.new_color_b) ).setText( String.valueOf(color_blue) );
//        try {
//            r = Integer.parseInt( ((EditText) findViewById(R.id.alpha)).getText().toString() );
//            g = Integer.parseInt( ((EditText) findViewById(R.id.beta)).getText().toString() );
//            b = Integer.parseInt( ((EditText) findViewById(R.id.gamma)).getText().toString() );
//        } catch (android.net.ParseException | NumberFormatException ignore) {
//        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorList.add(new Mycolor( newNameBox.getText().toString(), color_red, color_green, color_blue));
                adapter.add( colorList.get( colorList.size() - 1 ).name );
                finish();
            }
        });
    }
}