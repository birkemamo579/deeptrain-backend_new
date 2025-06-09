package Services;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;

import Model.DeepSeekRequest;
import Model.DeepSeekResponse;


@Service
public class DeepSeekService {

    private final WebClient webClient;

    public DeepSeekService(@Value("${deepseek.api.url}") String apiUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .build();
    }

    public Mono<String> getLLMResponse(String userInput) {
        // Prepare request body
        DeepSeekRequest request = new DeepSeekRequest(userInput);

        return webClient.post()
                .uri("/v1/generate")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(DeepSeekResponse.class)
                .map(DeepSeekResponse::getReply)
                .onErrorReturn("Default multiple-choice response due to AI timeout.");
    }
}
