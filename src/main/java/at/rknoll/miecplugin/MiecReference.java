package at.rknoll.miecplugin;

import at.rknoll.miecplugin.psi.MiecProperty;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecReference extends PsiReferenceBase<PsiElement> implements PsiPolyVariantReference {
    private String key;

    public MiecReference(@NotNull PsiElement element, TextRange textRange) {
        super(element, textRange);
        key = element.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
    }

    @NotNull
    @Override
    public ResolveResult[] multiResolve(boolean incompleteCode) {
        Project project = myElement.getProject();
        final List<MiecProperty> properties = MiecUtil.findProperties(project, key);
        List<ResolveResult> results = new ArrayList<>();
        for (MiecProperty property : properties) {
            results.add(new PsiElementResolveResult(property));
        }
        return results.toArray(new ResolveResult[results.size()]);
    }

    @Nullable
    @Override
    public PsiElement resolve() {
        ResolveResult[] resolveResults = multiResolve(false);
        return resolveResults.length == 1 ? resolveResults[0].getElement() : null;
    }

    @NotNull
    @Override
    public Object[] getVariants() {
        Project project = myElement.getProject();
        List<MiecProperty> properties = MiecUtil.findProperties(project);
        List<LookupElement> variants = new ArrayList<>();
        for (final MiecProperty property : properties) {
            if (property.getKey() != null && property.getKey().length() > 0) {
                variants.add(LookupElementBuilder.create(property).
                                withIcon(MiecIcons.FILE).
                                withTypeText(property.getContainingFile().getName())
                );
            }
        }
        return variants.toArray();
    }
}
