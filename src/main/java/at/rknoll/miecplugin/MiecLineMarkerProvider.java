package at.rknoll.miecplugin;

import at.rknoll.miecplugin.psi.MiecProperty;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecLineMarkerProvider extends RelatedItemLineMarkerProvider {
    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, Collection<? super RelatedItemLineMarkerInfo> result) {
        final String MIEC = "miec";
        final String PREFIX = MIEC + ":";

        if (element instanceof PsiLiteralExpression) {
            PsiLiteralExpression literalExpression = (PsiLiteralExpression) element;
            Object literal = literalExpression.getValue();
            if (literal instanceof String) {
                String value = (String) literal;
                if (value.startsWith(PREFIX)) {
                    Project project = element.getProject();
                    final List<MiecProperty> properties = MiecUtil.findProperties(project, value.substring(PREFIX.length()));
                    if (properties.size() > 0) {
                        NavigationGutterIconBuilder<PsiElement> builder =
                                NavigationGutterIconBuilder.create(MiecIcons.FILE).
                                        setTargets(properties).
                                        setTooltipText("Navigate to an miec property");
                        result.add(builder.createLineMarkerInfo(element));
                    }
                }
            }
        }
    }
}
