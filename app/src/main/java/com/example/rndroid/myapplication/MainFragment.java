package com.example.rndroid.myapplication;


import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    EditText editTextname, editTextsub;
    TextView textView;
    Button buttonSave, buttonGet;
    MyDatabase myDatabase;
    StringBuilder studInfoStringBuilder;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDatabase = new MyDatabase(getActivity());
        myDatabase.openDB();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = getActivity().getLayoutInflater().inflate(R.layout.fragment_main, null);
        editTextname = (EditText) v.findViewById(R.id.edname);
        editTextsub = (EditText) v.findViewById(R.id.edsubject);
        buttonSave = (Button) v.findViewById(R.id.buttonSave);
        buttonGet = (Button) v.findViewById(R.id.buttonGet);
        textView = (TextView) v.findViewById(R.id.textview);


        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabase.insertStudInfo(editTextname.getText().toString(), editTextsub.getText().toString());
                editTextname.setText("");
                editTextsub.setText("");
                editTextname.requestFocus();
                Toast.makeText(getActivity(), "Data Saved", Toast.LENGTH_SHORT).show();
            }
        });

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = myDatabase.queryCursorRead();

                studInfoStringBuilder = new StringBuilder("");
                if (c.moveToNext()){
                    do {
                        studInfoStringBuilder.append(c.getInt(0)+" "+c.getString(1)+" "+ c.getString(2)+"\n");
                        textView.setText(studInfoStringBuilder);
                    }while (c.moveToNext());
                }
        c.close();
            }
        });
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myDatabase.closeDB();
    }
}
