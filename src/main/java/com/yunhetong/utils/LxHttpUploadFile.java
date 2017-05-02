package com.yunhetong.utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by qianyj on 2017/5/2.
 */
public class LxHttpUploadFile {
    private static String responseContentType = "";

    /**
     * 利用post上传文件
     *
     * @param url
     * @param file
     * @param params
     * @return
     * @throws IOException
     */
    public static String postUploadFile(String url, File file, Map<String, String> params) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String body = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            FileBody fileBody = new FileBody(file);
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            multipartEntityBuilder.addPart("file", fileBody);
            setUploadParams(multipartEntityBuilder, params);
            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            body = paseResponse(httpResponse);
            Header contentTypeHeader = httpResponse.getFirstHeader("Content-Type");
            if (contentTypeHeader != null) {
                responseContentType = contentTypeHeader.getValue();
            }

            try {
                httpResponse.close();
            } catch (IOException var6) {
                var6.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        return body;
    }

    private static String paseResponse(HttpResponse response) {
        HttpEntity entity = response.getEntity();
        String body = null;

        try {
            body = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } catch (ParseException var4) {
            var4.printStackTrace();
        } catch (IOException var5) {
            var5.printStackTrace();
        }

        return body;
    }

    /**
     * 添加除了文件的其他参数
     *
     * @param multipartEntityBuilder
     * @param params
     */
    private static void setUploadParams(MultipartEntityBuilder multipartEntityBuilder,
                                        Map<String, String> params) {
        if (params != null && params.size() > 0) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                multipartEntityBuilder
                        .addPart(key, new StringBody(params.get(key),
                                ContentType.TEXT_PLAIN));
            }
        }
    }
}
