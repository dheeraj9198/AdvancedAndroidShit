package dheeraj.sachan.advancedandroidshit.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.widget.ActionMenuPresenter;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.lang.reflect.Method;

import dheeraj.sachan.advancedandroidshit.R;
import dheeraj.sachan.advancedandroidshit.fragment.MainActivityFragment;

public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return super.onMenuOpened(featureId, menu);
    }

    ActionMenuView actionMenuView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool);
        actionMenuView = (ActionMenuView) findViewById(R.id.one);
        final Context context = this;
        MenuBuilder menuBuilder = new MenuBuilder(context);
        menuBuilder.setCallback(new MenuBuilder.Callback() {
            @Override
            public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
                return onOptionsItemSelected(menuItem);
            }

            @Override
            public void onMenuModeChange(MenuBuilder menuBuilder) {

            }
        });

        // setup a actionMenuPresenter which will use up as much space as it can, even with width=wrap_content
        ActionMenuPresenter presenter = new ActionMenuPresenter(context);
        presenter.setReserveOverflow(true);
        presenter.setWidthLimit(getResources().getDisplayMetrics().widthPixels - 40, true);
        presenter.setItemLimit(Integer.MAX_VALUE);

        // open a menu xml into the menubuilder
        getMenuInflater().inflate(R.menu.menu_main, menuBuilder);
        // runs presenter.initformenu(mMenu) too, setting up presenter's mmenu ref...  this must be before setmenuview
        menuBuilder.addMenuPresenter(presenter, this);
        // runs menuview.initialize too, so menuview.mmenu = mpresenter.mmenu
        actionMenuView.setPresenter(presenter);
        presenter.updateMenuView(true);

        MenuItem item = actionMenuView.getMenu().findItem(R.id.action_settings15);
        SpannableStringBuilder builder = new SpannableStringBuilder("* Login");
        // replace "*" with icon
        builder.setSpan(new ImageSpan(this, android.R.drawable.ic_delete), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        item.setTitle(builder);

       /* try {
            Method m = actionMenuView.getMenu().getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
            m.setAccessible(true);
            m.invoke(actionMenuView.getMenu(), true);
        }catch (Exception e){
            Log.e("","");
        }*/
        getSupportFragmentManager().beginTransaction().add(R.id.frame, new MainActivityFragment()).commit();
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
        if (id == R.id.action_settings1) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
