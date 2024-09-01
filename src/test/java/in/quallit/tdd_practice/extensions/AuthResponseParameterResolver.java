package in.quallit.tdd_practice.extensions;

import in.quallit.tdd_practice.dtos.AuthResponse;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * @author Jigs
 */
public class AuthResponseParameterResolver implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == AuthResponse.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (!this.supportsParameter(parameterContext, extensionContext)) {
            return null;
        }
        return extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("token");
    }
}
