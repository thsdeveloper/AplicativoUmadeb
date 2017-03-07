package br.com.netcriativa.umadeb.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.netcriativa.umadeb.R;

/**
 * Created by Thiago on 05/03/2017.
 */

public class Splash extends Activity implements Runnable {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(this, 3000);
    }


    @Override
    public void run() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }
}