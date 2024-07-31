package com.app.extension.txt;

import com.app.numbers_service.Keys;
import com.app.parser.impl.NumbersLineParser;
import com.app.txt.load.impl.NumbersLoad;
import com.app.txt.transfer.impl.TransferImpl;
import com.app.validator.impl.NumbersValidator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class NumbersLoadExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(NumbersLoad.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        var transfer = new TransferImpl<Keys, Integer>();
        var lineParser = new NumbersLineParser("([1-9][0-9]*;){2}[1-9][0-9]*", new NumbersValidator());
        return new NumbersLoad(transfer, lineParser);
    }
}
