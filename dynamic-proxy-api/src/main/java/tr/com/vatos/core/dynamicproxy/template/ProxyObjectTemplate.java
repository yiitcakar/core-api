package tr.com.vatos.core.dynamicproxy.template;

import tr.com.vatos.core.common.utils.NullCheckUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.stream.Collectors;

public class ProxyObjectTemplate
{
    private static final String PROXY_TEMPLATE =
            "public class #namePart# extends #classPart# " +
                    "{ " +
                    "private final tr.com.vatos.core.dynamicproxy.action.ProxyAction proxyAction; "+
                    "public #namePart#(tr.com.vatos.core.dynamicproxy.action.ProxyAction proxyAction) { this.proxyAction = proxyAction; } "+
                    "#methodsPart# " +
                    "}";

    public static String getProxyObjectStr(Class<?> realClass, Collection<Method> methods,String proxyClassName)
    {
        return PROXY_TEMPLATE
                .replaceAll("#namePart#",proxyClassName)
                .replaceAll("#classPart#",realClass.getName())
                .replaceAll("#methodsPart#",methods
                        .stream()
                        .map(method -> getMehodTemplate(method.getName(),method.getReturnType().getName(),method.getParameters()))
                        .collect(Collectors.joining()));
    }

    private static String getMehodTemplate(String methodName,String methodReturn, Parameter[] parameters)
    {
        String paramDefPart = "";
        String paramUsagePart = "";
        if(NullCheckUtils.isNotArrayEmpty(parameters))
        {
            StringBuilder paramDefPartBuilder = new StringBuilder();
            StringBuilder paramUsageBuilder = new StringBuilder();
            for(Parameter parameter : parameters){
                paramDefPartBuilder
                        .append(parameter.getType().getName())
                        .append(" ")
                        .append(parameter.getName())
                        .append(",");
                paramUsageBuilder
                        .append(parameter.getName())
                        .append(",");
            }
            paramDefPart = paramDefPartBuilder.toString().substring(0,paramDefPartBuilder.length()-1);
            paramUsagePart = paramUsageBuilder.toString().substring(0,paramUsageBuilder.length()-1);
        }
        String methodTemplate = new StringBuilder()
                .append(" @Override ")
                .append("public ").append(methodReturn).append(" ").append(methodName).append("(#paramDefPart#) {")
                .append("switch(proxyAction.makeDecision(this,")
                    .append("\"").append(methodName) .append("\",")
                    .append(methodReturn).append(".class")
                    .append(parameters.length == 0 ? "" : ",").append("#paramUsePart#)) { ")
                .append("case CALL_SUPER : return (")
                .append(methodReturn)
                .append(") proxyAction.callSuperThenReturn(super.")
                .append(methodName).append("(#paramUsePart#)); ")
                .append("case OR_ELSE : return (")
                .append(methodReturn)
                .append(") proxyAction.orElseReturn();")
                .append(" } return null ;")
                .append("} ")
                .toString();
        return methodTemplate
                .replaceAll("#paramDefPart#",paramDefPart)
                .replaceAll("#paramUsePart#",paramUsagePart);
    }
}
