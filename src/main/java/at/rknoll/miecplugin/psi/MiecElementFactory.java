package at.rknoll.miecplugin.psi;

import at.rknoll.miecplugin.MiecFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;

/**
 * Created by rknoll on 11/04/15.
 */
public class MiecElementFactory {
    public static MiecProperty createProperty(Project project, String name, String value) {
        final MiecFile file = createFile(project, name + " = " + value);
        return (MiecProperty) file.getFirstChild();
    }

    public static MiecProperty createProperty(Project project, String name) {
        final MiecFile file = createFile(project, name);
        return (MiecProperty) file.getFirstChild();
    }

    public static PsiElement createCRLF(Project project) {
        final MiecFile file = createFile(project, "\n");
        return file.getFirstChild();
    }

    public static MiecFile createFile(Project project, String text) {
        String name = "hello.miec";
        return (MiecFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, MiecFileType.INSTANCE, text);
    }
}
