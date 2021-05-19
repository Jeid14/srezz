package com.srezz.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import java.lang.annotation.*;

@Inherited
@Qualifier("criteria")
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.PARAMETER})
public @interface CriteriaQualifier {
}
