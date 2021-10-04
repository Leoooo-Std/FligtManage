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

public class MessageDialog extends Dialog {
    public MessageDialog(Context context) {
        super(context);
    }

    public static class Builder {
        private Context context;
        private String title;

        private String positiveButtonText;
        private String negativeButtonText;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;

        private String userInfoText;
        private String flightInfoText;
        private String siteInfoText;
        private String serviceInfoText;

        private TextView userInfo;
        private TextView flightInfo;
        private TextView serviceInfo;
        private TextView siteInfo;


        public Builder(Context context){this.context = context;};

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setUserInfo(String info){
            this.userInfoText = info;
            return this;
        }

        public Builder setFlightInfo(String info){
            this.flightInfoText = info;
            return this;
        }

        public Builder setSiteInfo(String info){
            this.siteInfoText = info;
            return this;
        }

        public Builder setServiceInfo(String info){
            this.serviceInfoText = info;
            return this;
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

            View layout = inflater.inflate(R.layout.message_dialog, null);
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

            userInfo = layout.findViewById(R.id.userinfo);
            flightInfo = layout.findViewById(R.id.flightinfo);
            serviceInfo = layout.findViewById(R.id.serviceinfo);
            siteInfo = layout.findViewById(R.id.siteinfo);

            userInfo.setText(userInfoText);
            flightInfo.setText(flightInfoText);
            serviceInfo.setText(serviceInfoText);
            siteInfo.setText(siteInfoText);

            return dialog;
        }

    }
}
