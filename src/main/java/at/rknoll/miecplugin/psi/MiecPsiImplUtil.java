package at.rknoll.miecplugin.psi;

import at.rknoll.miecplugin.MiecIcons;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecPsiImplUtil {
    public static String getKey(MiecProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(MiecTypes.KEY);
        if (keyNode != null) {
            return keyNode.getText();
        } else {
            return null;
        }
    }

    public static String getValue(MiecProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(MiecTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

    public static String getName(MiecProperty element) {
        return getKey(element);
    }

    public static PsiElement setName(MiecProperty element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(MiecTypes.KEY);
        if (keyNode != null) {

            MiecProperty property = MiecElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(MiecProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(MiecTypes.KEY);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }

    public static ItemPresentation getPresentation(final MiecProperty element) {
        return new ItemPresentation() {
            @Nullable
            @Override
            public String getPresentableText() {
                return element.getKey();
            }

            @Nullable
            @Override
            public String getLocationString() {
                return element.getContainingFile().getName();
            }

            @Nullable
            @Override
            public Icon getIcon(boolean unused) {
                return MiecIcons.FILE;
            }
        };
    }
}
