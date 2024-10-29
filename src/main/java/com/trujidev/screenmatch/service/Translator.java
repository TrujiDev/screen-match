package com.trujidev.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class Translator {
  public static String getTranslation(String text) {
    OpenAiService service = new OpenAiService("");

    CompletionRequest requisition = CompletionRequest.builder()
        .model("gpt-3.5-turbo-instruct")
        .prompt("Translates the following text into English: " + text)
        .maxTokens(1000)
        .temperature(0.7)
        .build();

    var response = service.createCompletion(requisition);
    return response.getChoices().getFirst().getText();
  }
}
