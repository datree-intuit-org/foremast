package ai.foremast.metrics.demo;

import ai.foremast.metrics.demo.error.ErrorGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class K8sMetricsDemoApp {

    private static ErrorGenerator errorGenerator;

    public static void main(String[] args) {
        SpringApplication.run(K8sMetricsDemoApp.class, args);

        String errorType = System.getProperty("errorType");
        if ("4xx".equalsIgnoreCase(errorType) || "5xx".equalsIgnoreCase(errorType)) {
            String env = System.getProperty("frequency");
            int countPerSecond = 3;
            if (env != null) {
                countPerSecond = Integer.parseInt(env);
            }
            errorGenerator = new ErrorGenerator(countPerSecond, errorType);
            errorGenerator.init();
        }
    }
}
