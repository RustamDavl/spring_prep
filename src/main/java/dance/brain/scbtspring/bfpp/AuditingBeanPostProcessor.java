package dance.brain.scbtspring.bfpp;

import dance.brain.scbtspring.annotation.Auditing;
import dance.brain.scbtspring.annotation.Transaction;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuditingBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> map = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Auditing.class)) {
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
                        System.out.println("auditing of method : " + method.getName());
                        long start = System.currentTimeMillis();
                        try {
                            return method.invoke(bean, args); // в любом случае подставляем bean даже если это прокси.
                            // прокси объеты можно вклыдвать друг в друга.
                        } finally {
                            System.out.println("auditing execution time : " + (System.currentTimeMillis() - start));
                        }
                    });
        }
        return bean;
    }
}
