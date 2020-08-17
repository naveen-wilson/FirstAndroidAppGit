package com.example.workassist;


import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    public static ArrayList<String> workList= new ArrayList<String>();
        @VisibleForTesting
        public ProgressBar mProgressBar;

        public void setProgressBar(ProgressBar progressBar) {
            mProgressBar = progressBar;
        }

        public void showProgressBar() {
            if (mProgressBar != null) {
                mProgressBar.setVisibility(View.VISIBLE);
            }
        }

        public void hideProgressBar() {
            if (mProgressBar != null) {
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        }

        public void hideKeyboard(View view) {
            final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }

        @Override
        public void onStop() {
            super.onStop();
            hideProgressBar();
        }


}
