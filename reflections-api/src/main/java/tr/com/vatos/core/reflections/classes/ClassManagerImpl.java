package tr.com.vatos.core.reflections.classes;

import java.lang.annotation.Annotation;

import tr.com.vatos.core.common.utils.NullCheckUtils;

class ClassManagerImpl implements ClassManager
{
	static ClassManagerImpl instance = new ClassManagerImpl();

	@Override
	public boolean classHasAnnotation(Class<?> cls, Class<? extends Annotation> ant) {
		return NullCheckUtils.isNotObjectEmpty(cls) 
				? NullCheckUtils.isNotObjectEmpty(ant)
						? NullCheckUtils.isNotObjectEmpty(cls.getAnnotation(ant))
						: false
				: false ;
	}

	@Override
	public <T extends Annotation> T getAnnotationFromClassWithNullCheck(Class<?> cls, Class<T> ant) {
		return NullCheckUtils.isNotObjectEmpty(cls) 
				? NullCheckUtils.isNotObjectEmpty(ant)
						? cls.getAnnotation(ant)
						: null
				: null ;
	}

	@Override
	public <T extends Annotation> T getAnnotationFromClassWithoutNullCheck(Class<?> cls, Class<T> ant) {
		cls.getAnnotation(ant);
		return cls.getAnnotation(ant);
	}

	
	
}
