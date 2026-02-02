package com.yorkhuul.life.map.tools;

@FunctionalInterface
public interface ToFloatFunction<T> {
    float applyAsFloat(T value);
}
