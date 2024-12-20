package com.burrsutter;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class BackendResource {

    @Inject Candidate1 candidate1;
    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate1.chat-model.model-id") String modelNameCandidate1;

    @Inject Candidate2 candidate2;
    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate2.chat-model.model-id") String modelNameCandidate2;

    @Inject Candidate3 candidate3;
    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate3.chat-model.model-id") String modelNameCandidate3;

    @Inject Candidate4 candidate4;
    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate4.chat-model.model-id") String modelNameCandidate4;

    @Inject Candidate5 candidate5;
    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate5.chat-model.model-id") String modelNameCandidate5;

    @Inject Candidate6 candidate6;
    @ConfigProperty(name="quarkus.langchain4j.ollama.candidate6.chat-model.model-id") String modelNameCandidate6;


    @GET
    @Path("/stuff")
    public String stuff() {
        return "Stuff";
    }
    

    @GET
    @Path("/testJSON")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String testJSON(@QueryParam("input") String param) {
        System.out.println(param);

        return 
        """
        [
        "Hello stuff happens daily",
        "Bonjour please and thank you",
        "Jambo the quick brown fox jumped over the lazy dog",
        "Hola where in the world is waldo?",
        "Hey Y'all when you here the banjos",
        "Aloha and Ala Moana"
        ]  
        """;
    }

    @GET
    @Path("/endpoint")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public String endpoint(@QueryParam("input") String prompt) {

        System.out.println("User Prompt: " + prompt);

        String response1 = candidate1.request(prompt);
        response1 = escapeJSONString(response1);

        System.out.println(response1);        

        String response2 = candidate2.request(prompt);
        response2 = escapeJSONString(response2);

        System.out.println(response2);

        String response3 = candidate3.request(prompt);
        response3 = escapeJSONString(response3);
    
        System.out.println(response3);

        String response4 = candidate4.request(prompt);
        response4 = escapeJSONString(response4);
    
        System.out.println(response4);
        
        String response5 = candidate5.request(prompt);
        response5 = escapeJSONString(response5);
    
        System.out.println(response5);

        String response6 = candidate6.request(prompt);
        response6 = escapeJSONString(response6);
    
        System.out.println(response6);

        return 
        "[\"" + response1 + "\"," + 
        "\"" + response2 + "\"," +
        "\"" + response3 + "\"," +
        "\"" + response4 + "\"," +
        "\"" + response5 + "\"," +
        "\"" + response6 + "\"]";        
    }

    private String escapeJSONString (String input) {
        StringBuilder sb = new StringBuilder();

        for (char c : input.toCharArray()) {
            if (c == '"') {
                sb.append("\\\"");
            } else if (c == '\\') {
                sb.append("\\\\");
            } else if (c == '\n') {
                sb.append("\\n");
            } else if (c == '\r') {
                sb.append("\\r");
            } else if (c == '\t') {
                sb.append("\\t");
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

}
