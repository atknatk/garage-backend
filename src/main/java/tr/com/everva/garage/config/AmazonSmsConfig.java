package tr.com.everva.garage.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonSmsConfig {


    @Bean
    public AmazonSNS AmazonSNSClientBuilder() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials("AKIA2HDEDXFRBKPYBCKP", "XvwJQsK1JMCm+HadXZ0PbX0mgrYHD/sHptexBVWo");

        // snsClient.publish(new PublishRequest().withMessage("message").withPhoneNumber("+905325250522"))

        return AmazonSNSClient
                .builder()
                .withRegion(Regions.EU_WEST_1)
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

}
