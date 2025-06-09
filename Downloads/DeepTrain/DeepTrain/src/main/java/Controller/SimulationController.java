package Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Services.DeepSeekService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/simulation")
public class SimulationController {

    private final DeepSeekService deepSeekService;

    public SimulationController(DeepSeekService deepSeekService) {
        this.deepSeekService = deepSeekService;
    }

    @PostMapping("/ask")
    public Mono<String> askLLM(@RequestBody String userInput) {
        return deepSeekService.getLLMResponse(userInput);
    }
}