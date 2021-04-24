package net.uttersense.keypadapp;

//import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // Used to load the 'native-lib' library on application startup.
    //NB: Due to the use of two different build configurations for the APP,
    //if define the library as such:
    //Android Studio:   native-lib
    //          AOSP:   <name>-lib
    //where <name> matches the source c and header files
    static {
        System.loadLibrary("native-lib");
    }
    /*
     * Declare the JNI native methods:
     */
    public native String stringFromJNI();
    public native int initialise();
    public native void close();
    public native void toggle();
    public native void switchLED(int mode);

    //Attributes:
    TextView tv;
    String text = "This is a text string written on 17 April 2021";
    int cnt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Example of a call to a native method
        tv = (TextView) findViewById(R.id.tv_text);
        //text = stringFromJNI() + stringFromJNI() + stringFromJNI() + stringFromJNI() +stringFromJNI();
        //tv.setText(stringFromJNI());
        //text = text + "Last line!";
        tv.setMovementMethod(new ScrollingMovementMethod());
         tv.setText(text);

    }

    //Click handler for buttons
    public void onClick(View view) {
        String mes;
        switch (view.getId()) {

            case R.id.clearBtn:
                //Intent remote_intent = new Intent(this, RemoteControlActivity.class);
                //startActivity(remote_intent);
                String prev_text = String.valueOf(tv.getText());
                String val = String.valueOf(cnt) ;
                String curr_text = prev_text + "\n" +  text + val;
                tv.setText(curr_text);

                int scrollAmount = tv.getLayout().getLineTop(tv.getLineCount()) - tv.getHeight();
                // if there is no need to scroll, scrollAmount will be <=0
                if (scrollAmount > 0)
                    tv.scrollTo(0, scrollAmount);
                else
                    tv.scrollTo(0, 0);

                cnt++;
                break;

        }
    }


    public void updateTextView(char text)
    {
         String prev_text = String.valueOf(tv.getText());
        //Add the latest text:
        String curr_text = prev_text + text;
        tv.setText(curr_text);
    }


    public void updateTextView(String str)
    {
        String prev_text = String.valueOf(tv.getText());
        //Add the latest text:
        String curr_text = prev_text + str;
        tv.setText(curr_text);
    }

}
