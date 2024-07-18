package com.webforj.issues;

import com.webforj.App;
import com.webforj.exceptions.WebforjException;

public class DownloadWithPage extends App {
    @Override
    public void run() throws WebforjException {
        String message = "Message to Download";
        App.getPage().download(message.getBytes(), "message.txt");
    }
}
