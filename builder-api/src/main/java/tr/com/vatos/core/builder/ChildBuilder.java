package tr.com.vatos.core.builder;

public interface ChildBuilder<T,P extends SimpleBuilder<?>> extends SimpleBuilder<T>
{
	P end();
}
