package at.rknoll.miecplugin;

import at.rknoll.miecplugin.psi.MiecProperty;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.util.PsiTreeUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecFoldingBuilder extends FoldingBuilderEx {
    @NotNull
    @Override
    public FoldingDescriptor[] buildFoldRegions(@NotNull PsiElement root, @NotNull Document document, boolean quick) {
        final String MIEC = "miec";
        final String PREFIX = MIEC + ":";

        FoldingGroup group = FoldingGroup.newGroup(MIEC);

        List<FoldingDescriptor> descriptors = new ArrayList<>();
        Collection<PsiLiteralExpression> literalExpressions = PsiTreeUtil.findChildrenOfType(root, PsiLiteralExpression.class);
        for (final PsiLiteralExpression literalExpression : literalExpressions) {
            Object literal = literalExpression.getValue();
            if (literal instanceof String) {
                String value = (String) literal;
                if (value.startsWith(PREFIX)) {
                    Project project = literalExpression.getProject();
                    final List<MiecProperty> properties = MiecUtil.findProperties(project, value.substring(PREFIX.length()));
                    if (properties.size() == 1) {
                        descriptors.add(new FoldingDescriptor(literalExpression.getNode(),
                                new TextRange(literalExpression.getTextRange().getStartOffset() + 1,
                                        literalExpression.getTextRange().getEndOffset() - 1), group) {
                            @Nullable
                            @Override
                            public String getPlaceholderText() {
                                return properties.get(0).getValue();
                            }
                        });
                    }
                }
            }
        }
        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    @Nullable
    @Override
    public String getPlaceholderText(@NotNull ASTNode node) {
        return "...";
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode node) {
        return true;
    }
}
