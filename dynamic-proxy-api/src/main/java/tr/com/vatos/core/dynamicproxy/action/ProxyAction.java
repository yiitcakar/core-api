package tr.com.vatos.core.dynamicproxy.action;

import tr.com.vatos.core.dynamicproxy.constants.enums.ProxyActionDecision;

public interface ProxyAction {

    ProxyActionDecision makeDecision(String methodName, Object... args);
    Object afterCallSuper(Object object);
    Object orElse();

}
