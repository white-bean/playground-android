package nbouma.com.wstompclient.services;


import org.json.JSONException;

import nbouma.com.wstompclient.model.Frame;

public interface StompClientProvider {

    void disconnect();

    void subscribe(final String destination, final String id,final String roomId);

    void subscribe(final String destination,final String roomId);

    void unSubscribe(final String destination, final String id,final String roomId);

    void unSubscribe(final String destination,final String roomId);

    void sendMessage(final String destination, final String body) throws JSONException;

    void sendFrame(final Frame frame);

}
