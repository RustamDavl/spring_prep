package dance.brain.scbtspring.bfpp;

import dance.brain.scbtspring.annotation.InjectBean;
import dance.brain.scbtspring.annotation.Transaction;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
@Component
public class TransactionBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Transaction.class)) {
            map.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = map.get(beanName);
        if (clazz != null) {
            return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
                    (proxy, method, args) -> {
                        System.out.println("open transaction");
                        try {
                            return method.invoke(bean, args); // в любом случае подставляем bean даже если это прокси.
                            // прокси объеты можно вклыдвать друг в друга.
                        } finally {
                            System.out.println("close transaction");
                        }
                    });
        }
        return bean;
    }
}
