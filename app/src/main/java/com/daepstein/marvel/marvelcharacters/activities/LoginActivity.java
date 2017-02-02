package com.daepstein.marvel.marvelcharacters.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.daepstein.marvel.marvelcharacters.R;
import com.daepstein.marvel.marvelcharacters.login.Login;
import com.daepstein.marvel.marvelcharacters.login.LoginPair;
import com.daepstein.marvel.marvelcharacters.network.NetworkUtility;
import com.daepstein.marvel.marvelcharacters.network.SharedPrefs;
import com.daepstein.marvel.marvelcharacters.util.Constants;

import static com.daepstein.marvel.marvelcharacters.util.ImageResize.ResizeImage;

public class LoginActivity extends AppCompatActivity {

    SharedPrefs sharedPrefs;
    EditText pubKye, privKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        loginWatcher();
    }

    /**
     * Initialize buttons and check if exist a valid login
     */

    private void init ()
    {
        ((ImageView)findViewById(R.id.img_login)).setImageDrawable(ResizeImage(R.drawable.login_image, this));
        sharedPrefs = new SharedPrefs(this);
        if (!validLoginExist())
        {
            ((Button)findViewById(R.id.btn_oldSession)).setVisibility(View.INVISIBLE);
        }

        /**************For Test proposes only**********/
        if (Constants.privateKey!="")
            ((EditText)findViewById(R.id.txt_private)).setText(Constants.privateKey);
        if (Constants.publicKey!="")
            ((EditText)findViewById(R.id.txt_public)).setText(Constants.publicKey);
        if (Constants.privateKey!="" && Constants.publicKey!="")
            ((Button)findViewById(R.id.btn_Login)).setEnabled(true);

    }

    private boolean validLoginExist()
    {
        return !sharedPrefs.isEmpty();
    }

    //Public methods that cannot be hidden with obfuscator
    public void newLogin(View view)
    {
        setNewLogin();
    }
    public void validLogin(View view)
    {
        setPreviousLogin();
    }

    private void setNewLogin(){
        Login.getInstance().setPublicKey(((EditText)findViewById(R.id.txt_public)).getText().toString());
        Login.getInstance().setPrivateKey(((EditText)findViewById(R.id.txt_private)).getText().toString());
        NetworkUtility networkUtility = new NetworkUtility(this);
        if (networkUtility.connectionError())
        {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.not_connected_title))
                    .setMessage(getString(R.string.not_connected))
                    .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
                    .setCanceledOnTouchOutside(false);
            return;
        }

        Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }

    private  void setPreviousLogin()
    {
        NetworkUtility networkUtility = new NetworkUtility(this);
        if (networkUtility.connectionError())
            return;

        LoginPair p = sharedPrefs.getKeys();
        Login.getInstance().setPublicKey(p.getPublicKey());
        Login.getInstance().setPrivateKey(p.getPrivateKey());
        Intent mainActivityIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainActivityIntent);
        finish();
    }

    /**
     * Only enable login button if both keys are set
     */
    private void loginWatcher()
    {
        pubKye = ((EditText)findViewById(R.id.txt_public));
        privKey = ((EditText)findViewById(R.id.txt_private));

        TextWatcher tw = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                updateSignInButtonState();
            }
        };

        privKey.addTextChangedListener(tw);
        pubKye.addTextChangedListener(tw);
    }

    private void updateSignInButtonState() {
        ((Button)findViewById(R.id.btn_Login)).setEnabled(privKey.getText().length() > 0 &&
                pubKye.getText().length() > 0);
    }
}
