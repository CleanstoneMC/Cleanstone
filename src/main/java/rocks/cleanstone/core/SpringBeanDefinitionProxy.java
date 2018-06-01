package rocks.cleanstone.core;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class SpringBeanDefinitionProxy {
    private DefaultListableBeanFactory beanDefinitionRegistry;

    public SpringBeanDefinitionProxy() {
        this.beanDefinitionRegistry = new DefaultListableBeanFactory();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionRegistry.registerBeanDefinition(name, beanDefinition);
    }

    public void registerSimpleBean(String beanName, Class beanClass) {
        final BeanDefinition beanDefinition = BeanDefinitionBuilder
                .rootBeanDefinition(beanClass)
                .setScope(BeanDefinition.SCOPE_PROTOTYPE)
                .getBeanDefinition();

        beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinition);
    }
}
