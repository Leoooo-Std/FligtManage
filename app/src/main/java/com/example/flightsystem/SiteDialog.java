package com.example.flightsystem;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SiteDialog extends Dialog {
    public SiteDialog(Context context) {
        super(context);
    }

    public static class Builder {
        private Context context;
        private String title;

        private String positiveButtonText;
        private String negativeButtonText;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;

        private Spinner spinner1;
        private Spinner spinner2;
        private TextView titleText;
        private ArrayAdapter<String> adapter1;
        private ArrayAdapter<String> adapter2;
        private TextView siteText1;
        private TextView siteText2;

        private static final String[] m1 = {"A","B","C","D","E","F"};
        private static final String[] m2 = new String[50];

        public Builder(Context context){this.context = context;};

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public String getSite(){
            return siteText1.getText() + ":"+siteText2.getText();
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public SiteDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final SiteDialog dialog = new SiteDialog(context);

            View layout = inflater.inflate(R.layout.site_dialog, null);
            dialog.addContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            if (positiveButtonText != null) {
                ((TextView) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.positiveButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    positiveButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_POSITIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
            }

            // set the cancel button
            if (negativeButtonText != null) {
                ((TextView) layout.findViewById(R.id.negtiveButton))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener != null) {
                    ((TextView) layout.findViewById(R.id.negtiveButton))
                            .setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    negativeButtonClickListener.onClick(dialog,
                                            DialogInterface.BUTTON_NEGATIVE);
                                }
                            });
                }
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negtiveButton).setVisibility(
                        View.GONE);
            }

            siteText1 = layout.findViewById(R.id.site_text1);
            siteText2 = layout.findViewById(R.id.site_text2);
            titleText = layout.findViewById(R.id.site_title);
            spinner1 = layout.findViewById(R.id.site_spinner1);
            spinner2 = layout.findViewById(R.id.site_spinner2);
            for(int i = 0; i < 50; i++){
                m2[i] = String.valueOf(i+1);
            }
            titleText.setText(title);
            adapter1 = new ArrayAdapter<String>(dialog.getContext(),android.R.layout.simple_spinner_item,m1);
            adapter2 = new ArrayAdapter<String>(dialog.getContext(),android.R.layout.simple_spinner_item,m2);

            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner1.setAdapter(adapter1);
            spinner2.setAdapter(adapter2);

            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    siteText1.setText("Index: " + m1[position]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    siteText2.setText("Postion: " + m2[position]);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            return dialog;
        }

    }
}
