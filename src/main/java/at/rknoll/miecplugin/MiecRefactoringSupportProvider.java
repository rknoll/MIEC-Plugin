package at.rknoll.miecplugin;

import at.rknoll.miecplugin.psi.MiecProperty;
import com.intellij.lang.refactoring.RefactoringSupportProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecRefactoringSupportProvider extends RefactoringSupportProvider {
    @Override
    public boolean isMemberInplaceRenameAvailable(@NotNull PsiElement element, PsiElement context) {
        return element instanceof MiecProperty;
    }
}
