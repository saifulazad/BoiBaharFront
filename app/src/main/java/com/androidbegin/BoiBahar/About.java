package com.androidbegin.BoiBahar;

import com.androidbegin.BoiBahar.util.SystemUiHider;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class About extends Activity {


    TextView about, concept, name_c, develop, name_d, info, name_i, email;
    MediaPlayer buttonSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.about);

        about = (TextView) findViewById(R.id.about);
        concept = (TextView) findViewById(R.id.Concept);
        name_c = (TextView) findViewById(R.id.name_c);
        develop = (TextView) findViewById(R.id.develop);
        name_d = (TextView) findViewById(R.id.name_d);
        info = (TextView) findViewById(R.id.info);
        name_i = (TextView) findViewById(R.id.name_i);
        email = (TextView) findViewById(R.id.email);


        about.startAnimation((Animation) AnimationUtils.
                loadAnimation(About.this, R.anim.translate));

        concept.startAnimation((Animation) AnimationUtils.
                loadAnimation(About.this, R.anim.translate));

        name_c.startAnimation((Animation) AnimationUtils.
                loadAnimation(About.this, R.anim.translate));

        develop.startAnimation((Animation) AnimationUtils.
                loadAnimation(About.this, R.anim.translate));

        name_d.startAnimation((Animation) AnimationUtils.
                loadAnimation(About.this, R.anim.translate));

        info.startAnimation((Animation) AnimationUtils.
                loadAnimation(About.this, R.anim.translate));

        name_i.startAnimation((Animation) AnimationUtils.
                loadAnimation(About.this, R.anim.translate));

        email.startAnimation((Animation) AnimationUtils.
                loadAnimation(About.this, R.anim.translate));


        buttonSound = MediaPlayer.create(About.this, R.raw.click);

        Button backButton = (Button) this.findViewById(R.id.button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.start();
                finish();
            }
        });

    }


}

