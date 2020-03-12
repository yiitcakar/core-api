Simple Example

import tr.com.vatos.core.dynamicproxy.action.ProxyAction;
import tr.com.vatos.core.dynamicproxy.builder.DynamicProxyBuilder;
import tr.com.vatos.core.dynamicproxy.constants.enums.ProxyActionDecision;

public class DynamicClassTestAppV2 {
    public static void main(String[] args) {
        Boo booProxy = DynamicProxyBuilder.withClass(Boo.class)
                .allMethods()
                .proxyAction(new ProxyAction() {
                    @Override
                    public ProxyActionDecision makeDecision(Object instance, String methodName, Class<?> returnType, Object... args) {
                        System.out.println("method call: " + methodName);
                        return ProxyActionDecision.CALL_SUPER;
                    }

                    @Override
                    public Object callSuperThenReturn(Object object) {
                        System.out.println("Proxy Works!");
                        return object;
                    }

                    @Override
                    public Object orElseReturn() {
                        return "proxy call foo";
                    }
                }).build();

        System.out.println(booProxy.foo());
    }

    public static class Boo
    {
        public String foo(){
           return "foo";
        }
    }
}

Output:

method call: foo
Proxy Works!
foo
