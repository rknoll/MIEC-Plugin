package at.rknoll.miecplugin;

import com.intellij.openapi.util.TextRange;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.*;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecReferenceContributor extends PsiReferenceContributor {
    @Override
    public void registerReferenceProviders(@NotNull PsiReferenceRegistrar registrar) {
        final String MIEC = "miec";
        final String PREFIX = MIEC + ":";

        registrar.registerReferenceProvider(PlatformPatterns.psiElement(PsiLiteralExpression.class),
                new PsiReferenceProvider() {
                    @NotNull
                    @Override
                    public PsiReference[] getReferencesByElement(@NotNull PsiElement element, @NotNull ProcessingContext context) {
                        PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
                        Object literal = literalExpression.getValue();
                        if (literal instanceof String) {
                            String text = (String) literal;
                            if (text.startsWith(PREFIX)) {
                                return new PsiReference[]{new MiecReference(element,
                                        new TextRange(PREFIX.length() + 1, text.length() + 1))};
                            }
                        }
                        return new PsiReference[0];
                    }
                });
    }
}
