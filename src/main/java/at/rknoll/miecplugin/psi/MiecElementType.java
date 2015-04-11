package at.rknoll.miecplugin.psi;

import at.rknoll.miecplugin.MiecLanguage;
import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecElementType extends IElementType {
    public MiecElementType(@NotNull @NonNls String debugName) {
        super(debugName, MiecLanguage.INSTANCE);
    }
}
