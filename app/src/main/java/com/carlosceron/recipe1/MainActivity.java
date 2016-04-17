package com.carlosceron.recipe1;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {




    static final String KEY_COUNTER = "COUNTER";
    private int mCounter = 0;
    public static final String REQUEST_RESULT = "REQUEST_RESULT";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onClickCounter(View view){
        mCounter++;
        ((TextView) findViewById(R.id.textViewCounter)).setText("Counter" + Integer.toString(mCounter));
    }


    /*** Abrir Navegador e ir a la pagina ***/

    public void launchIntent(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.google.com"));
        startActivity(intent);
    }



    /*** Ir a Una Nueva Activity ***/


    public void onClickSwitchActivity(View view) {

        EditText editText = (EditText) findViewById(R.id.editTextArea);
        String text = editText.getText().toString();
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra(Intent.EXTRA_TEXT, text);

        // startActivity(intent); // Sin Valor de Retorno
        startActivityForResult(intent,1); //Con un Valor de retorno

    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

        outState.putInt(KEY_COUNTER, mCounter);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mCounter = savedInstanceState.getInt(KEY_COUNTER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Toast.makeText(this, Integer.toString(data.getIntExtra(REQUEST_RESULT, 0)), Toast.LENGTH_LONG).show();
        }
    }
}
