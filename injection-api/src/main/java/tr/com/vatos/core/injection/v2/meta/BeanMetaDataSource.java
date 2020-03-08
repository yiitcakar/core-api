package tr.com.vatos.core.injection.v2.meta;

public abstract class BeanMetaDataSource
{
    private final BeanSourceType beanSourceType;

    protected BeanMetaDataSource(BeanSourceType beanSourceType){
        this.beanSourceType = beanSourceType;
    }

    public BeanSourceType getBeanSourceType() {
        return beanSourceType;
    }
}
