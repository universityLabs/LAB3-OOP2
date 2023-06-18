package com.tech.zootech.security.utils.realization;

import com.auth0.jwt.algorithms.Algorithm;
import com.tech.zootech.security.utils.contracts.AlgorithmUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("defaultAlgorithmService")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AlgorithmUtilImpl implements AlgorithmUtil {
    @Value("${algorithm.secret.value}")
    private String secretValue;

    @Override
    public Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secretValue.getBytes());
    }
}
