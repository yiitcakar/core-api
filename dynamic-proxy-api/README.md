Simple Example


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



Output:

method call: foo
Proxy Works!
foo
