package com.theworkshop.bitu.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.theworkshop.bitu.model.generate.GenerateBlock;
import com.theworkshop.bitu.model.newaddress.NewAddress;
import com.theworkshop.bitu.model.send.SendToAddress;
import com.theworkshop.bitu.model.wallet.Wallet;

import java.io.*;
import java.net.*;

public class BitcoinRpc {

    public enum TYPE {

        sendtoaddress,
        getnewaddress,
        generate,
        getwalletinfo,
        ;

    }

    private class Params {

        private String key = null;
        private String value = null;

        @Override
        public String toString() {
            return super.toString();
        }
    }

    private static String sendRpc(TYPE type, String ...params) { // TODO
        String uri = SettingProperty.getUrl();
        String requestBody = "{\"jsonrpc\":\"1.0\",\"id\":\"curltest\",\"method\":\""+type.name()+"\",\"params\":[]}";
        if (TYPE.sendtoaddress.equals(type)) {
            requestBody = "{\"jsonrpc\":\"1.0\",\"id\":\"curltest\",\"method\":\""+type.name()+"\",\"params\":[\""+params[0]+"\", \""+params[1]+"\"]}";
        }else if (TYPE.generate.equals(type)) {
            requestBody = "{\"jsonrpc\":\"1.0\",\"id\":\"curltest\",\"method\":\""+type.name()+"\",\"params\":["+params[0]+"]}";
        }

        String contentType = "text/plain";
        HttpURLConnection connection = null;
        try {
            URL url = new URL(uri);
            connection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", contentType);
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Length", Integer.toString(requestBody.getBytes().length));
            connection.setUseCaches(true);
            connection.setDoInput(true);
            OutputStream out = connection.getOutputStream();
            out.write(requestBody.getBytes());
            out.flush();
            out.close();
        } catch (IOException ioE) {
            connection.disconnect();
            ioE.printStackTrace();
            return "{\"result\":null,\"error\":\"Fail to send request\",\"id\":\"curltest\"}";
        }

        try {
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                return response.toString();
            } else {
                connection.disconnect();
                return "{\"result\":null,\"error\":\"Fail to receive response: "+code+"\",\"id\":\"curltest\"}";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"result\":null,\"error\":\"Fail to receive response\",\"id\":\"curltest\"}";
        }
    }

    public static void updateAuthentictor(String username, char password[]) {
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    public static SendToAddress[] sendToAddress(String addresses[], String amounts[]) {
        BitcoinRpc.updateAuthentictor(SettingProperty.getUsername(), SettingProperty.getPassword().toCharArray());
        SendToAddress sendToAddresses[] = new SendToAddress[addresses.length];
        for (int i=0; i<addresses.length; i++) {
            if (addresses[i].length() > 0 && amounts[i].length() > 0) {
                String result = sendRpc(TYPE.sendtoaddress, addresses[i], amounts[i]);
                Gson gson = new Gson();
                sendToAddresses[i] = gson.fromJson(result, SendToAddress.class);
            }
        }
        generateBlock(4);
        return sendToAddresses;
    }

    public static NewAddress newAddress() {
        BitcoinRpc.updateAuthentictor(SettingProperty.getUsername(), SettingProperty.getPassword().toCharArray());
        String result = sendRpc(TYPE.getnewaddress);
        Gson gson = new Gson();
        return gson.fromJson(result, NewAddress.class);
    }

    public static GenerateBlock generateBlock(int number) {
        BitcoinRpc.updateAuthentictor(SettingProperty.getUsername(), SettingProperty.getPassword().toCharArray());
        String result = sendRpc(TYPE.generate, String.valueOf(number));
        Gson gson = new Gson();
        return gson.fromJson(result, GenerateBlock.class);
    }

    public static Wallet getWalletInfo() {
        BitcoinRpc.updateAuthentictor(SettingProperty.getUsername(), SettingProperty.getPassword().toCharArray());
        String result = sendRpc(TYPE.getwalletinfo);
        Gson gson = new Gson();
        return gson.fromJson(result, Wallet.class);
    }

}