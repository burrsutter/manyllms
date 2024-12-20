package com.burrsutter;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(modelName="candidate3")
public interface Candidate3 {

    @SystemMessage("""
        You are a helpful and succinct AI 
    """)

    String request(@UserMessage String prompt);

}
