package br.com.siecola.aws_project01.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sns.model.Topic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!local")
public class SnsConfig {

    @Value("${aws.region}")
    private String awsRegion;

    @Value("${aws.sns.topic.product.events.arn}")
    private String productEventsTopic;

    //Cliente que vai permitir com que seja possível publicar eventos
    @Bean
    public AmazonSNS snsClient() {
        return AmazonSNSClientBuilder.standard()
                .withRegion(awsRegion)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }

    //No momento que for publicar evento, estamos definindo em qual tópico deve ser publicado
    @Bean(name = "productEventsTopic")
    public Topic snsProductEventsTopic() {
        return new Topic().withTopicArn(productEventsTopic);
    }
}
