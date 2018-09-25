/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsul.chatbot.ChatBot;

/**
 *
 * @author romulo
 */
public class MainBot {

    public static String TOKEN_BOT = "561832171:AAEqnmFS1W-yVDFdkUFSmRyY70OTQ_vqXMg";

    public static void main(String[] args) {
        TelegramBot botListener = new TelegramBot(TOKEN_BOT, true);
        botListener.start();
    }
}
