package com.example.owen.comediansdatabase;

/**
 * Created by Owen on 9/21/2016.
 */

public class WikiThread {

    private WikiThread(){

    }

    public static WikiThread getInstance(ApiResponseHandler handler){
        responseHandler = handler;
        if(instance == null){
            instance = new WikiThread();
        }
        return instance;
    }
}
