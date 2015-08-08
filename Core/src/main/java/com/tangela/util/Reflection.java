package com.tangela.util;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.reflections.Reflections;

public class Reflection
{
	public Set<Class<?>> getTypesAnnotatedWith(String pkg_name, Class<? extends Annotation> annotation)
	{
		Reflections reflections = new Reflections(pkg_name);
		return reflections.getTypesAnnotatedWith(annotation);
	}
}