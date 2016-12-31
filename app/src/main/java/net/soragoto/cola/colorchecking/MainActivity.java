package net.soragoto.cola.colorchecking;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    static int color_red = 0;
    static int color_green = 0;
    static int color_blue = 0;
    static List<Mycolor> colorList = null;
    static ArrayAdapter<String> adapter = null;
    Mycolor[] mycolor;

    //String[] members = {"alpha", "beta", "gamma", "delta", "theta", "epsilon", "zeta", "eta", "theta"};
    //String[] colors = {"Red", "Orange", "Yellow ", "Green", "Blue", "Indigo", "Purple"};

    private InputMethodManager inputMethodManager;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //キーボードを隠して表示
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout) findViewById(R.id.activity_main);
        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);

        //Mycolorの設定
        colorList = new ArrayList<Mycolor>();
        mycolor = new Mycolor[7];
//        mycolor[0] = new Mycolor( "Red",255,0,0);
//        mycolor[1] = new Mycolor( "Orange",255,127,0);
//        mycolor[2] = new Mycolor( "Yellow",255,255,0);
//        mycolor[3] = new Mycolor( "Green",0,255,0);
//        mycolor[4] = new Mycolor( "Blue",0,0,255);
//        mycolor[5] = new Mycolor( "Indigo",64,0,127);
//        mycolor[6] = new Mycolor( "Purple",127,0,255);

        colorList.add( new Mycolor( "Red",    255,   0,   0) );
        colorList.add( new Mycolor( "Orange", 255, 127,   0) );
        colorList.add( new Mycolor( "Yellow", 255, 255,   0) );
        colorList.add( new Mycolor( "Green",    0, 255,   0) );
        colorList.add( new Mycolor( "Blue",     0,   0, 255) );
        colorList.add( new Mycolor( "Indigo",  64,   0, 127) );
        colorList.add( new Mycolor( "Purple", 127,   0, 255) );

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final SeekBar seek1 = (SeekBar) findViewById(R.id.seekBar1);
        final SeekBar seek2 = (SeekBar) findViewById(R.id.seekBar2);
        final SeekBar seek3 = (SeekBar) findViewById(R.id.seekBar3);

        final EditText alpha = (EditText) findViewById(R.id.alpha);
        final EditText beta = (EditText) findViewById(R.id.beta);
        final EditText gamma = (EditText) findViewById(R.id.gamma);

        final EditText alphaHex = (EditText) findViewById(R.id.alpha_hex);
        final EditText betaHex = (EditText) findViewById(R.id.beta_hex);
        final EditText gammaHex = (EditText) findViewById(R.id.gamma_hex);

        final TextView colorbox = (TextView) findViewById(R.id.boxcolor);

        //ボタンの設定
        final Button btn1 = (Button) findViewById(R.id.btn1);
        final Button btn2 = (Button) findViewById(R.id.btn2);
        final Button btn3 = (Button) findViewById(R.id.btn3);


        //リストの設定
        final ListView listView = (ListView) findViewById(R.id.color_list);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        for(int i = 0 ; i < 7; i++){
            adapter.add( colorList.get(i).name );
        }

        listView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                seek1.setProgress( 0 );
//                seek2.setProgress( 0 );
//                seek3.setProgress( 0 );
//
//                alpha.setText(String.valueOf( 0 ));
//                beta.setText(String.valueOf( 0 ));
//                gamma.setText(String.valueOf( 0 ));
                //colorList.add( new Mycolor( "White",255,255,255 ) );
                //adapter.add( colorList.get( colorList.size() - 1 ).name );
                Intent intent = new Intent(getApplicationContext(),net.soragoto.cola.colorchecking.SaveActivity.class);
                startActivity(intent);

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                color_red   = colorList.get( position ).r;
                color_green = colorList.get( position ).g;
                color_blue  = colorList.get( position ).b;

                seek1.setProgress(color_red);
                seek2.setProgress(color_green);
                seek3.setProgress(color_blue);

                alpha.setText(String.valueOf(color_red));
                beta.setText(String.valueOf(color_green));
                gamma.setText(String.valueOf(color_blue));

                setColor(colorbox);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    color_red = Integer.parseInt(alpha.getText().toString());
                    seek1.setProgress(color_red);
                    setColor(colorbox);
                    InputMethodManager inputMethodMgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodMgr.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                } catch (android.net.ParseException | NumberFormatException ignore) {
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    color_green = Integer.parseInt(beta.getText().toString());
                    seek2.setProgress(color_green);
                    setColor(colorbox);
                    InputMethodManager inputMethodMgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodMgr.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                } catch (android.net.ParseException | NumberFormatException ignore) {
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    color_blue = Integer.parseInt(gamma.getText().toString());
                    seek3.setProgress(color_blue);
                    setColor(colorbox);
                    InputMethodManager inputMethodMgr = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    inputMethodMgr.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                } catch (android.net.ParseException | NumberFormatException ignore) {
                }
            }
        });

        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                color_red = progress;
                alpha.setText(String.valueOf(color_red));
                alphaHex.setText(Integer.toHexString(color_red).toUpperCase());
                setColor(colorbox);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seek2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                color_green = progress;
                beta.setText(String.valueOf(color_green));
                betaHex.setText(Integer.toHexString(color_green).toUpperCase());
                setColor(colorbox);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        seek3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                color_blue = progress;
                gamma.setText(String.valueOf(color_blue));
                gammaHex.setText(Integer.toHexString(color_blue).toUpperCase());
                setColor(colorbox);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setColor(TextView box) {
        box.setBackgroundColor(Color.rgb(color_red, color_green, color_blue));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //キーボードを隠す
        inputMethodManager.hideSoftInputFromWindow(mainLayout.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        //背景にフォーカスを移す
        mainLayout.requestFocus();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
