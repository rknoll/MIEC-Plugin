package at.rknoll.miecplugin.psi.impl;

import at.rknoll.miecplugin.psi.MiecNamedElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

/**
 * Created by rknoll on 11/04/15.
 */
public abstract class MiecNamedElementImpl extends ASTWrapperPsiElement implements MiecNamedElement {
    public MiecNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }
}
