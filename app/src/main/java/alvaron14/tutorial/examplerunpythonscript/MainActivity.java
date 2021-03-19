package alvaron14.tutorial.examplerunpythonscript;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        } //Chaquopy 9.1
    }


    public void callback(View view) {
        TextView textView = findViewById(R.id.textView);

        Python python = Python.getInstance();
        try {
            PyObject pythonModule = python.getModule("populartimes/__init__");

            //TODO add your api key and the id of the place
            String resultAPI = pythonModule.callAttr("get_id",
                                                "Your api key here",
                                                        "Place to search id")
                                                    .toString();

            textView.setText(resultAPI);
        } catch (Exception e) {
            //For debug
            System.err.println("----------------");
            System.err.println(e);
            System.err.println("----------------");
        }
    }
}