package com.example.front_sample.utils.HttpHandler;

import com.example.front_sample.utils.Utils;

import java.io.IOException;
import java.util.Map;

import fi.iki.elonen.NanoHTTPD;

public class HttpHandler extends NanoHTTPD {
    private String context = "";

    public HttpHandler() throws IOException {
        super(8080);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
        System.out.println("\nRunning! Point your browsers to http://localhost:8080/ \n");
        System.out.println(Utils.getIPAddress(true));
    }

    public void setContext(String newContext) {
        this.context = newContext;
    }

    @Override
    public Response serve(IHTTPSession session) {
        String msg = "HI";
        Map<String, String> params = session.getParms();
        if (params.get("username") == null) {
            msg += "<form action='?' method='get'>\n  <p>Your name: <input type='text' name='username'></p>\n" + "</form>\n";
        } else {
            msg += "<p>Hello, " + params.get("username") + "!</p>";
        }
        return newFixedLengthResponse(msg + "</body></html>\n" + this.context);

    }

}
