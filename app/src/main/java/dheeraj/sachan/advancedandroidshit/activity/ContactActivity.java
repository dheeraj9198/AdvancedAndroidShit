package dheeraj.sachan.advancedandroidshit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import dheeraj.sachan.advancedandroidshit.R;
import dheeraj.sachan.advancedandroidshit.fragment.ContactFragment;

public class ContactActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getSupportFragmentManager().beginTransaction().add(R.id.frame, new ContactFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }
}
