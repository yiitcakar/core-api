package tr.com.vatos.core.reflections;

public interface SourceAcceptanceAction 
{
	static final SourceAcceptanceAction DEFAULT_BEHAVIOUR = (source) -> true; 
	static final SourceAcceptanceAction IS_CLASS_BEHAVIOUR = (source) -> !source.isAnnotation() && !source.isInterface() && !source.isEnum();
	static final SourceAcceptanceAction IS_INTERFACE_BEHAVIOUR = (source) -> source.isInterface();
	static final SourceAcceptanceAction IS_ANNOTATION_BEHAVIOUR = (source) -> source.isAnnotation();
	static final SourceAcceptanceAction IS_ENUM_BEHAVIOUR = (source) -> source.isEnum();
	
	boolean isAcceptable(Class<?> source);
}
