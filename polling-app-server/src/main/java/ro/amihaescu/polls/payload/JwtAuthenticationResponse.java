package ro.amihaescu.polls.payload;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
public class JwtAuthenticationResponse {

    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

}
