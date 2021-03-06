package com.discretion;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Any mathematical expression, such as a variable, a function or function application,
 * or a boolean expression.
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.MINIMAL_CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
public interface MathObject {
    void accept(MathObjectVisitor visitor);
}
