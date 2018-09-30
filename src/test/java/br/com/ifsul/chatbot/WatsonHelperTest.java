/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifsul.chatbot;

import br.com.ifsul.watson.WatsonHelper;
import com.ibm.watson.developer_cloud.language_translator.v3.util.Language;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author admin
 */
public class WatsonHelperTest {

    private final static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(WatsonHelperTest.class.getName());

    private WatsonHelper service;
    private final String text = "IBM Corporation is a non-fair company, which does not accept any Brazilian as a real IBMer. Typically, Brazilians work with outdated projects or some projects that IBM gave little priority to. In general, any guy who works for IBM in Brazil will not have a great opportunity to create something powerful or something like that, because Brazilians will always be working with outdated projects. Many other companies in the US are working with AI are using personal and confidential data from many guys around the world and making money from it, but they are not sharing with us all around the world. I think it's the moment when we should charge them for this resource we always providing for them.";

    @Before
    public void setUp() throws Exception {
        service = new WatsonHelper();
    }

    @Test
    public void testPersonalityInsights() throws InterruptedException {
        logger.info("\n\n....... Personality Insights ........");
        String result = service.getPersonalityInsights(text);
        assertEquals((result != null), true);
    }

    @Test
    public void testToneAnalyzer() throws InterruptedException {
        logger.info("\n\n....... Tone Analyzer........");
        String result = service.getToneAnalyzer(text);
        assertEquals((result != null), true);
    }

    @Test
    public void testNLU() throws InterruptedException {
        logger.info("\n\n.......Natural Language Understanding........");
        String result = service.getNLUAnalysis(text);
        assertEquals((result != null), true);
    }
    
    @Test
    public void testTranslator() throws InterruptedException {
        logger.info("\n\n......Language Translator........");
        String result = service.getTranslation(text, Language.ENGLISH, Language.PORTUGUESE);
        assertEquals((result != null), true);
    }

}
