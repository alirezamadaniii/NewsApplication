package com.example.global.data;

public interface DownloadListener {
    void finish(String name);

    void start_d();

    void update_d(int progress);

    void cancel();
}
