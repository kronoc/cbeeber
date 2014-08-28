package net.conor.android.cbeeber.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import net.conor.android.cbeeber.R;

/**
 * Created by keegac01 on 28/08/2014.
 */
public class HelpFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.help_view, container, false);
        return view;
    }
}