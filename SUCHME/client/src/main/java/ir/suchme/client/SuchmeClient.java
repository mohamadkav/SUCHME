package ir.suchme.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import ir.suchme.common.dto.base.BaseResponseDTO;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Farzin on 6/7/2017.
 */
public class SuchmeClient {

    private static final String BASE_URL = "http://localhost:54331";
    private Gson gson = new Gson();
    private String SESSION_ID ;// new BigInteger(130, new SecureRandom()).toString(32).toUpperCase();
    private CloseableHttpClient client;
    private HttpPost postRequest;
    private HttpGet getRequest;
    private ResponseHandler<String> responseHandler = new MyResponseHandler();
    private static SuchmeClient instance;
    private ObjectMapper objectMapper;



    private SuchmeClient()
    {
        objectMapper=new ObjectMapper();
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", getSessionId());
        cookie.setPath(BASE_URL);
        cookieStore.addCookie(cookie);
        ArrayList<Header> headers = new ArrayList<>();
//        headers.add(new BasicHeader("Cookie", "JSESSIONID."+getSessionId()));
        client = HttpClients.custom().setDefaultCookieStore(cookieStore).setDefaultHeaders(headers).build();
      //  System.out.println(getSessionId());
    }

    public static SuchmeClient getInstance()
    {
        if(instance != null)
            return instance;
        else
        {
            instance = new SuchmeClient();
            return instance;
        }

    }

    public <T extends BaseResponseDTO> T postRequestAndWaitForResponse(String url, Object param, Class<T> responseType)
    {
        String fullUrl = BASE_URL + url;
        postRequest = new HttpPost(fullUrl);
        postRequest.setHeader("Content-type", "application/json");
        String result;
        try {
            postRequest.setEntity(new StringEntity(gson.toJson(param)));
            result = client.execute(postRequest, responseHandler);
//            for (int i = 0; i < postRequest.getAllHeaders().length; i++) {
//                System.out.println("requesst:   "+postRequest.getAllHeaders()[i].getName());
//            }
            return objectMapper.readValue(result, responseType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getRequestAndWaitForResponse(String url, Object param)
    {
        String fullUrl = BASE_URL + url;
        getRequest = new HttpGet(fullUrl);
        getRequest.setHeader("Content-type", "application/json");
        String out;
        try {
            out = client.execute(getRequest, responseHandler);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return out;
    }

    class MyResponseHandler implements ResponseHandler<String>
    {
        @Override
        public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {

            if(response.getHeaders("Set-Cookie").length != 0)
            {
                int beginIndex = response.getHeaders("Set-Cookie")[0].getValue().indexOf('=');
                String id = response.getHeaders("Set-Cookie")[0].getValue().substring(beginIndex+1, beginIndex+33);
                setSessionId(id);
            }
            StringBuilder builder = new StringBuilder();
            Scanner scan = new Scanner(response.getEntity().getContent());
            while(scan.hasNext()) {
                builder.append(scan.nextLine());
            }
            return builder.toString();
        }
    }

    public String getSessionId()
    {
        return SESSION_ID;
    }

    private void setSessionId(String s)
    {
        SESSION_ID = s;
    }
}
