package com.atguigu.mtime.view.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.mtime.R;

/**
 * Created by HanFeng on 2015/12/13.
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;

        public Builder(Context context) {
            this.context = context;
        }

        public LoadingDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final LoadingDialog dialog = new LoadingDialog(context,R.style.Dialog);
            View layout = inflater.inflate(R.layout.dialog_load, null);

            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setCanceledOnTouchOutside(false);
            ImageView imageView = (ImageView) layout.findViewById(R.id.iv_loading_dialog);
            imageView.setImageResource(R.drawable.animation_list);
            AnimationDrawable rocketAnimation = (AnimationDrawable) imageView.getDrawable();
            rocketAnimation.start();

            dialog.setContentView(layout);
            return dialog;
        }
    }

}
