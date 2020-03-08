package tr.com.vatos.core.dynamicproxy.action;

import tr.com.vatos.core.dynamicproxy.constants.enums.ProxyActionDecision;

public interface ProxyAction {

    ProxyActionDecision makeDecision(Object instance,String methodName,Class<?> returnType, Object... args);
    Object callSuperThenReturn(Object object);
    Object orElseReturn();

}
