package be.lennehendrickx.main;

import org.apache.ignite.IgniteException;
import org.apache.ignite.lifecycle.LifecycleBean;
import org.apache.ignite.lifecycle.LifecycleEventType;
import org.springframework.stereotype.Component;

@Component
public class ExampleLifecycleBean implements LifecycleBean {
    @Override
    public void onLifecycleEvent(LifecycleEventType evt) throws IgniteException {
        System.out.println("Entered example lifecycle bean onLifecycleEvent");
    }
}
