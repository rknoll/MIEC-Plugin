package at.rknoll.miecplugin;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecFileType extends LanguageFileType {
    public static final MiecFileType INSTANCE = new MiecFileType();

    private MiecFileType() {
        super(MiecLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Miec File";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Miec Source File";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "miec";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return MiecIcons.FILE;
    }
}
