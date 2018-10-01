/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsul.chatbot;

import br.com.ifsul.watson.Params;

/**
 *
 * @author romulo
 */
public class MainBot {
    public static void main(String[] args) {
        TelegramBot botListener = new TelegramBot(Params.TOKEN_BOT, true);
        botListener.start();
    }
}
