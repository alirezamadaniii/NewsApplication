package com.example.global.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.global.R;
import com.example.global.data.DownloadListener;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadFile {
    public static final int start = 0, finish = 1;
    Context context;
    String url;
    String path;
    DownloadListener download_listener;
    ProgressBar progressBar;
    TextView tvPercent;
    Dialog dialog, dialog_finish;
    Button cancel;
    CardView submit;

    TextView text_fail, text_success, text_error;
    int fail = 0, success = 0;
    String error = "null";
    AsyncTask downloader;
    int state;
    Tool tool;
    String name;

    public DownloadFile(Context context, String url, String path, DownloadListener download_listener, String name) {
        this.url = url;
        this.context = context;
        this.path = path;
        this.name = name;
        tool = new Tool();
        this.download_listener = download_listener;

        downloader = new DownloadFileFromURL().execute(url);
    }

    protected void onCreateDialog(int id) {
//
//        switch (id) {
//            case start: // we set this to 0
//                dialog = new Dialog(context);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                dialog.setContentView(R.layout.layout_dialog);
//                progressBar = dialog.findViewById(R.id.progress);
//                tvPercent = dialog.findViewById(R.id.tv_percent);
//                cancel = dialog.findViewById(R.id.cancel);
//
//                cancel.setOnClickListener(v -> {
//                    File file = new File(path);
//                    tool.delete(context, file);
//                    downloader.cancel(true);
//
//                    dialog.dismiss();
//                });
//                dialog.show();
//                break;
//            case finish:
////                dialog_finish = new Dialog(context , R.style.Theme_Dialog);
////                dialog_finish.setContentView(R.layout.dialog_download_result);
////                submit = dialog_finish.findViewById(R.id.submit);
////                text_error = dialog_finish.findViewById(R.id.error_download);
////                text_fail = dialog_finish.findViewById(R.id.fail_download);
////                text_success = dialog_finish.findViewById(R.id.success_download);
////
////                String ok = context.getString(R.string.count_success)+success;
////                String fa = context.getString(R.string.count_fail)+fail;
////                String er = context.getString(R.string.message_error)+error;
////
////                text_error.setText(er);
////                text_success.setText(ok);
////                text_fail.setText(fa);
////
////                submit.setOnClickListener(v->{
////                    dialog_finish.dismiss();
////                });
////                dialog_finish.show();
//                break;
//            default:
//                break;
//        }
    }


    /**
     * Background Async Task to download file
     */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Bar Dialog
         */
        @Override
        public void onPreExecute() {
            super.onPreExecute();
            download_listener.start_d();
            onCreateDialog(start);
        }

        /**
         * Downloading file in background thread
         */
        @Override
        public String doInBackground(String... f_url) {
            int count;
            try {

                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                // this will be useful so that you can show a tipical 0-100%
                // progress bar
                int lenghtOfFile = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);

                // Output stream
                OutputStream output = new FileOutputStream(path);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);

                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                error = e.toString();
                Log.i("downloadStatus", ": cancel = " + error);
                downloader.cancel(true);
                download_listener.cancel();
            }

            return null;
        }

        /**
         * Updating progress bar
         */
        @SuppressLint("SetTextI18n")
        public void onProgressUpdate(String... progress) {
            // setting progress percentage
            int progressInt = Integer.parseInt(progress[0]);
            progressBar.setProgress(progressInt);
            tvPercent.setText(progress[0] + " %");
            download_listener.update_d(Integer.parseInt(progress[0]));

        }

        public void onCancelled() {
            downloader.cancel(false);
            fail = fail + 1;
            dialog.dismiss();
            onCreateDialog(finish);
            download_listener.cancel();
        }

        /**
         * After completing background task Dismiss the progress dialog
         **/
        @Override
        public void onPostExecute(String file_url) {
            dialog.dismiss();
            success = success + 1;
            onCreateDialog(finish);
            download_listener.finish(name);
        }
    }
}
