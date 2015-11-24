package dheeraj.sachan.advancedandroidshit.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

import dheeraj.sachan.advancedandroidshit.Data;
import dheeraj.sachan.advancedandroidshit.MyIntentService;
import dheeraj.sachan.advancedandroidshit.R;
import dheeraj.sachan.advancedandroidshit.activity.ContactActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();
    private ArrayList<Data> dataArrayList = new ArrayList<Data>();
    private Button button;
    private Button contactButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        button = (Button) view.findViewById(R.id.button0);
        contactButton = (Button) view.findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyIntentService.class);
                getActivity().startService(intent);
            }
        });
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }
}
