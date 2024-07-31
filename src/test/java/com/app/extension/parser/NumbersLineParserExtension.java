package com.app.extension.parser;

import com.app.parser.impl.NumbersLineParser;
import com.app.validator.impl.NumbersValidator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class NumbersLineParserExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(NumbersLineParser.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new NumbersLineParser("([1-9][0-9]*;){2}[1-9][0-9]*",new NumbersValidator());
    }
}
