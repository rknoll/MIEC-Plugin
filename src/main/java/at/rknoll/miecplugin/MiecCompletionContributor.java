package at.rknoll.miecplugin;

import at.rknoll.miecplugin.psi.MiecTypes;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecCompletionContributor extends CompletionContributor {
    public MiecCompletionContributor() {
        extend(CompletionType.BASIC,
                PlatformPatterns.psiElement(MiecTypes.VALUE).withLanguage(MiecLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                    }
                }
        );
    }
}
