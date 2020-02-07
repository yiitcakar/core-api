package tr.com.vatos.core.builder.behaviours;

import tr.com.vatos.core.builder.types.Builder;

public interface ReturnParent<P extends Builder> {
    P end();
}
