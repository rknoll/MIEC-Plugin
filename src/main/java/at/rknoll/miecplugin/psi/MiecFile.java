package at.rknoll.miecplugin.psi;

import at.rknoll.miecplugin.MiecFileType;
import at.rknoll.miecplugin.MiecLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecFile extends PsiFileBase {
    public MiecFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, MiecLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return MiecFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Miec File";
    }

    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }
}
