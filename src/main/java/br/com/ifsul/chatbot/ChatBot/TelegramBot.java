package br.com.ifsul.chatbot.ChatBot;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author romulo
 */
public class TelegramBot extends Thread {

    private final static Logger logger = Logger.getLogger(TelegramBot.class.getName());

    private final String endpoint = "https://api.telegram.org/";
    private final String token;
    private boolean botListen = false;

    public TelegramBot(String token, boolean listen) {
        this.token = token;
        this.botListen = listen;
    }

    public HttpResponse<JsonNode> sendMessage(Integer chatId, String text) throws UnirestException {
        logger.info("Enviando msg: " + chatId + " Msg: " + text);
        return Unirest.post(endpoint + "bot" + token + "/sendMessage").field("chat_id", chatId).field("text", text)
                .asJson();
    }

    public HttpResponse<JsonNode> getUpdates(Integer offset) throws UnirestException {
        return Unirest.post(endpoint + "bot" + token + "/getUpdates").field("offset", offset).asJson();
    }

    @Override
    public void run() {

        if (botListen) {
            logger.info("Bot preparado para receber msgs!");
            recebendoMsgs();
        } else {

        }

    }

    private void recebendoMsgs() {
        try {
            int last_update_id = 0; // controle das mensagens processadas
            HttpResponse<JsonNode> response;
            while (true) {
                response = getUpdates(last_update_id++);
                if (response.getStatus() == 200) {
                    JSONArray responses = response.getBody().getObject().getJSONArray("result");
                    if (responses.isNull(0)) {
                        continue;
                    } else {
                        last_update_id = responses.getJSONObject(responses.length() - 1).getInt("update_id") + 1;
                    }

                    for (int i = 0; i < responses.length(); i++) {
                        try {
                            JSONObject message = responses.getJSONObject(i).getJSONObject("message");
                            int chat_id = message.getJSONObject("chat").getInt("id");
                            String usuario = message.getJSONObject("chat").getString("username");
                            String texto = message.getString("text");
                            String textoInvertido = "";

                            logger.info("Msg recebida - usuario: " + usuario + " texto: " + texto);

                            for (int j = texto.length() - 1; j >= 0; j--) {
                                textoInvertido += texto.charAt(j);
                            }

                            if (texto.equalsIgnoreCase("teste")) {
                                sendMessage(chat_id, "Parece que esse bot está funcionando...");
                            } else {
                                sendMessage(chat_id, textoInvertido);
                            }
                        } catch (Exception e) {
                            Logger.getLogger(TelegramBot.class.getName()).log(Level.SEVERE, null, e);
                        }

                    }
                }
            }
        } catch (UnirestException e) {
            Logger.getLogger(TelegramBot.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
