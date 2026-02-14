package com.yorkhuul.life.utils.misc;

@FunctionalInterface
public interface ToFloatFunction<T> {
    float applyAsFloat(T value);
}
