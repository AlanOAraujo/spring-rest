package br.com.devmedia.curso.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration // Essa anotação informa para o Spring, que está é uma classe de configuração.
@ComponentScan("br.com.devmedia.curso") //A CS informa para o Spring, onde ele deve procurar as clases que ele irá Gerenciar.
@EnableWebMvc //Aqui estamos informando para o Spring para utilizar o Spring WebMVC
@EnableTransactionManagement //O Spring ira gerenciar, as transações com a base de dados
public class SpringRootConfig {

    
}