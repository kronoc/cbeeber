package net.conor.android.cbeeber.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import net.conor.android.cbeeber.R;

/**
 * Created by keegac01 on 28/08/2014.
 */
public class HelpFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.help_fragment, container, false);

        TextView helpHeadingText = (TextView) view.findViewById(R.id.activity_help_heading);
        helpHeadingText.setText(R.string.help_heading);

        TextView helpBodyText = (TextView) view.findViewById(R.id.activity_help_text);
        helpBodyText.setText(R.string.help_content);

        return view;


    }
}