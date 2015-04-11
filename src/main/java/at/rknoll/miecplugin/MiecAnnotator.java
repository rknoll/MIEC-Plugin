package at.rknoll.miecplugin;

import at.rknoll.miecplugin.psi.MiecProperty;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecAnnotator implements Annotator {
    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        final String MIEC = "miec";
        final String PREFIX = MIEC + ":";

        if (element instanceof PsiLiteralExpression) {
            PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
            Object literal = literalExpression.getValue();
            if (literal instanceof String) {
                String value = (String) literal;
                if (value.startsWith(PREFIX) && !value.isEmpty()) {
                    Project project = element.getProject();
                    String key = value.substring(PREFIX.length());
                    List<MiecProperty> properties = MiecUtil.findProperties(project, key);
                    if (properties.size() == 1) {
                        TextRange range = new TextRange(element.getTextRange().getStartOffset() + PREFIX.length(),
                                element.getTextRange().getStartOffset() + PREFIX.length());
                        Annotation annotation = holder.createInfoAnnotation(range, null);
                        annotation.setTextAttributes(DefaultLanguageHighlighterColors.LINE_COMMENT);
                    } else if (properties.size() == 0) {
                        TextRange range = new TextRange(element.getTextRange().getStartOffset() + PREFIX.length() + 1,
                                element.getTextRange().getEndOffset());
                        Annotation annotation = holder.createErrorAnnotation(range, "Unresolved property");
                        if (!key.isEmpty()) {
                            annotation.registerFix(new CreatePropertyQuickFix(key));
                        }
                    }
                }
            }
        }
    }
}
