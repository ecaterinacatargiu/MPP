package clientside.config;

import interfaces.BookPublisherInterface;
import interfaces.ClientServiceInterface;
import interfaces.TransactionServiceInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import interfaces.BookServiceInterface;

@Configuration
public class ClientConfig {

    @Bean
    RmiProxyFactoryBean rmiProxyFactoryBeanBookPublisher(){
        RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
        rmiProxyFactoryBean.setServiceInterface(BookPublisherInterface.class);
        rmiProxyFactoryBean.setServiceUrl("rmi://localhost:1099/BookPublisherInterface");

        return rmiProxyFactoryBean;
    }


}
