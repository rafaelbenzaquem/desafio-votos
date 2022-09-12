package br.com.benzaquem.desafiovotos.commons.config;

//import org.apache.kafka.clients.admin.AdminClientConfig;
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaAdmin;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import java.util.HashMap;

//@Configuration
public interface KafkaPuplisherConfig {

//    private final KafkaProperties kafkaProperties;
//
//    private final String nomeTopico;
//
//    private final String numParticoes;
//
//    private final String fatorDeReplicacao;
//
//    public KafkaPuplisherConfig(KafkaProperties kafkaProperties,
//                                @Value("${topico.nome}") String nomeTopico,
//                                @Value("${topico.num-particoes}") String numParticoes,
//                                @Value("${topico.fator-de-replicacao}") String fatorDeReplicacao) {
//        this.kafkaProperties = kafkaProperties;
//        this.nomeTopico = nomeTopico;
//        this.numParticoes = numParticoes;
//        this.fatorDeReplicacao = fatorDeReplicacao;
//    }
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        var configs = new HashMap<String, Object>();
//        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
//        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//
//        return new DefaultKafkaProducerFactory<>(configs);
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    @Bean
//    public KafkaAdmin kafkaAdmin() {
//        var configs = new HashMap<String, Object>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
//        return new KafkaAdmin(configs);
//    }
//
//    @Bean
//    public NewTopic novoTopico() {
//        return new NewTopic(nomeTopico, Integer.parseInt(numParticoes), Short.parseShort(fatorDeReplicacao));
//    }
}
